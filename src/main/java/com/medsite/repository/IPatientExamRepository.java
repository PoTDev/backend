package com.medsite.repository;

import com.medsite.Entities.PatientExam;
import com.medsite.Entities.Product;
import com.medsite.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPatientExamRepository extends JpaRepository<PatientExam, Long> {

}
