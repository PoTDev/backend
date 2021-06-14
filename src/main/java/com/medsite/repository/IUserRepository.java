package com.medsite.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.medsite.Entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}