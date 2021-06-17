package com.medsite.repository;

import com.medsite.Entities.Picture;
import com.medsite.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByPatientExams_Id(Long id);
}
