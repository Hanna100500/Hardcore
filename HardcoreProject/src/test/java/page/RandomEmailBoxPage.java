package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RandomEmailBoxPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();
    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/en/email-generator";

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement frame;

    @FindBy(xpath = "//table//td[not(contains(@colspan,'3'))]/h3")
    private WebElement totalEstimatedCostLine;

    @FindBy(xpath = "//button[@id = 'cprnd']")
    private WebElement copyToClipboardButton;

    @FindBy(xpath = "//*[@onclick = 'egengo();']")
    private WebElement checkInboxButton;

    public RandomEmailBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getRandomEmail() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(EMAIL_GENERATOR_URL);
        waitForElementVisibility(driver, copyToClipboardButton).click();
        logger.info("Random email is created");
        switchToAnotherWindow(driver);
    }

    public RandomEmailBoxPage navigateToEmailBox() throws InterruptedException {
        switchToAnotherWindow(driver);
        Thread.sleep(3000);
        waitForElementVisibility(driver, checkInboxButton).click();
        logger.info ("User is redirected to random email box");
        return this;
    }

    public String getTotalEstimatedCost() {
        String totalEstimatedCost = null;
        try {
            driver.switchTo().frame(frame);
        totalEstimatedCost =  waitForElementVisibility(driver, totalEstimatedCostLine).getText();
        } catch (Exception e){
            logger.error("No google cloud price email in mail box");
        }
        return totalEstimatedCost;
    }
}
