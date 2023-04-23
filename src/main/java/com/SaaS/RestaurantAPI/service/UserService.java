package com.SaaS.RestaurantAPI.service;

import com.SaaS.RestaurantAPI.model.User;
import com.SaaS.RestaurantAPI.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }
    
    public User saveUser(User user) {
        Date currentDate = new Date(System.currentTimeMillis());
        user.setCreated_at(currentDate);
        user.setUpdated_at(currentDate);
        return userRepository.save(user);
    }

    public User updateUserById(User request, int id) {
        User user = getUserById(id);
        user.setEmail (request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        
        Date currentDate = new Date(System.currentTimeMillis());
        user.setUpdated_at(currentDate);
        userRepository.save(user);

        return user;
    }

    public Boolean deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
