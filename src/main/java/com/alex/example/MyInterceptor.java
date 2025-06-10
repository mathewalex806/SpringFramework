package com.alex.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MyInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws UnsupportedEncodingException {
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println(response.getStatus());

    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {


        //Printing the response body
        if (response instanceof ContentCachingResponseWrapper wrappedResponse) {
            byte[] buf = wrappedResponse.getContentAsByteArray();
            if (buf.length > 0) {
                String responseBody = new String(buf, StandardCharsets.UTF_8);
                System.out.println("Response Body: " + responseBody);
            }
            else
            {
                System.out.println("Response Body empty");
            }
        }
        System.out.println(response.getStatus()+request.getRequestURI()+response.getStatus()+ request.getAttribute("startTime")+request.getAttribute("endTime")+request.getRemoteAddr());





    }

}
