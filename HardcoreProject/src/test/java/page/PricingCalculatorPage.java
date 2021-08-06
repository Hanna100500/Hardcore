package page;

import model.Engine;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PricingCalculatorPage extends BasePage {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//iframe[contains(@src,'calculator')]")
    private WebElement frame1;

    @FindBy(xpath = "//iframe[contains(@id,'myFrame')]")
    private WebElement frame2;

    @FindBy(id = "input_67")
    private WebElement numberOfInstanceField;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//button[@aria-label='Email Estimate']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailFieldOnEmailYourEstimateModalWindow;

    @FindBy(xpath = "//button[@aria-label = 'Send Email']")
    private WebElement sendEmailButtonOnEmailYourEstimateModalWindow;

    @FindBy(xpath = "//*[@id='resultBlock']//h2//b")
    private WebElement totalEstimatedCostLine;

    public PricingCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PricingCalculatorPage fillInFieldsInCalculator(Engine engine) {
        driver.switchTo().frame(frame1).switchTo().frame(frame2);
        waitForElementVisibility(driver, numberOfInstanceField).sendKeys(engine.getNumberOfInstances());
        logger.info("Calculator fields are filled in");
        return this;
    }

    public PricingCalculatorPage clickOnAddEstimateButton() {
        waitForElementVisibility(driver, addToEstimateButton).click();
        logger.info("Engine is added to estimate");
        return this;
    }

    public PricingCalculatorPage sendEstimateByEmail() throws InterruptedException {
        driver.switchTo().frame(frame1).switchTo().frame(frame2);
        waitForElementVisibility(driver, emailEstimateButton).click();
        driver.switchTo().activeElement();
        waitForElementVisibility(driver, emailFieldOnEmailYourEstimateModalWindow).click();
        pasteFromClipboard(driver);
        waitForElementVisibility(driver, sendEmailButtonOnEmailYourEstimateModalWindow).click();
        logger.info("Estimate is sent by email");
        return this;
    }

    public String getTotalEstimatedCost() {
        return StringUtils.stripEnd(StringUtils.stripStart(totalEstimatedCostLine.getText(), "Total Estimated Cost: "), "per 1 month");
    }
}



