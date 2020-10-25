package com.example.grocery;

import com.example.grocery.io.InputOutput;
import com.example.grocery.service.BasketService;
import com.example.grocery.service.ProductLookupService;
import com.example.grocery.vo.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.example.grocery.vo.UnitOfMeasure.SINGLE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ShopTest {

    private InputOutput io;
    private BasketService basketService;
    private ProductLookupService productLookupService;

    private Shop shop;

    @Before
    public void setup() {
        io = mock(InputOutput.class);
        basketService = mock(BasketService.class);
        productLookupService = mock(ProductLookupService.class);
        shop = new Shop(io, basketService, productLookupService);
    }

    @Test(timeout=5000)
    public void shouldAddProductsToBasket() throws Exception {
        // given
        Product things = new Product("things", SINGLE, BigDecimal.TEN);
        Product stuff = new Product("stuff", SINGLE, BigDecimal.TEN);

        when(productLookupService.lookup("things")).thenReturn(things);
        when(productLookupService.lookup("stuff")).thenReturn(stuff);

        when(io.readLine(anyString(), any()))
                .thenReturn("buy 5 things")
                .thenReturn("buy 1 stuff")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(basketService).addItem(5, things);
        verify(io).printf("Added %d %s to basket%n", 5, "things");
        verify(basketService).addItem(1, stuff);
        verify(io).printf("Added %d %s to basket%n", 1, "stuff");
    }

    @Test(timeout=5000)
    public void shouldTotalPurchases() throws Exception {
        // given
        when(basketService.priceUp()).thenReturn(BigDecimal.ZERO);
        when(io.readLine(anyString(), any()))
                .thenReturn("total")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("Total purchases: %s%n", "0.00");
    }

    @Test(timeout=5000)
    public void shouldQuit() throws Exception {
        // given
        when(io.readLine(anyString(), any())).thenReturn("quit");

        // when
        shop.serve();

        // no expectation except return
    }

    @Test(timeout=5000)
    public void shouldQuit_whenCommanded_inAnyCase() throws Exception {
        // given
        when(io.readLine(anyString(), any())).thenReturn("QUIT");

        // when
        shop.serve();

        // no expectation except return
    }

    @Test(timeout=5000)
    public void shouldShowMessage_whenUnknownCommand() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("shop!")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("%s%nYou can enter these commands:%n", "I don't understand 'shop!'");
    }

    @Test(timeout=5000)
    public void shouldShowMessage_whenIncompleteCommand() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("buy 77")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("%s%nYou can enter these commands:%n", "Expected more input");
    }

    @Test(timeout=5000)
    public void shouldShowMessage_whenNoCommand() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("%s%nYou can enter these commands:%n", "Expected more input");
    }

    @Test(timeout=5000)
    public void shouldShowMessage_whenQuantityNotNumeric() throws Exception {
        // given
        when(io.readLine(anyString(), any()))
                .thenReturn("buy apples and pears")
                .thenReturn("quit");

        // when
        shop.serve();

        // then
        verify(io).printf("%s%nYou can enter these commands:%n", "Expected a number");
    }

}
