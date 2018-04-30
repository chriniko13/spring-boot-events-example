package com.chriniko.example.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable

@EqualsAndHashCode
@ToString

public class Numbers {

    private static String DELIM = ",";

    private String choice;

    public Numbers() {
    }

    public Numbers(List<String> numbers) {
        choice = numbers.stream().collect(Collectors.joining(DELIM));
    }

    public List<String> getChoice() {
        return Arrays.asList(choice.split(DELIM));
    }

    public void setChoice(List<String> numbers) {
        this.choice = choice = numbers.stream().collect(Collectors.joining(DELIM));
    }
}
