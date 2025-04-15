package com.prince.journalApp.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prince.journalApp.entities.User;
import com.prince.journalApp.repo.UserRepo;

@Service
public class UserService {

    public void saveUsers(User users) {
        userRepo.save(users);
    }

    public void saveNewUsers(User users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepo.save(users);
    }

    public List<User> getallUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepo.findById(id);
    }

    public Boolean deleteUserByIdBoolean(ObjectId id) {
        userRepo.deleteById(id);
        return true;
    }

    public Boolean deleteByUserName(String userName) {
        userRepo.deleteByUserName(userName);
        return true;
    }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

}
