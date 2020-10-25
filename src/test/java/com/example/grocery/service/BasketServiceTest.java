package com.example.grocery.service;


import com.example.grocery.vo.Discount;
import com.example.grocery.vo.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static com.example.grocery.vo.UnitOfMeasure.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketServiceTest {

    private static final LocalDate OCT_23 = LocalDate.of(2020, Month.OCTOBER, 23);

    private DiscountService discountService;
    private BasketService basketService;

    @Before
    public void setup() {
        discountService = mock(DiscountService.class);
        when(discountService.getCurrent()).thenReturn(Stream.empty());

        basketService = new BasketService(discountService);
    }

    @Test
    public void shouldReturnZero_whenNothingAdded() {
        // given

        // when
        BigDecimal total = basketService.priceUp();
        // then
        assertThat(total).isZero();
    }

    @Test
    public void shouldAccumulateQuantity_overAllProducts() {
        // given
        basketService.addItem(1, new Product("Alpha", TIN, new BigDecimal("12.34")));
        basketService.addItem(2, new Product("Beta", SINGLE, new BigDecimal("0.56")));
        basketService.addItem(1, new Product("Gamma", LOAF, new BigDecimal("7.89")));
        basketService.addItem(1, new Product("Delta", BOTTLE, new BigDecimal("1.23")));

        // when
        BigDecimal total = basketService.priceUp();

        // then
        assertThat(total).isEqualTo(new BigDecimal("22.58"));
    }

    @Test
    public void shouldSubtractDiscounts() {
        // given
        basketService.addItem(1, new Product("Alpha", TIN, new BigDecimal("12.34")));
        basketService.addItem(2, new Product("Beta", SINGLE, new BigDecimal("0.56")));
        basketService.addItem(1, new Product("Gamma", LOAF, new BigDecimal("7.89")));
        basketService.addItem(1, new Product("Delta", BOTTLE, new BigDecimal("1.23")));

        Discount twentyPence = Discount.builder().calculate(map -> new BigDecimal("0.20")).build();
        Discount onePound = Discount.builder().calculate(map -> new BigDecimal("1.00")).build();
        when(discountService.getCurrent()).thenReturn(Stream.of(twentyPence, onePound));

        // when
        BigDecimal total = basketService.priceUp();

        // then
        assertThat(total).isEqualTo(new BigDecimal("21.38"));
    }

    @Test
    public void shouldAccumulateQuantity_whenSameProductAdded() {
        // given
        Product product = new Product("Expensive", TIN, new BigDecimal("12.34"));
        basketService.addItem(1, product);
        basketService.addItem(2, product);
        basketService.addItem(3, product);
        basketService.addItem(4, product);

        // when
        BigDecimal total = basketService.priceUp();

        // then
        assertThat(total).isEqualTo(new BigDecimal("123.40"));
    }
}
