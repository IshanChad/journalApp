package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//MongoRepository provides us basic CRUD operations facility and we are providing it entity type and it data type of primary key there (id)
public interface JournalEntryRepository  extends MongoRepository<JournalEntry, ObjectId> {
}
