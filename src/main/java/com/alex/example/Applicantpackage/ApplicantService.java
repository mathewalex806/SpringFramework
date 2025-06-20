package com.alex.example.Applicantpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    ApplicantCrudRepository applicantCrudRepository;


    public List<Applicant> getAllApplicants()
    {
        return (List<Applicant>) applicantCrudRepository.findAll();
    }

    public Applicant addApplicant(Applicant applicant)
    {
        return applicantCrudRepository.save(applicant);
    }

    public List<Applicant> findByPartialName(String partialName)
    {
        return applicantCrudRepository.findApplicantByPartialName(partialName);
    }



}
