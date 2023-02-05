package steps;

import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import pages.ResultsPage;
import pages.StartPage;

public class WhenSteps {

    @Steps
    StartPage startPage;

    @Steps
    ResultsPage resultsPage;

    EnvironmentVariables environmentVariables;


    @When("^User navigates to (.*) webpage")
    public void userNavigatesToUrl(String url) {
        if(url.contains("flight-network")){
            startPage.openUrl(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.baseurl"));
        }
        else{
            startPage.openUrl(url);
        }
    }

    @When("^User inputs (.*) as origin$")
    public void userInputsAirportAsOrigin(String airport) throws InterruptedException {
        startPage.typeOrigin(airport);
    }

    @When("^User inputs (.*) as destination")
    public void userInputsAirportAsDestination(String airport) throws InterruptedException {
        startPage.typeDestination(airport);
    }

    @When("User presses Search flights button")
    public void userPressesSearchFlightsButton(){
        startPage.pressSearchFlightsButton();
        resultsPage.waitForResults();
    }

    @When("^User selects (.*) as departure date$")
    public void userSelectsDateAsDepartureDate(String date) throws InterruptedException {
        startPage.selectDepartureDate(date);
    }

    @When("^User selects (.*) as return date$")
    public void userSelectsDateAsReturnDate(String date) throws InterruptedException {
        String tripType = Serenity.getCurrentSession().get("TRIP_TYPE").toString();
        if (!tripType.contains("One-way")){
            startPage.selectReturnDate(date);
        }
    }

    @When("^User selects (.*) trip type$")
    public void userSelectsTripTripType(String trip) {
        Serenity.getCurrentSession().put("TRIP_TYPE", trip);
        switch (trip){
            case "Return":
                startPage.clickReturnRadioButton();
                break;
            case "One-way":
                startPage.clickOneWayRadioButton();
                break;
            case "Multi-city":
//                startPage.clickMultiCityRadioButton();
                break;
        }
    }

    @When("User selects to expand the filter section")
    public void userExpandsTheFilterSection() {
        resultsPage.clickFiltersButton();
    }

    @When("^User selects (.*) stops for the Number of stops filter")
    public void userSelectsStopsForTheNumberOfStopsFilter(String stops){
        resultsPage.filterStops(stops);
    }

    @When("^User selects (.*) price for the Price filter$")
    public void userSelectsPriceForThePriceFilter(String price){
        resultsPage.modifyPriceFilter(price);
    }

    @When("^User selects (.*) airlines for the Airlines filter$")
    public void userSelectsAirlinesAirlinesForTheAirlinesFilter(String airlines) {
        resultsPage.selectAirlines(airlines);
    }

    @When("User selects to Reset all filters")
    public void userSelectsToResetAllFilters() {
        resultsPage.clickResetAllFilters();
    }
}
