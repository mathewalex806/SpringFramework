package com.alex.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogService {

    @Autowired
    private ApiLogRepository apiLogRepository;

    public List<ApiLog> getAllLog()
    {
        return apiLogRepository.findAll();
    }

    public void saveLog(ApiLog apiLog)
    {
         apiLogRepository.save(apiLog);
    }

    public List<ApiLog> getbymethod(String method)
    {
        return apiLogRepository.findByMethod(method);
    }

    public List<ApiLog> getbystatus(Integer code)
    {
        return apiLogRepository.findByStatusCode(code);
    }

    public List<ApiLog> getbyendpoint(String endpoint)
    {
        return apiLogRepository.findByEndpoint(endpoint);
    }

}
