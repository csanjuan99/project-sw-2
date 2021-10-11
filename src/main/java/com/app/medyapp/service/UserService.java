package com.app.medyapp.service;

import com.app.medyapp.model.User;
import com.app.medyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public Boolean existsByUserEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public boolean existsbyUserId(int userId) {
        return userRepository.existsById(userId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }

    public void deleteByUserDni(int userDni) {
        userRepository.deleteById(userDni);
    }


}
