package com.medsite.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "medic_roles")
public class MedicRoles {

    public MedicRoles() {

    }

    public MedicRoles(String name) {
        super();
        this.name =name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medic_id")
    private Long id;

    @Column(name="role")
    private String name;





    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

