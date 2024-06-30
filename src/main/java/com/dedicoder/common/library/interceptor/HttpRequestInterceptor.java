package com.dedicoder.common.library.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class HttpRequestInterceptor implements ClientHttpRequestInterceptor  {

    private final static Logger log = LoggerFactory.getLogger("com.dedicoder.http.internal.HttpMessageLogger.request");
    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.valueOf("application/*+json"),
            MediaType.valueOf("application/*+xml"),
            MediaType.MULTIPART_FORM_DATA
    );

    /**
     * List of HTTP headers whose values should not be logged.
     */
    private static final List<String> SENSITIVE_HEADERS = Arrays.asList(
            "authorization",
            "proxy-authorization"
    );

    private boolean enabled = true;

    @ManagedOperation(description = "Enable logging of HTTP requests and responses")
    public void enable() {
        this.enabled = true;
    }

    @ManagedOperation(description = "Disable logging of HTTP requests and responses")
    public void disable() {
        this.enabled = false;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        StringBuilder msg = new StringBuilder();
        ClientHttpResponse response = null;
        String correlationId = CorrelationIdHolder.getCorrelationId();
        request.getHeaders().add("x-correlation-id", correlationId);
        request.getHeaders().add("User-Agent", "AHC/1.0");

        try {
            beforeRequest(request, body, msg);
            response = execution.execute(request, body);
            afterRequest(request, body, response, msg);
        } finally {
            if (msg.length() > 0) {
                log.debug(msg.toString());
            }
        }
        return response;
    }


    private void beforeRequest(HttpRequest request, byte[] body, StringBuilder msg) {
        if (enabled && log.isInfoEnabled()) {
            msg.append("\nRequester \n");
            logRequestHeader(request, msg);
            logRequestBody(request, body, msg);
        }
    }

    private void afterRequest(HttpRequest request, byte[] body, ClientHttpResponse response, StringBuilder msg) throws IOException {
        if (enabled && log.isInfoEnabled()) {
            msg.append("\nRequester Response \n");
            logResponse(response, msg);
        }
    }

    private static void logRequestHeader(HttpRequest request, StringBuilder msg) {
        String queryString = request.getURI().getQuery();
        if (queryString == null) {
            msg.append(String.format("%s %s", request.getMethod(), request.getURI())).append("\n");
        } else {
            msg.append(String.format("%s %s?%s", request.getMethod(), request.getURI(), queryString)).append("\n");
        }

        request.getHeaders().forEach((key, value) -> {
            if (isSensitiveHeader(key)) {
                msg.append(String.format("%s: %s", key, "*******")).append("\n");
            } else {
                msg.append(String.format("%s: %s", key, String.join(",", value))).append("\n");
            }
        });
        msg.append("\n");
    }


    private static void logRequestBody(HttpRequest request, byte[] content, StringBuilder msg) {
        if (content.length > 0) {
            String encoding = getEncodingFromHeaders(request.getHeaders());
            logContent(content, request.getHeaders().getContentType().toString(), encoding, msg);
        }
    }


    private static void logResponse(ClientHttpResponse response, StringBuilder msg) throws IOException {
        int status = response.getStatusCode().value();
        msg.append(String.format("%d %s", status, response.getStatusText())).append("\n");
        response.getHeaders().forEach((key, value) -> {
            if (isSensitiveHeader(key)) {
                msg.append(String.format("%s: %s", key, "*******")).append("\n");
            } else {
                msg.append(String.format("%s: %s", key, String.join(",", value))).append("\n");
            }
        });
        msg.append("\n");
        byte[] content = response.getBody().readAllBytes();
        if (content.length > 0) {
            logContent(content, response.getHeaders().getContentType().toString(), getEncodingFromHeaders(response.getHeaders()), msg);
        }
    }

    private static void logContent(byte[] content, String contentType, String contentEncoding, StringBuilder msg) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                String contentString = new String(content, contentEncoding);
                Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> msg.append(" ").append(line).append("\n"));
            } catch (UnsupportedEncodingException e) {
                msg.append(String.format("[%d bytes content]", content.length)).append("\n");
            }
        } else {
            msg.append(String.format("[%d bytes content]", content.length)).append("\n");
        }
    }

    /**
     * Determine if a given header name should have its value logged.
     *
     * @param headerName HTTP header name.
     * @return True if the header is sensitive (i.e. its value should <b>not</b> be logged).
     */
    private static boolean isSensitiveHeader(String headerName) {
        return SENSITIVE_HEADERS.contains(headerName.toLowerCase());
    }

    private static String getEncodingFromHeaders(org.springframework.http.HttpHeaders headers) {
        String encoding = StandardCharsets.UTF_8.name();
        List<String> contentTypeHeaders = headers.get("Content-Type");
        if (contentTypeHeaders != null && !contentTypeHeaders.isEmpty()) {
            String contentType = contentTypeHeaders.get(0);
            if (contentType != null && contentType.contains("charset=")) {
                encoding = contentType.substring(contentType.indexOf("charset=") + 8);
            }
        }
        return encoding;
    }


}
