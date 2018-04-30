package com.chriniko.example.repository;

import com.chriniko.example.domain.PlayedTicketEvent;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketPlayedEventRepository extends MongoRepository<PlayedTicketEvent, String> {

}
