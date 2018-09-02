package com.excersise.virus.services;

import com.excersise.virus.entities.UserRole;
import com.excersise.virus.entities.models.RoleServiceDto;
import com.excersise.virus.repos.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    private final UserRoleRepository userRoleRepository;

    private final ModelMapper modelMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRole findRoleByName(String name) {
        return this.userRoleRepository.getUserRoleByAuthority(name);
    }

    @Override
    public List<UserRole> findAll() {
        return this.userRoleRepository.findAll();
    }

    @Override
    public void saveRole(UserRole role) {
        this.userRoleRepository.save(role);
    }
}
