package com.ford.exercise.shoppingcart.cart;

import com.ford.exercise.shoppingcart.inventory.StockItem;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShoppingCartItem {

    private StockItem stockItem;

    private Money cost;

    private Integer quantity;

    private LocalDate purchaseDate;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(StockItem stockItem, Integer quantity) {
        this.stockItem = stockItem;
        this.quantity = quantity;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "stockItem=" + stockItem +
                ", quantity=" + quantity +
                '}';
    }

    public Money getCost() {
        return cost;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }


    public Money getTotalCost() {

        return cost.multiply(quantity);
    }



}
