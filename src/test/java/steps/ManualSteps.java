package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.model.TestResult;

public class ManualSteps {

    @Given("User starts scheduling a flight at the flight network webpage")
    @Manual(result = TestResult.SUCCESS)
    public void userStartsSchedulingAFlightAtTheFlightNetworkWebpage() {
    }

    @When("^User selects (.*) as tripType$")
    @Manual(result = TestResult.SUCCESS)
    public void userSelectsTripAsTripType(String trip) {
    }

    @And("User inputs  as origin Athens")
    @Manual(result = TestResult.SUCCESS)
    public void userInputsAsOriginAthens() {

    }

    @And("User inputs as destination Amsterdam")
    @Manual(result = TestResult.SUCCESS)
    public void userInputsAsDestinationAmsterdam() {

    }

    @And("^User selects as departure date (.*)$")
    @Manual(result = TestResult.SUCCESS)
    public void userSelectsAsDepartureDate(String date) {

    }

    @And("^User selects as return date (.*)$")
    @Manual(result = TestResult.SUCCESS)
    public void userSelectsAsReturnDate(String date) {

    }

    @And("User presses Search flights button to get results")
    @Manual(result = TestResult.SUCCESS)
    public void userPressesSearchFlightsButtonToGetResults() {

    }

    @Then("The browser redirects the user to results page")
    @Manual(result = TestResult.SUCCESS)
    public void theBrowserRedirectsTheUserToResultsPage() {

    }

    @And("The origin and destination are displayed correctly")
    @Manual(result = TestResult.SUCCESS)
    public void theOriginAndDestinationAreDisplayedCorrectly() {

    }

    @When("User expands the filter section")
    @Manual(result = TestResult.SUCCESS)
    public void userExpandsTheFilterSection() {

    }

    @Then("All the filters are displayed correctly")
    @Manual(result = TestResult.SUCCESS)
    public void allTheFiltersAreDisplayedCorrectly() {
    }
}
