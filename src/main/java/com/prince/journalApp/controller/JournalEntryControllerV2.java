package com.prince.journalApp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.ListUtils;

import com.prince.journalApp.dto.JournalEntityDTO;
import com.prince.journalApp.entities.JournalEntry;
import com.prince.journalApp.entities.User;
import com.prince.journalApp.mapper.JournalEntryMapper;
import com.prince.journalApp.services.JournalEntryService;
import com.prince.journalApp.services.UserService;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {

    @GetMapping("/getAllJournals")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> userJournalEntries = user.getJournalEntries();
        if (userJournalEntries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(userJournalEntries);
        }
    }

    @PostMapping("/addjournals")
    public ResponseEntity<JournalEntry> createJournalsEntry(@RequestBody JournalEntityDTO journalEntityDTO) {
        System.out.println("Creating journal entry: " + journalEntityDTO);
        JournalEntry journalEntry = JournalEntryMapper.toEntity(journalEntityDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (Objects.isNull(journalEntry)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            entryService.saveJournalEntry(journalEntry, userName);
            return ResponseEntity.status(HttpStatus.CREATED).body(journalEntry);
        }
    }

    @GetMapping("/getJournalEntryById/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream()
                .filter(je -> je.getId().equals(id))
                .collect(Collectors.toList());
        if (!ListUtils.isEmpty(journalEntries)) {
            Optional<JournalEntry> journalEntryOptional = entryService.getJournalEntryById(id);
            if (journalEntryOptional.isPresent()) {
                return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteJournalEntryById/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return entryService.deleteJournalEntryById(userName, id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("updateJournalEntry/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntityDTO journalEntityDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        JournalEntry journalEntry = JournalEntryMapper.toEntity(journalEntityDTO);
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream()
                .filter(je -> je.getId().equals(id))
                .collect(Collectors.toList());
        if (!ListUtils.isEmpty(journalEntries)) {
            Optional<JournalEntry> journalEntryOptional = entryService.getJournalEntryById(id);
            if (journalEntryOptional.isPresent()) {
                JournalEntry existingJournalEntry = journalEntryOptional.get();
                    existingJournalEntry.setTitle(
                            journalEntry.getTitle() != null ? journalEntry.getTitle() : existingJournalEntry.getTitle());
                    existingJournalEntry.setContent(
                            journalEntry.getContent() != null ? journalEntry.getContent() : existingJournalEntry.getContent());
                    existingJournalEntry.setDate(LocalDate.now());
                entryService.saveJournalEntry(existingJournalEntry);
                return ResponseEntity.ok(existingJournalEntry);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Autowired
    private JournalEntryService entryService;

    @Autowired
    private UserService userService;

}
