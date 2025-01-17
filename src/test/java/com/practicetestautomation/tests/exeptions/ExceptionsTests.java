package com.practicetestautomation.tests.exeptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExceptionsTests.class);
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);

        //System.out.println("Runnig test in " + browser);
        logger.info("Runnig test in " + browser);

        switch (browser.toLowerCase()) {
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            default:
                //System.out.println("Configaration is missing, set to default - Chrome");
                logger.warning("Configaration is missing, set to default - Chrome");
                driver = new ChromeDriver();
                break;
        }
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Close browser");
        driver.quit();
    }

    @Test
    public void noSuchElementExceptionTest(){

        //Click the add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //Verify Row2 Input line
        //WebElement input2Line = driver.findElement(By.cssSelector("#row2 > input"));
        WebElement input2Line = driver.findElement((By.xpath("//div[@id='row2']/input")));



        try {
            Assert.assertTrue(input2Line.isDisplayed(), "InputLine2 was found");
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }


    }


}
