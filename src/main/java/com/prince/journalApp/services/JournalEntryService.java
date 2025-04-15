package com.prince.journalApp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prince.journalApp.entities.JournalEntry;
import com.prince.journalApp.entities.User;
import com.prince.journalApp.repo.JournalEntryRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JournalEntryService {

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUsers(user);
        } catch (Exception e) {
            log.error("Exception occured for {}", userName,  e);
            throw new RuntimeException("An error occured while saving journal for user: "+userName);
        }
        
    }

    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllJournals(String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        return journalEntries;
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public boolean deleteJournalEntryById(String userName, ObjectId id) {
        User user = userService.findByUserName(userName);
        if(user.getJournalEntries().removeIf(u -> u.getId().equals(id))) {
            userService.saveUsers(user);
            journalEntryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

}
