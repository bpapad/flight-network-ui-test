package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class StartPage extends PageObject {

    @FindBy(xpath = ".//input[contains(@id, 'origin')]")
    WebElement originInput;

    @FindBy(xpath = ".//input[contains(@id, 'destination')]")
    WebElement destinationInput;

    @FindBy(xpath = ".//button[contains(@data-testid, 'searchFlights')]")
    WebElement searchFlightsButton;

    @FindBy(xpath = ".//label[@for = 'singleBound.departureDate']/div")
    WebElement departureDate;

    @FindBy(xpath = ".//label[@for = 'singleBound.returnDate']/div")
    WebElement returnDate;

    @FindBy(xpath = ".//label[contains(@data-testid, 'return')]")
    WebElement returnRadioButton;

    @FindBy(xpath = ".//label[contains(@data-testid, 'oneWay')]")
    WebElement oneWayRadioButton;

    @FindBy(xpath = ".//label[contains(@data-testid, 'multiStop')]")
    WebElement multiCityRadioButton;

    @FindBy(xpath = ".//button[contains(@data-testid, 'addTrip')]")
    WebElement addTripButton;



    public void typeOrigin(String origin) throws InterruptedException {
        originInput.sendKeys(origin);
        Thread.sleep(1500);
        originInput.sendKeys(Keys.ENTER);

        String selectedOrigin = this.getDriver().findElement(By.xpath(".//div[contains(@data-testid, 'origin')]//div[contains(@class, 'singleValue')]")).getText();
        Serenity.getCurrentSession().put("ORIGIN", selectedOrigin);
    }

    public void typeDestination(String destination) throws InterruptedException {
        destinationInput.sendKeys(destination);
        Thread.sleep(1500);
        destinationInput.sendKeys(Keys.ENTER);

        String selectedDestination = this.getDriver().findElement(By.xpath(".//div[contains(@data-testid, 'destination')]//div[contains(@class, 'singleValue')]")).getText();
        Serenity.getCurrentSession().put("DESTINATION", selectedDestination);
    }

    public void pressSearchFlightsButton(){
        searchFlightsButton.click();
    }

    public void selectDepartureDate(String date) throws InterruptedException {
        departureDate.click();
        Thread.sleep(1000);
        if(date.contains("default")){
            this.getDriver().findElement(By.xpath(".//button[@aria-label= 'Next month']")).click();
            this.getDriver().findElement(By.xpath(".//div[contains(@aria-label, '01')]")).click();
        }
        //todo: implement code for specific date input
    }

    public void selectReturnDate(String date) throws InterruptedException {
        returnDate.click();
        Thread.sleep(1000);
        if(date.contains("default")){
            this.getDriver().findElement(By.xpath(".//button[@aria-label= 'Next month']")).click();
            this.getDriver().findElement(By.xpath(".//div[contains(@aria-label, '01')]")).click();
        }
        //todo: implement code for specific date input
    }

    public void clickReturnRadioButton(){
        returnRadioButton.click();
    }

    public void clickOneWayRadioButton(){
        oneWayRadioButton.click();
    }

    public void clickMultiCityRadioButton(){
        multiCityRadioButton.click();
    }

}
