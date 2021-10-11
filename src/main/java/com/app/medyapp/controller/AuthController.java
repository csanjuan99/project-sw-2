package com.app.medyapp.controller;

import com.app.medyapp.dto.JwtDTO;
import com.app.medyapp.dto.LoggedUserDTO;
import com.app.medyapp.dto.MessageDTO;
import com.app.medyapp.dto.NewUserDTO;
import com.app.medyapp.enums.ERole;
import com.app.medyapp.model.Role;
import com.app.medyapp.model.User;
import com.app.medyapp.security.JwtProvider;
import com.app.medyapp.service.RoleService;
import com.app.medyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    //Espera un json y lo convierte a tipo clase NuevoUsuario
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody NewUserDTO newUserDTO) {

        if (userService.existsByUserEmail(newUserDTO.getUserEmail())) {
            return new ResponseEntity<>(new MessageDTO("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(newUserDTO.getUserEmail(),
                passwordEncoder.encode(newUserDTO.getUserPassword()), newUserDTO.getUserName(), newUserDTO.getUserLastname(), newUserDTO.getUserDni());

        Set<Role> role = new HashSet<>();
        role.add(roleService.getByRoleName(ERole.ROLE_USER).get());
        if (newUserDTO.getRole().contains("admin"))
            role.add(roleService.getByRoleName(ERole.ROLE_ADMIN).get());
        user.setRole(role);
        userService.saveUser(user);

        return new ResponseEntity<>(new MessageDTO("Usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDTO> signIn(@Valid @RequestBody LoggedUserDTO loggedUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new MessageDTO("Campos mal"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loggedUserDTO.getUserEmail(),
                                loggedUserDTO.getUserPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(),
                userService.getByUserEmail(userDetails.getUsername()).get().getUserName(),
                userService.getByUserEmail(userDetails.getUsername()).get().getUserLastname(),
                userService.getByUserEmail(userDetails.getUsername()).get().getUserDni(),
                userService.getByUserEmail(userDetails.getUsername()).get().getUserId(),
                userService.getByUserEmail(userDetails.getUsername()).get().getUserPassword(),
                userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }
}
