package com.prince.journalApp.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prince.journalApp.entities.JournalEntry;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
