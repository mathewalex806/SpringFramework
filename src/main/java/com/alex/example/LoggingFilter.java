package com.alex.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final ApiLogService apiLogService;

    public LoggingFilter(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest rawRequest,
            HttpServletResponse rawResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // Wrap request to cache body
        CachedBodyHttpServletRequest request = new CachedBodyHttpServletRequest(rawRequest);
        // Wrap response to capture body
        ContentCachingResponseWrapper response = new ContentCachingResponseWrapper(rawResponse);

        // Proceed with processing (controllers, other filters)
        filterChain.doFilter(request, response);

        // Extract details
        String method = request.getMethod();
        String endpoint = request.getRequestURI();
        String requestBody = request.getCachedBodyAsString(request.getCharacterEncoding());
        byte[] respArr = response.getContentAsByteArray();
        String responseBody = new String(respArr, response.getCharacterEncoding());
        int statusCode = response.getStatus();
        Instant createdAt = Instant.now();

        System.out.println(method + " " + endpoint+ " " + requestBody + " " + responseBody + " " + statusCode + " " + createdAt);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode requestbodyjson = mapper.readTree(requestBody);
        JsonNode responsebodyjson = mapper.readTree(responseBody);
        ApiLog apiLog = new ApiLog(method, endpoint, statusCode, requestbodyjson, responsebodyjson,  null, createdAt);
        apiLogService.saveLog(apiLog);


        response.copyBodyToResponse();
    }
}
