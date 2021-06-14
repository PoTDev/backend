package com.medsite.service;


import com.medsite.Entities.User;
import com.medsite.repository.IMyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class MyProfileService {

    @Autowired
    private IMyProfileRepository repo;

    public User emailRequest(Principal principal){

        return repo.findByEmail(principal.getName());
    }
}
