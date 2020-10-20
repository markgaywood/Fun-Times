package com.pricing.domain;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class InNDaysTime extends When {
    public static InNDaysTime inNDays(Integer n) {
        return new InNDaysTime(n);
    }

    Integer n;

    private InNDaysTime(Integer n) {
        super(ZonedDateTime.now().plusDays(n));

        this.n = n;
    }
}
