package com.pricing.timing;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@EqualsAndHashCode
public abstract class When {
    private final ZonedDateTime now;

    protected When(ZonedDateTime now) {
        this.now = now;
    }
}
