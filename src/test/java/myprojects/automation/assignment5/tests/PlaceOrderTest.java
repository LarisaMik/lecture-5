package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class PlaceOrderTest extends BaseTest {

    @Test (groups = "basic")
    public void checkSiteVersion() {
        setUp("chrome", "");
        driver.get(Properties.getBaseUrl());
        WebElement mainImage = driver.findElement(By.className("carousel-item"));
        System.out.println(mainImage.isDisplayed() ? "desktop version" : "mobile version");
                // TODO open main page and validate website version
    }
   // @Test (groups = "basic")
    public void remoteChrome() {
        setUp("mobile", "");
        driver.get(Properties.getBaseAdminUrl());
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();

        // TODO open main page and validate website version
    }

    @Test
    public void createNewOrder() {
      //  setUp("chrome","" );
        // TODO implement order creation test
        driver.get(Properties.getBaseUrl());
        driver.findElement(By.className("all-product-link")).click();

        // open random product
        actions.openRandomProduct();

        // save product parameters
        String productNameClient = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        Float productPriceClient = getPriceFromClientData(driver.findElement(By.xpath("//div[@class='current-price']")).getText());
        driver.findElement(By.xpath("//a[@href='#product-details']")).click();
        actions.waitForContentLoad("//div[@class='product-quantities']");


        Integer productQtyClient = getQtyFromClientData(driver.findElement(By.xpath("//div[@class='product-quantities']/span")).getText());

        ProductData clientProductData = new ProductData(productNameClient, productQtyClient, productPriceClient);
        System.out.println(clientProductData);

        // add product to Cart and validate product information in the Cart
        driver.findElement(By.className("add-to-cart")).click();
        actions.waitForContentLoad("//div[@class='cart-content']");
        System.out.println("количество: " + driver.findElement(By.className("cart-products-count")).getText());
        System.out.println("цена: " + driver.findElement(By.xpath("//div[@class='cart-content']//p[2]")).getText());
        System.out.println("название: " + driver.findElement(By.className("product-name")).getText());
        driver.findElement(By.xpath("//div[@class='cart-content']//a")).click();
        System.out.println(actions.getRandomName().toString());
        driver.findElement(By.xpath("//*[contains(text(),'Оформление заказа')]")).click();













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
