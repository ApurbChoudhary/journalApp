package com.prince.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prince.journalApp.dto.UserDTO;
import com.prince.journalApp.entities.User;
import com.prince.journalApp.mapper.UserMapper;
import com.prince.journalApp.services.UserService;

@RestController
@RequestMapping("/users/v1")
public class UsersController {


    @PutMapping("/updateUsers")
    public ResponseEntity<?> updateUsers(@RequestBody UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User users = UserMapper.toEntity(userDTO);
        User userIdDb = userService.findByUserName(userName);
        userIdDb.setUserName(users.getUserName());
        userIdDb.setPassword(users.getPassword());
        userIdDb.setRoles(users.getRoles());
        userService.saveNewUsers(userIdDb);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Autowired
    private UserService userService;

}
