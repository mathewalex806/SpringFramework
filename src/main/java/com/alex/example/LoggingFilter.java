package com.alex.example;

import com.alex.example.ApiLogPackage.ApiLog;
import com.alex.example.ApiLogPackage.ApiLogService;
import com.alex.example.Elastic.ElasticLogService;
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
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final ApiLogService apiLogService;
    private final ElasticLogService elasticLogService;
    private final EmbeddingModel embeddingModel;

    public LoggingFilter(ApiLogService apiLogService, ElasticLogService elasticLogService, EmbeddingModel embeddingModel)
    {
        this.apiLogService = apiLogService;
        this.elasticLogService = elasticLogService;
        this.embeddingModel = embeddingModel;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest rawRequest, HttpServletResponse rawResponse, FilterChain filterChain) throws ServletException, IOException
    {

        CachedBodyHttpServletRequest request = new CachedBodyHttpServletRequest(rawRequest);
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
        ObjectMapper mapper = new ObjectMapper();


        //Params
        Map<String, String[]> parameters = request.getParameterMap();
        JsonNode parameter = mapper.valueToTree(parameters);

        Map<String, String> headers = new HashMap<>();

        //Headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }

        JsonNode headerNode = mapper.valueToTree(headers);


        JsonNode requestbodyjson = mapper.readTree(requestBody);
        JsonNode responsebodyjson = mapper.readTree(responseBody);


        ApiLog apiLog = new ApiLog(method, endpoint, statusCode, requestbodyjson, responsebodyjson,  headerNode, createdAt, parameter,null );
        apiLogService.saveLog(apiLog);
        System.out.println("Created DB entry");
        try
        {
            float[] vector  = embeddingModel.createEmbedding(apiLog);
            System.out.println("vector obtained in filter"+ Arrays.toString(vector));
            apiLog.setEmbedding(vector);
            apiLogService.saveLog(apiLog);
            elasticLogService.saveToElastic(apiLog);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        response.copyBodyToResponse();
    }
}
