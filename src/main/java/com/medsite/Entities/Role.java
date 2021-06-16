package com.medsite.Entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    public Role() {

    }
    public Role(String name) {
        super();
        this.name =name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}