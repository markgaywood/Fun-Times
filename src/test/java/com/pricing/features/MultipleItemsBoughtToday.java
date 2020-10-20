package com.pricing.features;

import com.pricing.domain.Basket;
import com.pricing.domain.OrderedItem;
import com.pricing.domain.Pence;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.pricing.domain.Basket.basket;
import static com.pricing.domain.Bread.bread;
import static com.pricing.domain.BasketTotalCostCalculation.BasketTotalCostCalculator.calculateBasketTotalCost;
import static com.pricing.domain.OrderedItem.orderedItem;
import static com.pricing.domain.Pence.pence;
import static com.pricing.domain.Soup.soup;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class MultipleItemsBoughtToday {
    private LocalDate timeFrame;
    private Optional<OrderedItem> tinsOfSoup;
    private Optional<OrderedItem> loavesOfBread;
    private Basket basket;

    @Given("the time frame is today")
    public void the_time_frame_is_today() { timeFrame = LocalDate.now(); }

    @And("{int} tins of soup")
    public void tins_of_soup(Integer quantity) {
        tinsOfSoup = orderedItem(soup(), quantity);
    }

    @And("{int} loaves of bread")
    public void loaves_of_bread(Integer quantity) {
        loavesOfBread = orderedItem(bread(), quantity);
    }

    @When("the price of the basket of items is calculated")
    public void the_price_of_the_basket_of_items_is_calculated() {
        Set<OrderedItem> orderedItems = Stream.of(tinsOfSoup, loavesOfBread)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());

        basket = basket(orderedItems);
    }

    @Then("the total cost is {int} pounds and {int} pence")
    public void the_total_cost_is_pounds_and_pence(Integer pounds, Integer pence) {
        Pence expected = pence(pounds, pence);

        Pence actual = calculateBasketTotalCost(basket);

        assertThat(actual).isEqualTo(expected);
    }
}
