package com.ford.exercise.shoppingcart.dicount;

import java.time.LocalDate;

abstract public class Discount implements DiscountCalculator {

    private LocalDate validFrom;

    private LocalDate validTo;

    private DiscountCondition condition;

    private DiscountReward reward;


    public DiscountCondition getCondition() {
        return condition;
    }

    public void setCondition(DiscountCondition condition) {
        this.condition = condition;
    }

    public DiscountReward getReward() {
        return reward;
    }

    public void setReward(DiscountReward reward) {
        this.reward = reward;
    }


    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }



}
