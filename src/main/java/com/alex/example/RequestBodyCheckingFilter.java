package com.alex.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class RequestBodyCheckingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Filter activated");
        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);

        String body = cachedRequest.getCachedBodyAsString(cachedRequest.getCharacterEncoding());
        System.out.println("Request Body (before controller): " + body);

        filterChain.doFilter(cachedRequest, response); // Pass wrapped request forward
    }
}




