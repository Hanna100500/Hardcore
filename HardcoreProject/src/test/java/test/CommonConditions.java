package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import util.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners ({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;

    @BeforeClass
    public void browserSetUp() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void exit() {
        DriverSingleton.closeDriver();
    }
}
