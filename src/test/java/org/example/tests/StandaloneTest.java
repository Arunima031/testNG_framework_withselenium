package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {

    public static void main(String[] args){
        String productName="adidas original";
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("graham.bell@yopmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Hello@123");
        driver.findElement(By.name("login")).click();
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
        List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
        WebElement prod = products.stream().filter(product ->
                product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
System.out.println(prod);
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartWrap h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")).sendKeys("India");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
        List<WebElement> search = driver.findElements(By.cssSelector(".list-group-item span"));
        search.stream().filter(result -> result.getText().trim().equalsIgnoreCase("India")).findFirst().orElse(null).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        List<WebElement> orderIds = driver.findElements(By.xpath("//td[contains(text(),'You can')]/parent::tr/following-sibling::tr/td/label"));
        for (WebElement id:orderIds) {
            String text = id.getText();
            System.out.println(text);
            driver.close();
        }
    }
}
