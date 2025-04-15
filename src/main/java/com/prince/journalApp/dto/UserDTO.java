package com.prince.journalApp.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.prince.journalApp.entities.JournalEntry;

public class UserDTO {

    private ObjectId id;
    private String userName;
    private String password;
    private List<String> roles;
    private List<JournalEntry> journalEntries = new ArrayList<>();
    public UserDTO(String userName, String password, List<String> roles, List<JournalEntry> journalEntries) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.journalEntries = journalEntries;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }
    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    
    

}
