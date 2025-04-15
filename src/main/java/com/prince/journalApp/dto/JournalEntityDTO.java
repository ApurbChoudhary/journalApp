package com.prince.journalApp.dto;

import java.time.LocalDate;

import org.bson.types.ObjectId;

public class JournalEntityDTO {

    private ObjectId id;
    private String title;
    private String content;
    private LocalDate date;
    public JournalEntityDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
