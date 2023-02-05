# flight-network-ui-test
This is a UI automation suite written in Java 11 with Serenity, Cucumber, Gherkin that mainly tests the filters of a user flight search

# Git
If you want to **clone this repository** then run this command in your terminal

````
git clone https://github.com/bpapad/flight-network-ui-test
````

# Run tests
You can either:
````
Run tests via the TestRunner.class

*Test results will be displayed in the Run tab
````
Or:
````
Run tests via the Terminal

mvn clean verify

*Test results will be displayed in the Terminal tab and a Serenity report will be generated in:
target/site/serenity -> index.html
````

# Info
This suite includes several automated tests as well as some dummy manual tests that were conducted via the UI

# Possible Improvements
**For now** all tests regard the UI filtering and the data displayed after those filters apply.
````
Cross check the filters that are applied through the UI with the API call made as well the response received.
1) Check the api request body for existing filters
2) Parse the api response to assert that the results are correct
````
````
Implement an API testing suite that:
1) Creates a request with desired filters
2) Makes an api call with the created request
3) Parse api response to assert that the results are correct

*Access to the related SWAGGER page is needed for further development
````
