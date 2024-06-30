# Common Library

This library provides general configurations, exception handling, logging, and configuration settings for various services. By using this library in your projects, you can easily integrate these settings without dealing with them individually.

## Contents

- [Installation](#installation)
- [Usage](#usage)
  - [General Configuration](#general-configuration)
  - [Base Path Configuration](#base-path-configuration)
  - [Logging](#logging)
    - [Logger Details](#logger-details)
  - [Exception Handling](#exception-handling)
  - [Web Service Configuration](#web-service-configuration)
  - [Database Configuration](#database-configuration)
    - [MSSQL](#mssql)
    - [Oracle](#oracle)
  - [SAP Configuration](#sap-configuration)
  - [Salesforce Configuration](#salesforce-configuration)
  - [FTP Configuration](#ftp-configuration)
  - [S3 Configuration](#s3-configuration)

## Installation

### Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.dedicoder.common.library</groupId>
    <artifactId>common-library</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Add the following dependency to your `build.gradle` file:

```groovy
implementation 'com.dedicoder.common.library:common-library:1.0.0'
```




## Usage

### General Configuration

You can enable general configurations simply by adding the library to your project. There is no need for additional configuration as it is already set up to be auto-configured.

For Spring Boot 3, the configuration is managed in `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.

For Spring Boot 2, the configuration is managed in `src/main/resources/META-INF/spring.factories`.

Just add the dependency and the configurations will be automatically applied.

### Base Path Configuration

If you want to set a base path for your application, you can add the following property to your `application.properties` or `application.yml` file:

For `application.properties`:
```properties
http.basePath=/api
```
For `application.yaml`:
```properties
http:
    basePath: /api
```
If the http.basePath property is not set, the default base path will be /.



### Logging

The default logging levels are set as follows:

```xml
<Logger name="com.dedicoder.general.exception" level="ERROR" />
<Logger name="com.dedicoder.http.internal.HttpMessageLogger.request" level="WARN" />
<Logger name="com.dedicoder.http.internal.HttpMessageLogger.listener" level="WARN" />
```
If you need to override these settings in your project's log4j2.xml, you can do so by adding the following configurations:

```xml
<Logger name="com.dedicoder.general.exception" level="ERROR"/>
<Logger name="com.dedicoder.http.internal.HttpMessageLogger.request" level="DEBUG" />
<Logger name="com.dedicoder.http.internal.HttpMessageLogger.listener" level="DEBUG" />
<!-- To enable all logging under HttpMessageLogger
 <Logger name="com.dedicoder.http.internal.HttpMessageLogger" level="DEBUG" /> 
 -->
```
Add these logger configurations to your project's log4j2.xml file to customize the logging levels and appenders.
#### Logger Details

**GeneralExceptionHandler Logger**:
- **Logger Name**: `com.dedicoder.general.exception`
- **Purpose**: Logs general exception errors.
- **Default Level**: `ERROR`
- **Example**:
    ```log
    2024-06-30 18:23:05,114 ERROR c.d.g.exception [http-nio-8080-exec-3] 3a42b766-7424-486d-a8b7-4e0c43f0593a Error occurred: The requested resource could not be found
    ```

**Request Logger**:
- **Logger Name**: `com.dedicoder.http.internal.HttpMessageLogger.request`
- **Purpose**: Logs outgoing HTTP requests.
- **Default Level**: `DEBUG`
- **Example**:
    ```log
    2024-06-30 18:23:05,139 DEBUG c.d.h.i.H.request [http-nio-8080-exec-2] 72e7e296-bf4c-4966-9ad0-3dfce95ffc60 
   Requester
  GET http://localhost:8080/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9
  Accept: application/json, application/*+json
  Content-Length: 0
  x-correlation-id: 3a42b766-7424-486d-a8b7-4e0c43f0593a
  User-Agent: AHC/1.0
  
  Requester Response 
  404
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Sun, 30 Jun 2024 15:23:05 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
   {"correlationId":"3a42b766-7424-486d-a8b7-4e0c43f0593a","statusCode":404,"message":"The requested resource could not be found","detail":"/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9","errors":[{"applicationName":"account","path":"/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9","httpMethod":"GET"}]}
    ```

**Listener Logger**:
- **Logger Name**: `com.dedicoder.http.internal.HttpMessageLogger.listener`
- **Purpose**: Logs incoming HTTP requests.
- **Default Level**: `DEBUG`
- **Example**:
    ```log
    2024-06-30 18:23:05,148 DEBUG c.d.h.i.H.listener [http-nio-8080-exec-2]  
  Listener
  POST /api/1.0/account
  host: test
  content-type: application/json
  user-agent: PostmanRuntime/7.39.0
  accept: */*
  cache-control: no-cache
  postman-token: fbbdb2f8-57ad-4782-a77a-9fa6840720fe
  accept-encoding: gzip, deflate, br
  connection: keep-alive
  content-length: 87
  
  {
  "customerId": "1b114bf3-834f-4183-acdd-638b2c4b8fc9",
  "initialCredit": 0
  }
  
  
  Listener Response 
  500 Internal Server Error
  x-correlation-id: 3a42b766-7424-486d-a8b7-4e0c43f0593a
  
  {"correlationId":"72e7e296-bf4c-4966-9ad0-3dfce95ffc60","statusCode":500,"message":"Customer could not by id: 1b114bf3-834f-4183-acdd-638b2c4b8fc9","detail":"Internal Server Error","errors":[{"applicationName":"account","path":"/api/1.0/account","httpMethod":"POST"}]}

    ```

**HttpMessageLogger Logger**:
- **Logger Name**: `com.dedicoder.http.internal.HttpMessageLogger`
- **Purpose**: Logs all HTTP message logs (both requests and responses).
- **Example**:
    ```log
    2024-06-30 18:23:05,139 DEBUG c.d.h.i.H.request [http-nio-8080-exec-2] 72e7e296-bf4c-4966-9ad0-3dfce95ffc60 
  Requester
  GET http://localhost:8080/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9
  Accept: application/json, application/*+json
  Content-Length: 0
  x-correlation-id: 3a42b766-7424-486d-a8b7-4e0c43f0593a
  User-Agent: AHC/1.0
  
  
  Requester Response
  404
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Sun, 30 Jun 2024 15:23:05 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {"correlationId":"3a42b766-7424-486d-a8b7-4e0c43f0593a","statusCode":404,"message":"The requested resource could not be found","detail":"/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9","errors":[{"applicationName":"account","path":"/api/test/1b114bf3-834f-4183-acdd-638b2c4b8fc9","httpMethod":"GET"}]}
  
  2024-06-30 18:23:05,148 DEBUG c.d.h.i.H.listener [http-nio-8080-exec-2]  
  Listener
  POST /api/1.0/account
  host: test
  content-type: application/json
  user-agent: PostmanRuntime/7.39.0
  accept: */*
  cache-control: no-cache
  postman-token: fbbdb2f8-57ad-4782-a77a-9fa6840720fe
  accept-encoding: gzip, deflate, br
  connection: keep-alive
  content-length: 87
  
  {
  "customerId": "1b114bf3-834f-4183-acdd-638b2c4b8fc9",
  "initialCredit": 0
  }
  
  Listener Response
  500 Internal Server Error
  x-correlation-id: 3a42b766-7424-486d-a8b7-4e0c43f0593a
  
  {"correlationId":"72e7e296-bf4c-4966-9ad0-3dfce95ffc60","statusCode":500,"message":"Customer could not by id: 1b114bf3-834f-4183-acdd-638b2c4b8fc9","detail":"Internal Server Error","errors":[{"applicationName":"account","path":"/api/1.0/account","httpMethod":"POST"}]}
    ```




### Exception Handling

The `GeneralExceptionHandler` class is already configured for global exception handling. You don't need to do anything extra to use it. If you need to add custom exception handling, you can extend this class.

To add custom exception handling, create a class that extends `GeneralExceptionHandler`:

```java
import com.dedicoder.common.library.exception.generic.GeneralExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler extends GeneralExceptionHandler {
    
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<Object> handleCustomNotFoundException(CustomNotFoundException ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    // Create a custom error response body if necessary
    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
```




### Web Service Configuration

You can configure your web service by creating a configuration class that implements the `WSConfig` interface and by using Spring Boot's `@ConfigurationProperties` to bind the properties from your `application.properties` or `application.yml` file.

#### Example Configuration Class

Create a configuration class in your package, for example, `com.dedicoder.test.config.ws.TestWSConfig`:

```kotlin
package com.dedicoder.test.config.ws

import com.dedicoder.common.library.config.WSConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "test.ws.request")
open class TestWSConfig(
    override var protocol: String = "", // if not include test.ws.request.protocol in .properties file
    override var host: String = "",  // if not include test.ws.request.host in .properties file
    override var port: Int = 0,  // if not include test.ws.request.port in .properties file
    override var basePath: String = ""  // if not include test.ws.request.basePath in .properties file
) : WSConfig
```
### application.properties 

Add the following properties to your application.properties file:
```properties
test.ws.request.protocol=http
test.ws.request.host=localhost
test.ws.request.port=8080
test.ws.request.basePath=/api
```
### application.yml

Add the following properties to your application.yml file:
```yaml
paro:
  ws:
    request:
      protocol: http
      host: localhost
      port: 8080
      basePath: /api
```

With this setup, the TestWSConfig class will automatically bind to the properties defined in your application.properties or application.yml file.

### Accessing Configuration

You can access the configuration values in your service by autowiring the `TestWSConfig` class. Below is an example of how to use the configuration values to make a web service request.

#### Example Service Class

Create a service class in your package, for example, `com.dedicoder.test.service.TestService`:

```java
package com.dedicoder.test.service;

import com.dedicoder.test.config.ws.TestWSConfig;
...
...

@Service
public class TestWebService {
  private final TestWSConfig TestWSConfig;
  private final RestTemplate restTemplate;

  public TestWebService(TestWSConfig testWSConfig, RestTemplate restTemplate) {
    this.testWSConfig = testWSConfig;
    this.restTemplate = restTemplate;
  }

  public TestDto getTest(String id) {
    String url = String.format("%s://%s:%d%s/test/%s",
            testWSConfig.getProtocol(),
            testWSConfig.getHost(),
            testWSConfig.getPort(),
            testWSConfig.getBasePath(),
            id);
    return makeRequest(url);
  }

  private Test makeRequest(String url) {
    try {
      return restTemplate.getForObject(url, TestDto.class);
    } catch (HttpClientErrorException ex) {
      System.out.println("Client error: " + ex.getStatusCode());
      return null;
    } catch (HttpServerErrorException ex) {
      System.out.println("Server error: " + ex.getStatusCode());
      return null;
    } catch (RestClientException ex) {
      System.out.println("Error: " + ex.getMessage());
      return null;
    }
  }
}
```



This example shows how to create a configuration class that implements the WSConfig interface, bind it to properties defined in application.properties or application.yml, and access these properties within a Spring Boot application.


### Database Configuration

### MSSL

For MSSQL configurations, you can use the `MSSQLConfig` interface.

```kotlin
interface MSSQLConfig {
  val host: String
  val port: Int
  val database: String
  val username: String
  val password: String
}

```

### Oracle

For Oracle configurations, you can use the `OracleConfig` interface.

```kotlin
interface OracleConfig {
    val host: String
    val port: Int
    val serviceName: String
    val username: String
    val password: String
}
```

### SAP Configuration

For SAP configurations, you can use the `SAPConfig` interface.

```kotlin
interface SAPConfig {
  val host: String
  val port: Int
  val client: String
  val user: String
  val password: String
  val language: String
}
```


### SALESFORCE Configuration

For Salesforce configurations, you can use the `SalesforceConfig` interface.

```kotlin
interface SalesforceConfig {
  val clientId: String
  val clientSecret: String
  val username: String
  val password: String
  val securityToken: String
  val loginUrl: String
}
```

### FTP Configuration

For FTP configurations, you can use the `FTPConfig` interface.

```kotlin
interface FTPConfig {
  val host: String
  val port: Int
  val username: String
  val password: String
}
```

### S3 Configuration

For S3 configurations, you can use the `S3Config ` interface.

```kotlin
interface S3Config {
  val accessKey: String
  val secretKey: String
  val region: String
  val bucketName: String
}
```

This library is designed and maintained by Dedicoder. We hope it helps you streamline your project configurations and improve your development experience.  












