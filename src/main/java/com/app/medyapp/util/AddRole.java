/*package com.app.medyapp.util;

import com.app.medyapp.enums.ERole;
import com.app.medyapp.model.Role;
import com.app.medyapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AddRole implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(ERole.ROLE_ADMIN);
        Role roleUser = new Role(ERole.ROLE_USER);
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
    }
}*/