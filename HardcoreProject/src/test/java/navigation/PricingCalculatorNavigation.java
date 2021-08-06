package navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import page.BasePage;

public class PricingCalculatorNavigation {

    private final String GOOGLE_CLOUD_URL = "https://cloud.google.com/";

    public void navigateToCalculator(WebDriver driver) {
        driver.get(GOOGLE_CLOUD_URL);
        driver.findElement(By.xpath("//input[contains(@class,'search-field')]"))
                .sendKeys("Google Cloud Platform Pricing Calculator" + Keys.ENTER);
        BasePage.waitForElementVisibilityLocatedBy(driver, By.xpath("//a[@class='gs-title']")).click();
    }
}
