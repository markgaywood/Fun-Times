package com.pricing.timing;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
public class Yesterday extends ValidFrom {
    public static Yesterday yesterday() { return new Yesterday(); }

    private Yesterday() {
        super(ZonedDateTime.now().minusDays(1));
    }
}
