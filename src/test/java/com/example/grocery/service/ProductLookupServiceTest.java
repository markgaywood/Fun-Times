package com.example.grocery.service;


import com.example.grocery.vo.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.example.grocery.vo.UnitOfMeasure.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductLookupServiceTest {

    private ProductLookupService productLookupService;

    @Before
    public void setup() {
        productLookupService = new ProductLookupService();
    }

    @Test
    public void shouldReturnProduct_whenVaryingCase() {
        // given

        // when
        Product soup = productLookupService.lookup("soup");
        Product bread = productLookupService.lookup("Bread");
        Product milk = productLookupService.lookup("MILK");
        Product apples = productLookupService.lookup("aPpLeS");

        // then
        assertThat(soup.getName()).isEqualTo("soup");
        assertThat(bread.getName()).isEqualTo("bread");
        assertThat(milk.getName()).isEqualTo("milk");
        assertThat(apples.getName()).isEqualTo("apples");
    }

    @Test
    public void shouldReturnAllProductNames() {
        // given

        // when
        Set<String> names = productLookupService.getNames();

        // then
        assertThat(names).containsExactlyInAnyOrder("soup", "bread", "milk", "apples");
    }
}
