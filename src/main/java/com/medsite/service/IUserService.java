package com.medsite.service;

import com.medsite.Entities.User;
import com.medsite.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);
}


