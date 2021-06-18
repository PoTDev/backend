package com.medsite.repository;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPatientExamRepository extends JpaRepository<PatientExam, Long> {

    PatientExam findByEmail(String email);

    List<PatientExam> findAllByEmail(String email);



}
