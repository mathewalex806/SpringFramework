package com.alex.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirstController
{
    @Autowired
    ApiLogService apiLogService;

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

    @GetMapping("/applicant-partialname")
    public List<Applicant> getByPartialName(@RequestParam("partialName") String partialName)
    {
        return applicantService.findByPartialName(partialName);
    }


    @GetMapping("/logs")
    public List<ApiLog> getLogs()
    {
        return apiLogService.getAllLog();
    }

}



