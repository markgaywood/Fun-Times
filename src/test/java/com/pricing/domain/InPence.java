package com.pricing.domain;

public interface InPence {
    default Pence pence(Integer value) { return Pence.builder().value(value).build(); }
}
