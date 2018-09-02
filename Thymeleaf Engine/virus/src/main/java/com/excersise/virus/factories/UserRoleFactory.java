package com.excersise.virus.factories;

import com.excersise.virus.entities.UserRole;

public final class UserRoleFactory {
    public UserRoleFactory() {}

    public final UserRole createUserRole(String authority) {
        UserRole userRole = new UserRole();

        userRole.setAuthority(authority);

        return userRole;
    }
}
