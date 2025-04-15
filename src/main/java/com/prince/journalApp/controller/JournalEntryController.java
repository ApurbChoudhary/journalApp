package com.prince.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prince.journalApp.entities.JournalEntry;



@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/get-all-entries")
    public List<JournalEntry> getAllJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }
   
    @GetMapping("getJournalById/{id}")
    public JournalEntry getJournalById(@PathVariable long id) {
        return journalEntries.get(id);
    }

    @PostMapping("/add-entry")
    public boolean addJournalEntries(@RequestBody JournalEntry journalEntity) {
        journalEntries.put(journalEntity.getId(), journalEntity);
        return true;
    }
    

    @DeleteMapping("deleteJournalEntrybyId/{id}")
    public JournalEntry deleteJournalById(@PathVariable long id) {
        return journalEntries.remove(id);
    }

    @PutMapping("updateJournalEntrybyId/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntity) {
        return journalEntries.put(id, journalEntity);
    }

}
