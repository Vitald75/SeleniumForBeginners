package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTests {

    @Test
    public void incorrectUserNameTest() {
        WebDriver driver = new ChromeDriver();
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

    @Test
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
