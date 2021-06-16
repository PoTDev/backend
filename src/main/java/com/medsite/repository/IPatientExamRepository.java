package com.medsite.repository;

import com.medsite.Entities.PatientExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientExamRepository extends JpaRepository<PatientExam, Long> {
    PatientExam findByEmail(String email);

}
