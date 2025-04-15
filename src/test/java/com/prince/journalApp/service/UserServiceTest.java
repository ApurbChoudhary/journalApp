package com.prince.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prince.journalApp.repo.UserRepo;

@SpringBootTest
@Disabled
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUserName() {
        assertNotNull(userRepo.findByUserName( "Game"));
    }

}
