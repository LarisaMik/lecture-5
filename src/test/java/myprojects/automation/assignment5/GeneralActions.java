package myprojects.automation.assignment5;


import myprojects.automation.assignment5.model.ClientInfo;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openRandomProduct() {
        String text = driver.findElement(By.id("js-product-list-top")).getText();
        System.out.println(text);
        String splitText = text.substring(text.indexOf(':') + 1, text.indexOf(".")).trim();
        int countProducts = Integer.parseInt(splitText);
        int random = ThreadLocalRandom.current().nextInt(0, countProducts);

        List<WebElement> elements = driver.findElements(By.className("product-title"));
        elements.get(random).click();
    }

    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        // TODO extract data from opened page
        throw new UnsupportedOperationException();
    }

    public void waitForContentLoad(String xpath) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));

    }

    public ClientInfo getRandomName (){
        String[] names = {"Eighan", "John", "Ned", "Jaimie", "Ted"};
        String[] surnames = {"Lannister", "Stark", "Tirel", "Targarien", "Baratheon"};
        String[] domains = {"@gmail.com", "@europe.com", "@mail.com", "@i.ua"};

        String randomName = names[ThreadLocalRandom.current().nextInt(names.length)];
        String randomSurname = surnames[ThreadLocalRandom.current().nextInt(surnames.length)];
        String randomDomain = domains[ThreadLocalRandom.current().nextInt(domains.length)];
        String email = (randomName.substring(0,1)+randomSurname+randomDomain).toLowerCase();
        return new ClientInfo(randomName,randomSurname,email);
    }
}
