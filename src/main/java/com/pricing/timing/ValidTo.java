package com.pricing.timing;

import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
public abstract class ValidTo extends When {
    protected ValidTo(ZonedDateTime now) {
        super(now);
    }
}
