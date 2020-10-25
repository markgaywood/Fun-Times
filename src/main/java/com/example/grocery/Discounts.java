package com.example.grocery;

import com.example.grocery.service.TimeService;
import com.example.grocery.vo.BasketItem;
import com.example.grocery.vo.Discount;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.Math.min;
import static java.math.RoundingMode.HALF_DOWN;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component
@Log
public class Discounts {

    public static Discount soupToBread(TimeService timeService) {
        LocalDate today = timeService.today();
        LocalDate yesterday = today.minus(1, DAYS);
        return Discount.builder()
                .validFrom(yesterday)
                .validTo(yesterday.plus(7, DAYS))
                .calculate(basket -> {
                    BigDecimal result = BigDecimal.ZERO;
                    int soupPairs = Optional.ofNullable(basket.get("soup"))
                            .map(BasketItem::getQuantity)
                            .map(q -> q / 2)
                            .orElse(0);
                    BasketItem bread = basket.get("bread");
                    if (soupPairs > 0 && bread != null) {
                        result = bread.getProduct().getBasePrice()
                                .divide(new BigDecimal(2), HALF_DOWN)
                                .multiply(new BigDecimal(min(soupPairs, bread.getQuantity())));
                        log.info("Applied discount 'Buy 2 tins of soup and get a loaf of bread half price': " + result);
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
                .calculate(basket -> {
                    BigDecimal result = Optional.ofNullable(basket.get("apples"))
                                    .map(item -> item.getProduct().getBasePrice().multiply(new BigDecimal(item.getQuantity())))
                                    .map(value -> value.divide(new BigDecimal(10), HALF_DOWN))
                                    .orElse(BigDecimal.ZERO);
                    if (result.signum() > 0) {
                        log.info("Applied discount 'Apples have a 10% discount': " + result);
                    }
                    return result;
                })
                .build();

    }
}
