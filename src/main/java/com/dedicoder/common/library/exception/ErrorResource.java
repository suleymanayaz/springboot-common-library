package com.dedicoder.common.library.exception;

public class ErrorResource {
    private String applicationName;
    private String path;
    private String httpMethod;

    public ErrorResource(String applicationName, String path, String httpMethod) {
        this.applicationName = applicationName;
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
