package org.example.pageObject;

import org.example.absctractComponents.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends UtilityMethods {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder=\"Select Country\"]")
    WebElement countryTextBox;

    By autosuggest = By.cssSelector("section.ta-results");

    @FindBy(css=".list-group-item span")
    List<WebElement> searchResults;
    @FindBy(css=".action__submit")
    WebElement submitButton;
    public ConfirmationPage selectCountryAndSubmit(String countryName) {
        countryTextBox.sendKeys(countryName);
        waitForElementToAppear(autosuggest);
        searchResults.stream().filter(result -> result.getText().trim().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();
        submitButton.click();
        ConfirmationPage cn =new ConfirmationPage(driver);
        return cn;
    }
}