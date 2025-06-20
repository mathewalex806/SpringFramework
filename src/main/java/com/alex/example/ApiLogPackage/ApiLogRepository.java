package com.alex.example.ApiLogPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long>
{
    List<ApiLog> findByMethod(String method);
    List<ApiLog> findByEndpoint(String endpoint);
    List<ApiLog> findByStatusCode(Integer code);
}
