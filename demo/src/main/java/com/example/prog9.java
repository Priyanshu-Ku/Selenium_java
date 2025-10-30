package com.example;

// package ex1;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class prog9 {

    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup(); // auto-manage driver binary [attached_file:2]
        WebDriver driver = new ChromeDriver(); // start Chrome [attached_file:2]
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait [attached_file:2]
        driver.manage().window().maximize(); // maximize window [attached_file:2]

        // Navigate to demo alert page
        driver.get("https://the-internet.herokuapp.com/javascript_alerts"); // target page [attached_file:2]

        try {
            // ======================= SIMPLE ALERT =======================
            WebElement simpleAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']")); // locator
                                                                                                               // [attached_file:2]
            simpleAlertBtn.click(); // open alert [attached_file:2]

            Alert simpleAlert = driver.switchTo().alert(); // switch context [attached_file:2]
            System.out.println("Simple Alert Text: " + simpleAlert.getText()); // print text [attached_file:2]
            Thread.sleep(3000); // observe [attached_file:2]

            simpleAlert.accept(); // OK [attached_file:2]
            System.out.println("Simple Alert Accepted"); // status [attached_file:2]
            Thread.sleep(1000); // post-accept pause [attached_file:2]

            // ======================= CONFIRM ALERT ======================
            WebElement confirmAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")); // locator
                                                                                                                  // [attached_file:3]
            confirmAlertBtn.click(); // open confirm [attached_file:3]

            Alert confirmAlert = driver.switchTo().alert(); // switch context [attached_file:3]
            System.out.println("Confirm Alert Text: " + confirmAlert.getText()); // print text [attached_file:3]
            Thread.sleep(5000); // observe [attached_file:3]

            // Accept (OK)
            confirmAlert.accept(); // OK [attached_file:3]
            System.out.println("Confirm Alert Accepted (OK)"); // status [attached_file:3]
            Thread.sleep(3000); // observe result [attached_file:3]

            // Trigger confirm alert again
            confirmAlertBtn.click(); // open again [attached_file:3]
            confirmAlert = driver.switchTo().alert(); // re-acquire handle [attached_file:3]

            Thread.sleep(5000); // observe [attached_file:3]

            // Dismiss (Cancel)
            confirmAlert.dismiss(); // Cancel [attached_file:3]
            System.out.println("Confirm Alert Dismissed (Cancel)"); // status [attached_file:3]
            Thread.sleep(3000); // observe result [attached_file:3]

            // ======================= PROMPT ALERT =======================
            WebElement promptAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")); // locator
                                                                                                                // [attached_file:1]
            promptAlertBtn.click(); // open prompt [attached_file:1]

            // Switch to alert
            Alert promptAlert = driver.switchTo().alert(); // switch context [attached_file:1]
            System.out.println("Prompt Alert Text: " + promptAlert.getText()); // print text [attached_file:1]

            // Type text into the prompt using Robot (character-by-character)
            try {
                Robot robot = new Robot(); // native events [attached_file:1]
                String text = "Hello Selenium!"; // input [attached_file:1]

                for (char c : text.toCharArray()) { // iterate [attached_file:1]
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c); // map char->keycode [attached_file:1]
                    robot.keyPress(keyCode); // press [attached_file:1]
                    robot.keyRelease(keyCode); // release [attached_file:1]
                    Thread.sleep(300); // ~0.3s per char [attached_file:1]
                }

                // Allow time for text to appear in the prompt box
                Thread.sleep(15000); // ~1.5 seconds in screenshot, here extended for visibility [attached_file:1]
            } catch (InterruptedException e) {
                e.printStackTrace(); // error handling [attached_file:1]
            }

            // Accept the prompt
            promptAlert.accept(); // submit text [attached_file:1]
            System.out.println("Prompt Alert Accepted"); // status [attached_file:1]

            // Delay to observe the page after prompt
            Thread.sleep(10000); // observe [attached_file:1]

        } catch (Exception e) {
            e.printStackTrace(); // general catch [attached_file:1][attached_file:3]
        } finally {
            driver.quit(); // cleanup [attached_file:1]
        }
    }
}