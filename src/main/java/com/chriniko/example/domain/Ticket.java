package com.chriniko.example.domain;


import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tickets")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@EqualsAndHashCode
@ToString

public class Ticket {

    @Id
    private String id;

    @Embedded
    private Numbers numbers;
}
