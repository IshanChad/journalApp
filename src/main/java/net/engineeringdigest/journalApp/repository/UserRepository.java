package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//MongoRepository provides us basic CRUD operations facility and we are providing it entity type and it data type of primary key there (id)
public interface UserRepository extends MongoRepository<User, ObjectId> {
    //By default, MongoRepository automatically provides you with basic CRUD (Create, Read, Update, Delete) operations, but only for the primary key.
    //However, if you need to perform operations based on other fields (like username, email, etc.), you need to define custom methods in the repository interface.
    User findByUserName(String username);

    void deleteByUserName(String username);
}
