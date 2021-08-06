package page;


import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;


public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static void switchToAnotherWindow(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        windows.stream()
                .forEach(windowHandle -> {
                    if (!originalWindow.contentEquals(windowHandle))
                        driver.switchTo().window(windowHandle);
                });
    }

    public static WebElement waitForElementVisibilityLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(by));

    }

    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));

    }

    public static void pasteFromClipboard(WebDriver driver) {
        Actions paste = new Actions(driver);
        paste.keyDown(Keys.CONTROL).sendKeys("v").perform();
    }
}

