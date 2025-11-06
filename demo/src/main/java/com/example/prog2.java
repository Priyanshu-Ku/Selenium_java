package com.example;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class prog2 {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Maximize window and set implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String baseUrl = "https://practicetestautomation.com/practice-test-login/";
        driver.get(baseUrl);

        // Test data for negative login cases
        String[][] testData = {
                { "invaliduser", "Password123" },
                { "student", "wrongpass" },
                { "", "Password123" },
                { "student", "" },
                { "", "" }
        };

        // Loop through each test case
        for (String[] data : testData) {
            String username = data[0];
            String password = data[1];

            // Clear fields before each input
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("password")).clear();

            // Enter credentials
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);

            // Click login
            driver.findElement(By.id("submit")).click();

            // Wait for a short while for response
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check for error message
            try {
                String errorMessage = driver.findElement(By.id("error")).getText();
                System.out.println("❌ Login failed for [" + username + "/" + password + "]");
                System.out.println("   Error message displayed: " + errorMessage);
            } catch (Exception e) {
                System.out.println("⚠ No error message found for [" + username + "/" + password + "]");
            }

            // Reload the page for the next test case
            driver.get(baseUrl);
        }

        // Close browser
        driver.quit();
        System.out.println("✅ Negative login tests completed successfully!");
    }
}