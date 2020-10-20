package com.pricing.timing;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class ForNDays extends ValidFrom {
    public static ForNDays forNDays(Integer n) {
        ZonedDateTime from = ZonedDateTime.now();

        return forNDays(from, n);
    }

    private static ForNDays forNDays(ZonedDateTime from, Integer n) {
        return new ForNDays(from, from.plusDays(n));
    }

    ZonedDateTime end;

    private ForNDays(ZonedDateTime starts, ZonedDateTime end) {
        super(starts);

        this.end = end;
    }

    public ZonedDateTime getStart() { return getNow(); }
}
