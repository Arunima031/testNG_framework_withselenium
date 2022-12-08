package org.example.tests;
import org.example.pageObject.CartPage;
import org.example.pageObject.ProductCatalogPage;
import org.example.testComponents.BaseTest;
import org.example.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ErrorValidationTest extends BaseTest {
    @Test(groups={"Error validation"},retryAnalyzer = Retry.class)
    public void loggingErrorValidation() {
       lp.loginApplication("nottinghill@yopmail.com","Hello@123");
        Assert.assertEquals("Incorrect email or password.",lp.getErrorToast());
    }
    @Test
    public void productErrorValidation() {
        String productName = "ADIDAS ORIGINAL";
        ProductCatalogPage pc = lp.loginApplication("rosanna.bell@yopmail.com","Hello@123");
        pc.getProductList();
        pc.selectProduct(productName);
        CartPage cp=pc.goToCart();
        Assert.assertFalse(cp.validateAddedProduct("ZARA"));
    }
}
