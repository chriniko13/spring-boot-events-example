package com.chriniko.example.resource;

import com.chriniko.example.domain.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketResource ticketResource;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
        assertThat(ticketResource).isNotNull();
    }

    @Test
    public void findAll() throws IOException {

        //when
        String result = restTemplate.getForObject(
                "http://localhost:" + port + "/tickets",
                String.class
        );

        List<Ticket> tickets = objectMapper.readValue(result, new TypeReference<List<Ticket>>() {
        });

        //then
        assertThat(tickets.size()).isEqualTo(10);


    }
}