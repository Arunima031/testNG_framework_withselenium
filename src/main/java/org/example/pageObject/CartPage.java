package org.example.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class CartPage {
        WebDriver driver;
    public CartPage(WebDriver driver){
            this.driver=driver;
            PageFactory.initElements(driver,this);
        }
        @FindBy(css=".cartWrap h3")
        List<WebElement> cartProducts;
    @FindBy(css=".totalRow button")
    WebElement checkoutButton;

//    public List<WebElement> getAddedCardProducts(){
//        return cartProducts;
//    }

    public boolean validateAddedProduct(String productName){
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage checkoutCard(){
        checkoutButton.click();
        CheckoutPage chckout=new CheckoutPage(driver);
        return chckout;
    }

}
