package com.pricing;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

public class StepDefinitions {
    private LocalDate timeFrame;

    @Given("the time frame is today")
    public void the_time_frame_is_today() { timeFrame = LocalDate.now(); }

    @And("{int} tins of soup")
    public void tins_of_soup(Integer quantity) {}

    @And("{int} loaves of bread")
    public void loaves_of_bread(Integer quantity) {}

    @When("the price of the basket of items is calculated")
    public void the_price_of_the_basket_of_items_is_calculated() {}

    @Then("the total cost is {int} pounds and {int} pence")
    public void the_total_cost_is_pounds_and_pence(Integer pounds, Integer pence) {
        throw new PendingException("Total cost of basket to be asserted!");
    }
}
