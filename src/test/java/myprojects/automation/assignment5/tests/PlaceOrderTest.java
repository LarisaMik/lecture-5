package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.Properties;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {
        driver.get(Properties.getBaseUrl());

        // TODO open main page and validate website version
    }

    @Test
    public void createNewOrder() {
        // TODO implement order creation test
        driver.get(Properties.getBaseUrl());
        driver.findElement(By.className("all-product-link")).click();

        // open random product
        actions.openRandomProduct();

        // save product parameters
        String productNameClient = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        Float productPriceClient = getPriceFromClientData(driver.findElement(By.xpath("//div[@class='current-price']")).getText());
        Integer productQtyClient = getQtyFromClientData(driver.findElement(By.xpath("//div[@class='product-quantities']/span")).getText());

        ProductData clientProductData = new ProductData(productNameClient, productQtyClient, productPriceClient);
        System.out.println(clientProductData);

        // add product to Cart and validate product information in the Cart

        // proceed to order creation, fill required information

        // place new order and validate order summary

        // check updated In Stock value
    }

    private static Integer getQtyFromClientData(String text) {
        String sCount = text.split(" ")[0];
        return Integer.parseInt(sCount);
    }

    private static Float getPriceFromClientData(String text) {
        String sAmount = text.split(" ")[0].replace(",", ".");
        return Float.parseFloat(sAmount);
    }
}
