package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.User;
import com.gerenciador.tarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>("New user created " + createdUser.getUsername(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>("New user created " + createdUser.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping
    public void removeUser(@RequestBody User user) {
        userService.removeUser(user);
    }

}
