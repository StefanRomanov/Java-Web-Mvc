package com.excersise.virus.entities.models;

public class RoleServiceDto {
    private Long id;

    private String authority;

    public RoleServiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
