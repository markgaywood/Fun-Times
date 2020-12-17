package com.ford.exercise.shoppingcart.dicount;

public class DiscountCondition {

    private String product;

    private Integer quantity;

    public DiscountCondition() {
    }


    public DiscountCondition(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DiscountCondition{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
