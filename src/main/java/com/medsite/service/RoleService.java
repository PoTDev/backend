package com.medsite.service;


import com.medsite.Entities.Role;
import com.medsite.repository.IProfileRepository;
import com.medsite.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private IRoleRepository repo;

    public List<Role> listAll() {
        return repo.findAll();
    }
}
