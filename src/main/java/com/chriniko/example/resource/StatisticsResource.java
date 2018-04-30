package com.chriniko.example.resource;

import com.chriniko.example.service.StatisticsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsResource {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode ticketResourceStatistics() {
        long totalHits = statisticsService.getTicketResourceCounter();
        return objectMapper.createObjectNode().put("ticket-resource-total-hits", totalHits);
    }
}
