package com.ford.exercise.shoppingcart.dicount;

public class DiscountReward {

    private String product;

    private Integer quantity;

    private Double percent;

    public DiscountReward() {
    }

    public DiscountReward(String product, Integer quantity, Double percent) {
        this.product = product;
        this.quantity = quantity;
        this.percent = percent;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DiscountReward{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", percent=" + percent +
                '}';
    }
}

