package com.SaaS.RestaurantAPI.controller;


import com.SaaS.RestaurantAPI.model.User;
import com.SaaS.RestaurantAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ArrayList<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @GetMapping(path = "/log/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return this.userService.getUserByUsername(username);
    }
    
    @PostMapping(path = "/new")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping(path = "/update-{id}")
    public User updateUser(@RequestBody User request, @PathVariable int id) {
        return this.userService.updateUserById(request, id);
    }

    @DeleteMapping(path = "/delete-{id}")
    public String deleteUser(@PathVariable int id) {
        boolean isDeleted = this.userService.deleteUserById(id);
        if (isDeleted) {
            return "User with id: " + id + " was deleted";
        } else {
            return "Unexpected error: User with id:" + id + " was not deleted";
        }
    }
}
