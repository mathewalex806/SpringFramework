package com.alex.example;


import com.alex.example.ApiLogPackage.ApiLogService;
import com.alex.example.Applicantpackage.Applicant;
import com.alex.example.Applicantpackage.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
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

}



