package com.prince.journalApp.mapper;

import com.prince.journalApp.dto.UserDTO;
import com.prince.journalApp.entities.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUserName(), user.getPassword(), user.getRoles(), user.getJournalEntries());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setJournalEntries(userDTO.getJournalEntries());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        return user;
    }



}
