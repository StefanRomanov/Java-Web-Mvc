package com.excersise.virus.services;

import com.excersise.virus.entities.UserRole;
import com.excersise.virus.entities.models.RoleServiceDto;

import java.util.List;

public interface UserRoleService {
    UserRole findRoleByName(String name);
    List<UserRole> findAll();
    void saveRole(UserRole role);
}
