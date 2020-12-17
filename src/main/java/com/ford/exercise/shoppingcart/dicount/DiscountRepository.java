package com.ford.exercise.shoppingcart.dicount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountRepository {

    private List<Discount> discounts;

    public DiscountRepository() {
        discounts = new ArrayList<>();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }


    public void add(BuyXQuantityGetYPercentOffDiscountCalculator discount) {
        discounts.add(discount);
    }

    /**
     *
     * @param key
     * @return
     */
    public List<Discount> find(String key) {
        return discounts.stream()
                .filter(discount -> key.equals(discount.getReward().getProduct()))
                .collect(Collectors.toList());
    }
}
