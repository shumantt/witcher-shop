import org.openqa.selenium.By;
;import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Auth {

    static WebDriver driver;

    static void manager() {
        authUser("manager", "123");
    }

    static void employee() {
        authUser("employee", "123");
    }

    static void client() {
        authUser("client", "123");
    }

    private static void authUser(String login, String pwd) {
        driver.get("http://localhost:8080");

        Utils.sendToTextField(0, login);
        Utils.sendToTextField(1, pwd);

        driver.findElement (By.xpath  ("/html/body/div/div/div[3]/button[1]")).click();
    }
}
