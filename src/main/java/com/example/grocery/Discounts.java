package com.example.grocery;

import com.example.grocery.service.TimeService;
import com.example.grocery.vo.BasketItem;
import com.example.grocery.vo.Discount;
import com.example.grocery.vo.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static java.math.RoundingMode.HALF_DOWN;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component
public class Discounts {

    public static Discount soupToBread(TimeService timeService) {
        LocalDate today = timeService.today();
        LocalDate yesterday = today.minus(1, DAYS);
        return Discount.builder()
                .validFrom(yesterday)
                .validTo(yesterday.plus(7, DAYS))
                .calculate(basket -> {
                    BigDecimal result = BigDecimal.ZERO;
                    boolean qualified = Optional.ofNullable(basket.get("soup"))
                            .map(BasketItem::getQuantity)
                            .map(q -> q >= 2)
                            .orElse(false);
                    if (qualified) {
                        result = Optional.ofNullable(basket.get("bread"))
                                .filter(item -> item.getQuantity() > 0)
                                .map(BasketItem::getProduct)
                                .map(Product::getBasePrice)
                                .map(price -> price.divide(new BigDecimal(2), HALF_DOWN))
                                .orElse(BigDecimal.ZERO);
                    }
                    return result;
                })
                .build();
    }

    public static Discount applesTenPercent(TimeService timeService) {
        LocalDate today = timeService.today();
        LocalDate threeDaysHence = today.plus(3, DAYS);
        LocalDate endOfFollowingMonth = threeDaysHence.with(firstDayOfNextMonth()).with(lastDayOfMonth());
        return Discount.builder()
                .validFrom(threeDaysHence)
                .validTo(endOfFollowingMonth)
                .calculate(basket ->
                        Optional.ofNullable(basket.get("apples"))
                                .map(item -> item.getProduct().getBasePrice().multiply(new BigDecimal(item.getQuantity())))
                                .map(value -> value.divide(new BigDecimal(10), HALF_DOWN))
                                .orElse(BigDecimal.ZERO)
                )
                .build();

    }
}
