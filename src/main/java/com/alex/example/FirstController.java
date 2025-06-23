package com.alex.example;


import com.alex.example.ApiLogPackage.ApiLog;
import com.alex.example.ApiLogPackage.ApiLogRepository;
import com.alex.example.ApiLogPackage.ApiLogService;
import com.alex.example.Applicantpackage.Applicant;
import com.alex.example.Applicantpackage.ApplicantService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController
{
    @Autowired
    ApiLogService apiLogService;
    @Autowired
    ApiLogRepository apilogRepository;

    @Autowired
    ApplicantService applicantService;
    @GetMapping("/Applicant")
    public List<Applicant> getallApplicant()
    {
        return applicantService.getAllApplicants();
    }

    @PostMapping("/Applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant)
    {
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("/replay/group")
    public List<ApiLog> groupbymethodandendpoint(@RequestParam("endpoint")String endpoint, @RequestParam("method")String method)
    {
        endpoint = endpoint.trim();
        method = method.trim();
        return apilogRepository.findByMethodAndEndpoint(method, endpoint);
    }


    @GetMapping("/generalizepath")
    public void generalpath() {
        List<ApiLog> apilog = apiLogService.getAllLog();

        for (ApiLog log : apilog)
        {
            String path = log.getEndpoint();
            StringBuilder generalized = new StringBuilder();

            String[] segments = path.split("/");
            for (String segment : segments)
            {
                if (segment.isEmpty())
                {
                    continue;
                }
                else if (segment.matches("^\\d+$"))
                {  // match integer IDs
                    generalized.append("/").append(":int");
                }
                else if (segment.matches("^[0-9a-fA-F\\-]{36}$"))
                {  // match UUID
                    generalized.append("/").append(":uuid");
                }
                else if (segment.matches("^[a-zA-Z0-9]+-[a-zA-Z0-9]+$") && segment.length() > 3)
                {
                    generalized.append("/").append(":hypenated");
                }
                else
                {
                    generalized.append("/").append(segment);
                }
            }


            log.setEndpoint(generalized.toString());
            apilogRepository.save(log);
        }
    }


}



