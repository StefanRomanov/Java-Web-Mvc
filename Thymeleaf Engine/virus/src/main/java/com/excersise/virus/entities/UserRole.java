package com.excersise.virus.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {

    private Long id;
    private String authority;

    public UserRole() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    @Column(nullable = false)
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
