package com.example.grocery.service;

import com.example.grocery.vo.BasketItem;
import com.example.grocery.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

@Service
public class BasketService {

    private final Map<String, BasketItem> basketItems = new TreeMap<>(CASE_INSENSITIVE_ORDER);
    private final DiscountService discountService;

    @Autowired
    public BasketService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public BigDecimal priceUp() {
        BigDecimal total = basketItems.values().stream()
            .map(item -> item.getProduct().getBasePrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = discountService.getCurrent()
            .map(d -> d.calculate.apply(basketItems))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.subtract(discount);
    }

    public void addItem(final int quantity, final Product product) {
        BasketItem item = basketItems.get(product.getName());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            basketItems.put(product.getName(), new BasketItem(product, quantity));
        }
    }

    public void clear() {
        basketItems.clear();
    }
}
