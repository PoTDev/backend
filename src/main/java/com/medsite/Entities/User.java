package com.medsite.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;



    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }


    @Column(name = "first_name")
    private String firstName;

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    @Column(name = "last_name")
    private String lastName;

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    @Column(name = "email")
    private String email;

    public String getUserEmail() {
        return email;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String username){
        this.email=username;
    }


    @Column(name = "password")
    private String password;

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    @Column(name = "active")
    private Boolean active;

    public boolean getActive(){
        return this.active;
    }
    public void setActive(boolean active){
        this.active=active;
    }

    protected User() {
    }
    public User(String firstName, String lastName, String email, String password, Collection< Role > roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active=true;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))

    private Collection <Role> roles;



    public void setRoles(Collection<Role> roles){
        this.roles = roles;
    }
    public Collection<Role> getRoles(){
        return this.roles;
    }


}