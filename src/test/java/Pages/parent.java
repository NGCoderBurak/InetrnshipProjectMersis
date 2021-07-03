package Pages;

import Utilities.baseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class parent {

    static WebDriverWait wait;

    public parent() {
        wait = new WebDriverWait(baseDriver.getDriver(), 15);
    }

    public void sendKeysFunction(WebElement element, String value) {
        waitUntilVisible(element);
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);
    }

    public void clickFunction(WebElement element) {
        waitUntilClickable(element);
        scrollToElement(element);
        element.click();
    }

    public static void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) baseDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToUpElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) baseDriver.getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'top:0px')", element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waiting(int second) {
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyContainsText(WebElement element, String msg) {
        waitUntilVisible(element);
        Assert.assertTrue(element.getText().toLowerCase().contains(msg.toLowerCase()));
    }

    public void waitNumberOfElementsToBeLessThan(By selector, int number) {
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(selector, number));
    }

    public void listSelectOption(List<WebElement> list, String option) {
        for (WebElement x : list) {
            if (x.getText().contains(option)) {
                x.click();
                break;
            }
        }
    }

    public void escClick() {
        Actions actions = new Actions(baseDriver.getDriver());
        Action action = actions.sendKeys(Keys.ESCAPE).build();
        action.perform();
    }
}
