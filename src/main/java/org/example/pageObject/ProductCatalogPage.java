package org.example.pageObject;

import org.example.absctractComponents.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends UtilityMethods {
    WebDriver driver;
    public ProductCatalogPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".card-body")
    List<WebElement> products;
    @FindBy(css=".ng-animating")
    WebElement loader;
    By productsBy=By.cssSelector(".card-body");
    By addCartButton=By.cssSelector(".card-body button:last-of-type");
    By successToast=By.cssSelector("#toast-container");
    public List<WebElement> getProductList(){
    waitForElementToAppear(productsBy);
    return products;
    }
    public WebElement getRequiredProduct(String productName){
        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
    }
    public void selectProduct(String productName){
        WebElement prod = getRequiredProduct(productName);
        prod.findElement(addCartButton).click();
        waitForElementToAppear(successToast);
        waitForElementToDisappear(loader);
    }
}
