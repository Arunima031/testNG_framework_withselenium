package org.example.absctractComponents;

import org.apache.commons.io.FileUtils;
import org.example.pageObject.CartPage;
import org.example.pageObject.OrderPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UtilityMethods {
    WebDriver driver;

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartIcon;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderIcon;

    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForWebElementToAppear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement ele) {
//        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCart() {
//        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
//                wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        cartIcon.click();
        CartPage cp = new CartPage(driver);
        return cp;
    }

    public OrderPage goToMyOrders() {
        orderIcon.click();
        OrderPage op = new OrderPage(driver);
        return op;
    }

}