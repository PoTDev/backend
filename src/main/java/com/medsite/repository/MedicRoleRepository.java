package com.medsite.repository;

import com.medsite.Entities.MedicRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRoleRepository extends JpaRepository<MedicRoles, Long> {
    MedicRoles findByName(String name);
}
