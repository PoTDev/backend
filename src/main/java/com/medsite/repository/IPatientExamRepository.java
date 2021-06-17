package com.medsite.repository;

import com.medsite.Entities.PatientExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPatientExamRepository extends JpaRepository<PatientExam, Long> {

    PatientExam findByEmail(String email);

}
