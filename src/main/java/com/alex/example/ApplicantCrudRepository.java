package com.alex.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantCrudRepository extends JpaRepository<Applicant, Long> {

    @Query("SELECT a FROM Applicant a WHERE a.name LIKE CONCAT('%', :name, '%')")
    List<Applicant> findApplicantByPartialName(@Param("name") String partialName);

}
