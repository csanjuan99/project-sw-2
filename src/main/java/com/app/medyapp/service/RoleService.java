package com.app.medyapp.service;

import com.app.medyapp.enums.ERole;
import com.app.medyapp.model.Role;
import com.app.medyapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(ERole roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
