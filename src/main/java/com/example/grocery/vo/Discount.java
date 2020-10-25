package com.example.grocery.vo;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

@Builder
public class Discount {

    @Getter
    private final LocalDate validFrom;
    @Getter
    private final LocalDate validTo;

    public final Function<Map<String, BasketItem>, BigDecimal> calculate;
}
