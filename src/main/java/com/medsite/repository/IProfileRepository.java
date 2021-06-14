package com.medsite.repository;

import com.medsite.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IProfileRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE (u.firstName LIKE '%criteria%') OR (u.firstName LIKE '%criteria%') OR (u.lastName LIKE '%criteria%') OR (u.email LIKE '%criteria%')")
    List<User> searchProfile(String criteria);
}
