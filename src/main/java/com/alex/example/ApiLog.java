package com.alex.example;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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


    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "request_body", columnDefinition = "jsonb")
    private JsonNode requestBody;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "response_body", columnDefinition = "jsonb")
    private JsonNode responseBody;


    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "parameters", columnDefinition = "jsonb")
    private JsonNode parameters;


    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "headers", columnDefinition = "jsonb")
    private JsonNode headers;


    private Instant createdAt;

    public ApiLog(String method, String endpoint, int statusCode, JsonNode requestBody, JsonNode responseBody, JsonNode headers, Instant createdAt, JsonNode parameters) {
        this.method = method;
        this.endpoint = endpoint;
        this.statusCode = statusCode;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.headers = headers;
        this.createdAt = createdAt;
        this.parameters = parameters;
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

    public JsonNode getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(JsonNode requestBody) {
        this.requestBody = requestBody;
    }

    public JsonNode getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(JsonNode responseBody) {
        this.responseBody = responseBody;
    }

    public JsonNode getHeaders() {
        return headers;
    }

    public void setHeaders(JsonNode headers) {
        this.headers = headers;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public JsonNode getParameters() {
        return parameters;
    }

    public void setParameters(JsonNode parameters) {
        this.parameters = parameters;
    }
}
