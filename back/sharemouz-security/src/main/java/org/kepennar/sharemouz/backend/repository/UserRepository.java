package org.kepennar.sharemouz.backend.repository;

import org.kepennar.sharemouz.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
	User findByEmail(String email);
}
