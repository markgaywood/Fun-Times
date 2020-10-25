package com.example.grocery.service;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

@Service
public class TimeService {

    private Clock clock = Clock.systemDefaultZone();

    public LocalDate today() {
        return LocalDate.now(clock);
    }

}
