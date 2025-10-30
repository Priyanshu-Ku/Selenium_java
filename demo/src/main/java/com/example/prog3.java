package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prog3{

    public static void main(String[] args) throws InterruptedException {
        // Set up the WebDriver (ChromeDriver in this case)
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Myntra homepage
        driver.get("https://www.myntra.com");

        // Click on the "Men" category link using link text
        WebElement menLink = driver.findElement(By.linkText("Men"));
        menLink.click();

        // Wait for the page to load
        Thread.sleep(2000); // Replace with explicit wait in production code

        // Verify we're on the Men's category page
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        // Go back to the Myntra homepage using the back button
        driver.navigate().back();

        // Wait for the page to load
        Thread.sleep(2000); // Replace with explicit wait in production code

        // Verify we're back on the homepage
        System.out.println("Navigated back to: " + driver.getCurrentUrl());

        // Close the browser
        driver.quit();
    }
}