package steps;

import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import pages.ResultsPage;

public class ThenSteps {

    @Steps
    ResultsPage resultsPage;

    SoftAssertions softAssertions = new SoftAssertions();

    @Then("User is redirected to results page")
    public void userIsRedirectedToResultsPage() {
        softAssertions.assertThat(resultsPage.getResultPageUrl()).contains("/results");
    }

    @Then("The selected origin and destination are displayed correctly")
    public void theSelectedOriginAndDestinationAreDisplayedCorrectly() {
        softAssertions.assertThat(resultsPage.getOrigin()).contains(Serenity.getCurrentSession().get("ORIGIN").toString());
        softAssertions.assertThat(resultsPage.getDestination()).contains(Serenity.getCurrentSession().get("DESTINATION").toString());
    }

    @Then("All filters are displayed correctly")
    public void allFiltersAreDisplayedCorrectly() {
        softAssertions.assertThat(resultsPage.allFiltersAreDisplayedCorrectly()).isTrue();
    }

    @Then("^Results displayed contain flights with (.*) stops only$")
    public void resultsDisplayedContainFlightsWithStopsOnly(String stops) {
        softAssertions.assertThat(resultsPage.numberOfStopsFilterAppliedCorrectly(stops)).isTrue();
    }

    @Then("^Results displayed contain flights with (.*) price only$")
    public void resultsDisplayedContainFlightsWithPricesOnly(String price) {
        switch (price){
            case "increased lowest":
                softAssertions.assertThat(resultsPage.getInitialLowestPrice()).isLessThan(resultsPage.getNewLowestPrice());
                softAssertions.assertThat(resultsPage.getCheapestButtonPrice()).isGreaterThanOrEqualTo(resultsPage.getNewLowestPrice());
                break;
            case "decreased highest":
                softAssertions.assertThat(resultsPage.getInitialHighestPrice()).isLessThanOrEqualTo(resultsPage.getNewHighestPrice());
                break;
        }
    }

    @Then("^Results displayed contain flights with (.*) airlines$")
    public void resultsDisplayedContainFlightsWithAirlinesAirlines(String airlines) {
        softAssertions.assertThat(resultsPage.airlinesRequestedDisplayedCorrectly(airlines)).isTrue();
    }

    @Then("^Results displayed do not contain flights without (.*) airlines$")
    public void resultsDisplayedDoNotContainFlightsWithoutAirlinesAirlines(String airlines) {
        softAssertions.assertThat(resultsPage.airlinesNotRequestedAreNotDisplayed(airlines)).isFalse();
    }

    @Then("^Applied filters do not contain (.*)$")
    public void appliedFiltersDoNotContainFilter(String filter) {
        softAssertions.assertThat(resultsPage.appliedFiltersDoNotContainFilter(filter)).isTrue();
    }

    @Then("^Applied filters contain (.*)")
    public void appliedFiltersContainAirlines(String filter) {
        softAssertions.assertThat(resultsPage.appliedFiltersContainFilter(filter)).isTrue();
    }
}
