package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class prog1 {

    public static void main(String[] args) throws InterruptedException {

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Find the username input field and enter valid username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");

        // Find the password input field and enter valid password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");

        // Find the login button by ID and click it
        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();

        // Wait for the post-login page to load and verify successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean loggedIn = wait.until(
                ExpectedConditions.urlToBe("https://practicetestautomation.com/logged-in-successfully/"));

        if (loggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed or URL mismatch.");
        }

        // Close the browser
        Thread.sleep(5000);
        driver.quit();
    }
}
