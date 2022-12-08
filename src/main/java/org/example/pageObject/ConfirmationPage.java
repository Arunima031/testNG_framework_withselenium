package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConfirmationPage {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationText;
    @FindBy(xpath = "//td[contains(text(),'You can')]/parent::tr/following-sibling::tr/td/label")
    List<WebElement> orderIds;

    public String getConfirmationMessage(){
        return confirmationText.getText();
    }
    public void printlabels() {
        for (WebElement id : orderIds) {
            String text = id.getText();
            System.out.println(text);
        }
    }


}
