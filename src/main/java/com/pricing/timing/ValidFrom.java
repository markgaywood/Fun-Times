package com.pricing.timing;

import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
public abstract class ValidFrom extends When {
    protected ValidFrom(ZonedDateTime value) {
        super(value);
    }
}
