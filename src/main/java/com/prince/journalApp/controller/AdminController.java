package com.prince.journalApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.ListUtils;

import com.prince.journalApp.entities.User;
import com.prince.journalApp.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getallUsers();
        if(!ListUtils.isEmpty(users)) {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

}
