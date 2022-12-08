package org.example.pageObject;

import org.example.absctractComponents.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends UtilityMethods {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
//    WebElement userEmail= driver.findElement(By.id("userEmail"));
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(name="login")
    WebElement loginBtn;
    @FindBy(css=".toast-message")
    WebElement errorToast;


    public ProductCatalogPage loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
        ProductCatalogPage pc=new ProductCatalogPage(driver);
        return pc;
    }
    public void goTo(String url){
        driver.get(url);
    }

     public String getErrorToast(){
      waitForWebElementToAppear(errorToast);
        String error=errorToast.getText();
        return error;
    }}