package test;

import model.Engine;
import navigation.PricingCalculatorNavigation;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RandomEmailBoxPage;
import page.PricingCalculatorPage;
import service.EngineCreator;


public class UsingPricingCalculatorTest extends CommonConditions {

    @Test
    public void testTotalMonthlyCostDisplayedCorrectlyInEmail() throws InterruptedException {
        PricingCalculatorNavigation calculator = new PricingCalculatorNavigation();
        Engine testEngine = EngineCreator.createSomeDefaultEngine();

        calculator.navigateToCalculator(driver);

        new PricingCalculatorPage(driver)
                .fillInFieldsInCalculator(testEngine)
                .clickOnAddEstimateButton();

        new RandomEmailBoxPage(driver)
                .getRandomEmail();

        String estimatedCostFromCalculator = new PricingCalculatorPage(driver)
                .sendEstimateByEmail()
                .getTotalEstimatedCost();

        String estimatedCostFromEmail = new RandomEmailBoxPage(driver)
                .navigateToEmailBox()
                .getTotalEstimatedCost();

        Assert.assertEquals(estimatedCostFromCalculator, estimatedCostFromEmail, "Estimate Cost from email not equals to calculator");
    }
}
