package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class prog2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ---------- Negative Test: Invalid Username and Password ----------
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Enter invalid credentials
        username.clear();
        username.sendKeys("wrongUser");
        password.clear();
        password.sendKeys("wrongPass");
        loginButton.click();

        // Wait for error message to appear
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h3[data-test='error']")));

        String actualError = errorMsg.getText();
        String expectedError = "Epic sadface: Username and password do not match any user";

        // Verify error message
        if (actualError.contains(expectedError)) {
            System.out.println("Negative Test Passed: Correct error message displayed.");
        } else {
            System.out.println("Negative Test Failed: Unexpected message - " + actualError);
        }

        // ---------- Additional Check: Empty Fields ----------
        username.clear();
        password.clear();
        loginButton.click();

        WebElement emptyError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h3[data-test='error']")));

        System.out.println("Empty field error message: " + emptyError.getText());

        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }
}
