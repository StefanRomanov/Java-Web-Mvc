package com.excersise.virus.repos;

import com.excersise.virus.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole getUserRoleByAuthority(String authority);
}
