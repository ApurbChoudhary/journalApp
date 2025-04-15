package com.prince.journalApp.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.prince.journalApp.entities.User;
import com.prince.journalApp.repo.UserRepo;

@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(Objects.nonNull(user)) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User
            .builder().username(user.getUserName())
            .password(user.getPassword())
            .roles(user.getRoles().toArray(new String[0]))
            .build();
            return userDetails;
        }
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
