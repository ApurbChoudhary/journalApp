package com.prince.journalApp.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prince.journalApp.entities.User;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);

}
