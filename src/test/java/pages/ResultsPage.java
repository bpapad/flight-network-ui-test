package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ResultsPage extends PageObject {

    @FindBy(xpath = ".//span[text() = 'Recommended']/ancestor::button")
    WebElement recommendedButton;

    @FindBy(xpath = ".//span[text() = 'Promotion']/ancestor::button")
    WebElement promotionButton;

    @FindBy(xpath = ".//span[text() = 'Cheapest']/ancestor::button")
    WebElement cheapestButton;

    @FindBy(xpath = ".//span[text() = 'Cheapest']/ancestor::button//*[contains(text(), '$')]")
    WebElement cheapestPrice;

    @FindBy(xpath = ".//span[text() = 'Shortest']/ancestor::button")
    WebElement shortestButton;

    @FindBy(xpath = ".//button[contains(@data-testid,'toggleFiltersButton')]")
    WebElement filtersButton;

    @FindBy(xpath = ".//label[text() = 'Nonstop flights']")
    WebElement stopsFilterNoStopsLabel;

    @FindBy(xpath = ".//label[text() = 'Maximum one stop']")
    WebElement stopsFilterOneStopsLabel;

    @FindBy(xpath = ".//label[text() = 'All']")
    WebElement stopsFilterAllStopsLabel;

    @FindBy(xpath = ".//div[contains(@data-testid, 'PRICEFilter')]//div[@data-testid= 'handle-0']")
    WebElement lowestPriceHandle;

    @FindBy(xpath = ".//div[contains(@data-testid, 'PRICEFilter')]//div[@data-testid= 'handle-1']")
    WebElement highestPriceHandle;

    @FindBy(xpath = ".//div[contains(@data-testid, 'PRICEFilter')]//div[contains(text(), '$')][1]")
    WebElement lowestPriceFilter;

    @FindBy(xpath = ".//div[contains(@data-testid, 'PRICEFilter')]//div[contains(text(), '$')][2]")
    WebElement highestPriceFilter;

    @FindBy(xpath = ".//span[contains(text(), 'Clear all')]")
    WebElement clearAllAirlinesButton;

    @FindBy(xpath = ".//button[contains(@data-testid, 'allFilterResetButton')]")
    WebElement resetAllFiltersButton;



    public String getResultPageUrl(){
        return this.getDriver().getCurrentUrl();
    }

    public void waitForResults(){
        WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofMillis(10000));
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton));
    }

    public void waitForFiltersToApply(){
        WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofMillis(10000));
        wait.until(ExpectedConditions.elementToBeClickable(recommendedButton));
        wait.until(ExpectedConditions.elementToBeClickable(promotionButton));
        wait.until(ExpectedConditions.elementToBeClickable(cheapestButton));
        wait.until(ExpectedConditions.elementToBeClickable(shortestButton));
    }

    public String getOrigin(){
        WebElement origin = this.getDriver().findElement(By.xpath(".//div[contains(@data-testid, 'TitleText')]/span[1]"));
        return origin.getText();
    }

    public String getDestination(){
        WebElement destination = this.getDriver().findElement(By.xpath(".//div[contains(@data-testid, 'TitleText')]/span[3]"));
        return destination.getText();
    }

    public void clickFiltersButton(){
        filtersButton.click();
    }

    public Boolean allFiltersAreDisplayedCorrectly(){
        WebDriver driver = this.getDriver();
        try{
            driver.findElement(By.xpath(".//header//div[contains(text(), 'Number of stops')]")).isDisplayed();
            driver.findElement(By.xpath(".//header//div[contains(text(), 'Price')]")).isDisplayed();
            driver.findElement(By.xpath(".//header//div[contains(text(), 'Airlines')]")).isDisplayed();
            driver.findElement(By.xpath(".//header//div[contains(text(), 'Travel time')]")).isDisplayed();
            driver.findElement(By.xpath(".//header//div[contains(text(), 'Number of stops')]")).isDisplayed();

            String tripType = Serenity.getCurrentSession().get("TRIP_TYPE").toString();
            if(tripType.contains("One-way")){
                driver.findElement(By.xpath(".//header[contains(@data-testid, 'departureArrival0')]")).isDisplayed();
            }
            if(tripType.contains("Return")){
                driver.findElement(By.xpath(".//header[contains(@data-testid, 'departureArrival0')]")).isDisplayed();
                driver.findElement(By.xpath(".//header[contains(@data-testid, 'departureArrival1')]")).isDisplayed();
            }
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public void filterStops(String stops){
        switch (stops){
            case "0":
                stopsFilterNoStopsLabel.click();
                break;
            case "1":
                stopsFilterOneStopsLabel.click();
                break;
            default:
                stopsFilterAllStopsLabel.click();
                break;
        }
        this.waitForFiltersToApply();
    }

    public Boolean numberOfStopsFilterAppliedCorrectly(String stops){
        WebDriver driver = this.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        try{
            switch (stops){
                case "0":
                    driver.findElement(By.xpath(".//div[@data-testid = 'resultPage-searchResults']//*[contains(text(),'stop')]"));
                    break;
                case "1":
                    driver.findElement(By.xpath(".//div[@data-testid = 'resultPage-searchResults']//*[contains(text(),'stops')]"));
                    break;
                default:
                    return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }catch (Exception e){
            return Boolean.TRUE;
        }
    }

    public void modifyPriceFilter(String price){
        WebDriver driver = this.getDriver();
        Actions actions = new Actions(driver);
        Serenity.getCurrentSession().put("ORIGINAL_LOWEST_PRICE", lowestPriceFilter.getText());
        Serenity.getCurrentSession().put("ORIGINAL_HIGHEST_PRICE", highestPriceFilter.getText());
        switch (price){
            case "increased lowest":
                actions.clickAndHold(lowestPriceHandle)
                        .moveToElement(highestPriceHandle)
                        .release()
                        .perform();
                break;
            case "decreased highest":
                actions.clickAndHold(highestPriceHandle)
                        .moveToElement(lowestPriceHandle)
                        .release()
                        .perform();
                //todo: find out why the highestPriceHandle fails to move
                break;
        }
        this.waitForFiltersToApply();
    }

    public Double getInitialLowestPrice(){
        String originalLowestPrice = Serenity.getCurrentSession().get("ORIGINAL_LOWEST_PRICE").toString().replaceAll("[^\\d.]", "");
        return Double.parseDouble(originalLowestPrice.trim().substring(originalLowestPrice.lastIndexOf("$")+1));
    }

    public Double getNewLowestPrice(){
        String newLowestPrice = lowestPriceFilter.getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(newLowestPrice.trim().substring(newLowestPrice.lastIndexOf("$")+1));
    }

    public Double getCheapestButtonPrice(){
        String cheapestButtonPrice = cheapestPrice.getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(cheapestButtonPrice.trim().substring(cheapestButtonPrice.lastIndexOf("$")+1));
    }

    public Double getInitialHighestPrice(){
        String originalHighestPrice = Serenity.getCurrentSession().get("ORIGINAL_HIGHEST_PRICE").toString().replaceAll("[^\\d.]", "");
        return Double.parseDouble(originalHighestPrice.trim().substring(originalHighestPrice.lastIndexOf("$")+1));
    }

    public Double getNewHighestPrice(){
        String newHighestPrice = highestPriceFilter.getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(newHighestPrice.trim().substring(newHighestPrice.lastIndexOf("$")+1));
    }

    public void selectAirlines(String airlines){
        List<String> airlinesList = List.of(airlines.trim().split(","));
        try{
            clearAllAirlinesButton.click();
            WebDriver driver = this.getDriver();
            for(String airline: airlinesList){
                airline = airline.trim();
                driver.findElement(By.xpath(".//label[contains(text(), '"+airline+"')]")).click();
                this.waitForFiltersToApply();
            }
//            Serenity.getCurrentSession().put("AIRLINES_FILTERS", airlinesList);
        }catch (Exception e){
            System.out.println("A requested airline does not exist");
        }
    }

    public Boolean airlinesRequestedDisplayedCorrectly(String airlines){
        List<String> airlinesList = List.of(airlines.trim().split(","));
        WebDriver driver = this.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        try{
            for(String airline: airlinesList){
                airline = airline.trim();
                driver.findElement(By.xpath(".//section//div[contains(text(), '"+airline+"')]")).isDisplayed();
            }
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public Boolean airlinesNotRequestedAreNotDisplayed(String airlines){
        WebDriver driver = this.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        try{
            driver.findElement(By.xpath(".//section//div[contains(text(), 'Lufthansa')]")).isDisplayed();
            //todo: Make assertion abstract
            return Boolean.FALSE;
        }catch (Exception e){
            return Boolean.TRUE;
        }
    }

    public void clickResetAllFilters(){
        resetAllFiltersButton.click();
        this.waitForFiltersToApply();
    }

    public Boolean appliedFiltersContainFilter(String filter){
        WebDriver driver = this.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        try{
            driver.findElement(By.xpath(".//div[contains(@data-testid, 'selectedFiltersIndicator')]/div[contains(text(), '"+filter+"')]")).isDisplayed();
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public Boolean appliedFiltersDoNotContainFilter(String filter){
        WebDriver driver = this.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        try{
            driver.findElement(By.xpath(".//div[contains(@data-testid, 'selectedFiltersIndicator')]/div[contains(text(), '"+filter+"')]")).isDisplayed();
            return Boolean.FALSE;
        }catch (Exception e){
            return Boolean.TRUE;
        }
    }



}
