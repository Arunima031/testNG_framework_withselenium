package org.example.tests;
import org.example.pageObject.*;
import org.example.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData",groups={"Purchase"})
    public void submitOrder(HashMap<String,String> input) {

        ProductCatalogPage pc = lp.loginApplication(input.get("email"),input.get("password"));
        pc.getProductList();
        pc.selectProduct(input.get("productName"));
        CartPage cp=pc.goToCart();
        Assert.assertTrue(cp.validateAddedProduct(input.get("productName")));
        CheckoutPage chckout= cp.checkoutCard();
        ConfirmationPage cn= chckout.selectCountryAndSubmit("India");
        Assert.assertTrue(cn.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        cn.printlabels();
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void checkOrders(){
        ProductCatalogPage pc = lp.loginApplication("graham.bell@yopmail.com","Hello@123");
        OrderPage op = pc.goToMyOrders();
        Assert.assertTrue(op.validateOrderinList(productName));
    }
    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> map =new HashMap<String,String>();
//        map.put("email","graham.bell@yopmail.com");
//        map.put("password","Hello@123");
//        map.put("productName","ZARA COAT 3");
//        HashMap<String,String> map1 =new HashMap<String,String>();
//        map1.put("email","rosanna.bell@yopmail.com");
//        map1.put("password","Hello@123");
//        map1.put("productName","ADIDAS ORIGINAL");
        List<HashMap<String, String>> list = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\org\\example\\data\\purchaseOrder.json");
        return  new Object[][] {{list.get(0)},{list.get(1)}};

    }
}
