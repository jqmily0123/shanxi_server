package com.shanxi.water.mongoRepository;

import com.shanxi.water.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
