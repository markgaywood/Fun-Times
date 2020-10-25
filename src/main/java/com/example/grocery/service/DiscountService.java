package com.example.grocery.service;

import com.example.grocery.Discounts;
import com.example.grocery.vo.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DiscountService {

    private final List<Discount> discountList = new ArrayList<>();
    private final TimeService timeService;

    @Autowired
    public DiscountService(TimeService timeService) {
        this.timeService = timeService;

        discountList.add(Discounts.soupToBread(timeService));
        discountList.add(Discounts.applesTenPercent(timeService));
    }

    public Stream<Discount> getCurrent() {
        final LocalDate today = timeService.today();
        return discountList.stream().filter(
                d -> !d.getValidFrom().isAfter(today) && !d.getValidTo().isBefore(today));
    }

}
