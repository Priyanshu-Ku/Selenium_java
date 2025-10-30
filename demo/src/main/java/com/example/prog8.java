package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class prog8 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread myntraThread = new Thread(new MyntraTest());
        Thread flipkartThread = new Thread(new FlipkartTest());

        // Start both threads exactly at the same time
        myntraThread.start();
        flipkartThread.start();

        try {
            myntraThread.join();
            flipkartThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both Myntra and Flipkart searches executed concurrently.");
    }
}

class MyntraTest implements Runnable {
    @Override
    public void run() {
        // Set ChromeDriver path

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.myntra.com");

        WebElement searchBox = driver.findElement(By.cssSelector("input.desktop-searchBar"));
        searchBox.sendKeys("Men T-Shirt");
        searchBox.sendKeys(Keys.ENTER);

        System.out.println("Myntra Search Completed: Page title is " + driver.getTitle());
        driver.quit();
    }
}

class FlipkartTest implements Runnable {
    @Override
    public void run() {
        // Set ChromeDriver path

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com");

        // Maximize window
        driver.manage().window().maximize();

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Laptop");
        searchBox.sendKeys(Keys.ENTER);

        // Try closing popup in parallel (non-blocking)
        try {
            WebElement closeBtn = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            // Popup not present, ignore
        }

        System.out.println("Flipkart Search Completed: Page title is " + driver.getTitle());
        driver.quit();
    }
}
