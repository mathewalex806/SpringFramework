package com.alex.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.Instant;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest rawRequest, HttpServletResponse rawResponse, FilterChain filterChain) throws ServletException, IOException
    {
        CachedBodyHttpServletRequest request = new CachedBodyHttpServletRequest(rawRequest);
        ContentCachingResponseWrapper response = new ContentCachingResponseWrapper(rawResponse);

        filterChain.doFilter(request, response);

        String method = request.getMethod();
        String uri = request.getRequestURI();


        String requestBody = request.getCachedBodyAsString(request.getCharacterEncoding());


        byte[] respArr = response.getContentAsByteArray();
        String responseBody = new String(respArr, response.getCharacterEncoding());

        int status = response.getStatus();
        System.out.println(method+ uri+ requestBody+ responseBody+status+ Instant.now());
        response.copyBodyToResponse();
    }
}
