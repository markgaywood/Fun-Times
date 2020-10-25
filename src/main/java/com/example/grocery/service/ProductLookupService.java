package com.example.grocery.service;

import com.example.grocery.vo.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import static com.example.grocery.vo.UnitOfMeasure.*;

@Service
public class ProductLookupService {

    private final Map<String, Product> products = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ProductLookupService() {
        Stream.of(
                new Product("soup", TIN, new BigDecimal("0.65")),
                new Product("bread", LOAF, new BigDecimal("0.80")),
                new Product("milk", BOTTLE, new BigDecimal("1.30")),
                new Product("apples", SINGLE, new BigDecimal("0.10")))
            .forEach(p -> products.put(p.getName(), p));

    }

    public Product lookup(String name) {
        return products.get(name);
    }

    public Set<String> getNames() {
        return products.keySet();
    }
}
