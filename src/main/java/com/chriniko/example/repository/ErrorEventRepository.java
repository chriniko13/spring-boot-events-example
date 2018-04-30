package com.chriniko.example.repository;

import com.chriniko.example.domain.ErrorEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorEventRepository extends MongoRepository<ErrorEvent, String> {
}
