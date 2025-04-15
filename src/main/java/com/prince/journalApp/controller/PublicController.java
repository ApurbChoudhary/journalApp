package com.prince.journalApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prince.journalApp.dto.UserDTO;
import com.prince.journalApp.entities.User;
import com.prince.journalApp.mapper.UserMapper;
import com.prince.journalApp.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUsers")
    public ResponseEntity<?> addUsers(@RequestBody UserDTO  userDTO) {
        User users = UserMapper.toEntity(userDTO);
        log.debug("User Object: ", users);
        Optional<User> result = Optional.ofNullable(users).map(user -> {
            userService.saveNewUsers(users);
            return user;
        });
        if(result.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
        }
    }



}
