package exam;
// package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prog18 {
    public static void main(String[] args) {
        Thread myntraThread = new Thread(new MyntraTest());
        Thread flipkartThread = new Thread(new FlipkartTest());

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
    public void run() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.myntra.com");

        driver.findElement(By.cssSelector("input.desktop-searchBar"))
              .sendKeys("Men T-Shirt", Keys.ENTER);

        System.out.println("Myntra Search Done: " + driver.getTitle());
        driver.quit();
    }
}

class FlipkartTest implements Runnable {
    public void run() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com");
        driver.manage().window().maximize();

        try {
            driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z")).click();
        } catch (Exception e) {}

        driver.findElement(By.name("q")).sendKeys("Laptop", Keys.ENTER);

        System.out.println("Flipkart Search Done: " + driver.getTitle());
        driver.quit();
    }
}
