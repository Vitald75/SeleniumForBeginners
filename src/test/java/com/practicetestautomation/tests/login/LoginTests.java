package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        System.out.println("Runnig test in " + browser);

        switch (browser.toLowerCase()) {
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(groups={"positive", "regression", "smoke"})
    public void testLoginFunctionality(){
        // Open page
        //WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/");

        // Type username student into Username field
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");
        //usernameField.sendKeys("wrong_student");

        // Type password Password123 into Password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        try {
            Thread.sleep(2000); // pause 2 sec
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "url matches");

        //Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage), "Message was found");

        // Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        //WebElement logOutButton = driver.findElement(By.linkText("Log in"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Button LogOut was found");

        //driver.quit();

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups={"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        //WebDriver driver = new ChromeDriver();
        //System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();
        //driver.get("https://practicetestautomation.com/practice-test-login/");


        // Type username wrong_student into Username field
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        // Type password Password123 into Password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        try {
            Thread.sleep(2000); // pause 2 sec
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify Error message is displayed    Display message: Your username is invalid!
        WebElement usernameInvalidMessage = null;
        try {
            usernameInvalidMessage = driver.findElement(By.id("error"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Assert.assertTrue(usernameInvalidMessage.isDisplayed(), expectedErrorMessage);

        // Verify Error message text is Your username is invalid!
        Assert.assertEquals(usernameInvalidMessage.getText(), expectedErrorMessage, "Message content");

        //driver.quit();
    }
}
