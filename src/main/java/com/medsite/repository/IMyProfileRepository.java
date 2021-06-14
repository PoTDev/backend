package com.medsite.repository;

import com.medsite.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMyProfileRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
