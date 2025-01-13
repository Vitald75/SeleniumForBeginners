package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {
    @Test(groups={"positive", "regression", "smoke"})
    public void testLoginFunctionality(){
        // Open page
        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");

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

        driver.quit();

    }

    @Test(groups={"negative", "regression"})
    public void incorrectUserNameTest() {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();

        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Type username wrong_student into Username field
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("Wrong_student");

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

        // Verify Error message is displayed
        WebElement usernameInvalidMessage = null;
        try {
            usernameInvalidMessage = driver.findElement(By.id("error"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(usernameInvalidMessage.isDisplayed(), "Display message: Your username is invalid!");

        // Verify Error message text is Your username is invalid!
        Assert.assertEquals(usernameInvalidMessage.getText(), "Your username is invalid!", "Message content");

        driver.quit();

    }

    @Test(groups={"negative", "regression"})
    public void incorrectPasswordTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Type username student into Username field
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");

        // Type password Password123 into Password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Weong_Password123");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        try {
            Thread.sleep(2000); // pause 2 sec
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify Error message is displayed
        WebElement usernameInvalidMessage = null;
        try {
            usernameInvalidMessage = driver.findElement(By.id("error"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(usernameInvalidMessage.isDisplayed(), "Display message: Your password is invalid!");

        // Verify Error message text is Your username is invalid!
        Assert.assertEquals(usernameInvalidMessage.getText(), "Your password is invalid!", "Message content");

        driver.quit();
    }

}
