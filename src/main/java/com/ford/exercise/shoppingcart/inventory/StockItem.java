package com.ford.exercise.shoppingcart.inventory;


public class StockItem {

    private String product;

    public String unit;

    private Double cost;

    public StockItem() {
    }

    public StockItem(String product, String unit, Double cost) {
        this.product = product;
        this.unit = unit;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "product='" + product + '\'' +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                '}';
    }
}
