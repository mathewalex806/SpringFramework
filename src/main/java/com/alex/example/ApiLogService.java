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

    public ApiLog saveLog(ApiLog apiLog)
    {
        return apiLogRepository.save(apiLog);
    }
}
