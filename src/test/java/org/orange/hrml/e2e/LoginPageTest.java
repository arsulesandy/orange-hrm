package org.orange.hrml.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {

    private WebDriver driver;

    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\AB000367\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");


        this.driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void loadLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    @Test
    public void testLogin() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginButton.click();

        // Add assertion based on the expected behavior of your application
        // For example, you can assert that a successful login redirects to a certain page.
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @AfterTest
    public void tearDown() {

        try {
            Thread.sleep(5000); // Wait for 3 seconds (adjust as needed)
        } catch (InterruptedException ignored) {
        }
        driver.quit();
    }
}
