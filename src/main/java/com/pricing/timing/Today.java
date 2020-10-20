package com.pricing.timing;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class Today extends When {
    public static Today today() { return new Today(); }

    private Today() {
        super(ZonedDateTime.now());
    }
}
