import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Utils {
    static WebDriver driver;

    static WebElement elementBy(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    static void sendToTextField(String xpath, String message) {
        WebElement textField = elementBy(xpath);
        textField.click();
        textField.sendKeys(message);
    }

    static void sleepFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {

        }
    }

    static void pickFromListBy(String xpath, int element, int div) {
        elementBy(xpath).click();
        elementBy("/html/body/div["+div+"]/div/ul/li[" + element +"]").click();
        sleepFor(500);
    }

    static void pickFromListBy(String xpath, int element) {
        pickFromListBy(xpath, element, 2);
    }

    static WebElement elementByClass(String className, int pos) {
        return elementsByClass(className).get(pos);
    }

    static List<WebElement> elementsByClass(String className) {
        return driver.findElements(By.className(className));
    }

    static void sendToTextField(int pos, String message) {
        WebElement textField = elementByClass("md-input", pos);
        textField.click();
        textField.sendKeys(message);
    }

    static void pickTableRow(String xpath, int row) {
        String rowStr = row == 0 ? "" : "[" + row + "]";
        elementBy(xpath + "/tbody/tr"+ rowStr +"/td[1]").click();
    }
}
