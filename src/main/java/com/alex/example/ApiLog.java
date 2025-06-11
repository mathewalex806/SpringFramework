package com.alex.example;

import jakarta.persistence.*;

import java.time.Instant;


@Entity
public class ApiLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String method;
    @Column(nullable = false)
    private String endpoint;
    private int statusCode;
    @Column(columnDefinition = "text")
    private String requestBody;
    @Column(columnDefinition = "text")
    private String responseBody;
    private String headers;
    private Instant createdAt;

    public ApiLog(String method, String endpoint, int statusCode, String requestBody, String responseBody, String headers, Instant createdAt) {
        this.method = method;
        this.endpoint = endpoint;
        this.statusCode = statusCode;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.headers = headers;
        this.createdAt = createdAt;
    }

    public ApiLog() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
