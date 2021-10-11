package com.app.medyapp.controller;

import com.app.medyapp.dto.LoggedUserDTO;
import com.app.medyapp.dto.MessageDTO;
import com.app.medyapp.model.User;
import com.app.medyapp.service.UserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affiliate")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> listUser = this.userService.listUser();
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @GetMapping("/read/{user_id}")
    public ResponseEntity<User> getByDni(@PathVariable("user_id") int userId) {

        if (!userService.existsbyUserId(userId))
            return new ResponseEntity(new MessageDTO("No existe el afiliado"), HttpStatus.NOT_FOUND);

        User user = userService.getUserById(userId).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }


    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> updateById(@PathVariable("user_id") int userId, @RequestBody LoggedUserDTO loggedUserDTO) {

        if (!userService.existsbyUserId(userId))
            return new ResponseEntity(new MessageDTO("No existe el afiliado"), HttpStatus.NOT_FOUND);

        if (StringUtils.isBlank(loggedUserDTO.getUserName()))
            return new ResponseEntity(new MessageDTO("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if (StringUtils.isBlank(loggedUserDTO.getUserLastname()))
            return new ResponseEntity(new MessageDTO("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        User user = userService.getUserById(loggedUserDTO.getUserId()).get();
        user.setUserDni(loggedUserDTO.getUserDni());
        user.setUserName(loggedUserDTO.getUserName());
        user.setUserLastname(loggedUserDTO.getUserLastname());
        user.setUserEmail(loggedUserDTO.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(loggedUserDTO.getUserPassword()));
        userService.saveUser(user);
        return new ResponseEntity(new MessageDTO("Afiliado actualizado"), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{user_dni}")
    public ResponseEntity<?> deleteById(@PathVariable("user_id") int userId) {
        if (!userService.existsbyUserId(userId))
            return new ResponseEntity(new MessageDTO("No existe el afiliado"), HttpStatus.NOT_FOUND);
        userService.deleteByUserDni(userId);
        return new ResponseEntity(new MessageDTO("Afiliado eliminado"), HttpStatus.OK);
    }
}

