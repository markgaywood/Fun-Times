package com.example.grocery;

import com.example.grocery.io.InputOutput;
import com.example.grocery.service.BasketService;
import com.example.grocery.service.DiscountService;
import com.example.grocery.service.ProductLookupService;
import com.example.grocery.service.TimeService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.mockito.Mockito.*;

public class ShopIntegrationTest {

    private static final LocalDate TODAY = LocalDate.of(2020, Month.OCTOBER, 20);
    private static final LocalDate FIVE_DAYS_TIME = TODAY.plus(5, DAYS);

    private InputOutput io;
    private TimeService timeService;

    private Shop shop;

    @Before
    public void setup() {
        io = mock(InputOutput.class);
        timeService = mock(TimeService.class);
        when(timeService.today()).thenReturn(TODAY);

        DiscountService discountService = new DiscountService(timeService);
        BasketService basketService = new BasketService(discountService);
        ProductLookupService productLookupService = new ProductLookupService();

        shop = new Shop(io, basketService, productLookupService);
    }

    @Test(timeout=5000)
    public void shouldDiscount_3soup_2bread_today_by_40p() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 3 soup")
                .thenReturn("buy 2 bread")
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "3.15");
    }

    @Test(timeout=5000)
    public void shouldDiscount_6soup_2bread_today_by_80p() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 6 soup")
                .thenReturn("buy 2 bread")
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "4.70");
    }

    @Test(timeout=5000)
    public void shouldDiscount_6apples_1milk_today() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 6 apples")
                .thenReturn("buy 1 milk")
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "1.90");
    }

    @Test(timeout=5000)
    public void shouldDiscount_6apples_1milk_5daysTime_by_10percent() throws Exception {
        // given
        when(timeService.today()).thenReturn(FIVE_DAYS_TIME);
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 6 apples")
                .thenReturn("buy 1 milk")
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "1.84");
    }

    @Test(timeout=5000)
    public void shouldDiscount_3apples_2soup_1bread_5daysTime() throws Exception {
        // given
        when(timeService.today()).thenReturn(FIVE_DAYS_TIME);
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 3 apples")
                .thenReturn("buy 2 soup")
                .thenReturn("buy 1 bread")
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "1.97");
    }

}
