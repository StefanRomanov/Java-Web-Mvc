package com.excersise.virus.config;


import com.excersise.virus.entities.UserRole;
import com.excersise.virus.entities.enums.UserRoleEnum;
import com.excersise.virus.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRoleService roleService;

    @Autowired
    public RoleSeeder(UserRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {
        if(this.roleService.findAll().isEmpty()){

            for (UserRoleEnum role : UserRoleEnum.values()){
                UserRole userRole = new UserRole();
                userRole.setAuthority(role.name());
                this.roleService.saveRole(userRole);
            }
        }
    }
}
