import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Application {

    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Users/iurii/Developer/WebDriver/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 1, 1000);
        Auth.driver = driver;
        Utils.driver = driver;
    }

    static void employeeGoToPage(EmployeePages page) {
        Auth.employee();
        Utils.elementBy("/html/body/div/div/div/div/div/div[2]/div/div/ul/li[" + page.id + "]").click();
    }

    static void employeeResourcesListInformation() { // Test 2
        Auth.employee();
        Utils.elementBy( "/html/body/div/div/div/div/div/div[2]/div/ul/li[4]").click();
    }

    static void employeeSingleResourceInformation() { // Test 3
        employeeResourcesListInformation();
        Utils.pickTableRow("/html/body/div/div/div/main/div/div/div/div[2]/div/div/table", 0);
        Utils.elementBy( "/html/body/div/div/div/main/div/div/div/div[3]/div/div[3]/button[2]").click();
    }

    static void employeeResourceManagement(ResourceManagement management) { // Test 9, 13
        employeeGoToPage(EmployeePages.Costs);
        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[2]/div[1]/div/div[2]/div/div", 1);
        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[2]/div[2]/div/div[2]/div/div", 1);
        Utils.sendToTextField(2, "3");
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[3]/button[" + management.id +"]").click();
    }

    static void alchemistCookReceipt(int number) { // Test 10
        Auth.employee();
        Utils.elementBy("/html/body/div/div/div/div/div/div[2]/div/ul/li[1]").click();
        Utils.pickTableRow("/html/body/div/div/div/main/div/div/div/div[3]/div/div/table", number);
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[4]/div/div[3]/button[1]").click();
    }

    static void alchemistCreateReceipt() { // Test 14
        Auth.employee();
        Utils.elementBy("/html/body/div/div/div/div/div/div[2]/div/ul/li[1]").click();
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[1]/div/div[2]/button[2]").click();

        Utils.sendToTextField(0, "TestName");
        Utils.sendToTextField(1, "TestDescr");
        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[1]/div[4]/div/div[2]/div/div", 1);
        Utils.sendToTextField(3, "TestInstr");

        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[3]/button").click();
        Utils.pickFromListBy("/html/body/div[2]/div/div[1]/div/div[1]/div/div[2]/div/div", 1, 3);
        Utils.pickFromListBy("/html/body/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div", 1, 3);
        Utils.elementBy("/html/body/div[2]/div/div[2]/button[2]/div").click();

        Utils.sleepFor(1000);
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[4]/button[1]").click();
    }

    static void employeeChangeOrderStatus(int number) { // Test 11
        employeeGoToPage(EmployeePages.Orders);
        String rowStr = number == 0 ? "" : "[" + number + "]";

        Utils.pickFromListBy("/html/body/div/div/div/main/div/div/div/div[3]/div/div/table/tbody/tr"+rowStr+"/td[5]/div/div/div/div", 3);
    }

    static void wisardEnchantedRune(int number) { // Test 12
        employeeGoToPage(EmployeePages.Orders); // todo
        String rowStr = number == 0 ? "" : "[" + number + "]";

        Utils.pickTableRow("/html/body/div/div/div/main/div/div/div/div[2]/div/div/table", 0);
        Utils.elementBy("/html/body/div/div/div/main/div/div/div/div[3]/div/div[3]/button[2]").click();
    }
}