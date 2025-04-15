package com.prince.journalApp.mapper;

import java.time.LocalDate;

import com.prince.journalApp.dto.JournalEntityDTO;
import com.prince.journalApp.entities.JournalEntry;

public class JournalEntryMapper {

    public static JournalEntityDTO toJournalEntityDTO(JournalEntry journalEntry) {
        return new JournalEntityDTO(journalEntry.getTitle(), journalEntry.getContent());
    }
    public static JournalEntry toEntity(JournalEntityDTO journalEntityDTO) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle(journalEntityDTO.getTitle());
        journalEntry.setContent(journalEntityDTO.getContent());
        journalEntry.setDate(LocalDate.now());
        return journalEntry;
    }

}
