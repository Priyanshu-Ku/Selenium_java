package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class prog5 {
    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Step 2: Configure browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Step 3: Read data from CSV file
        String csvFile = "login_data.csv"; // CSV must exist in project root
        String line;
        String csvSplitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine(); // Skip header

        // Step 4: Loop through data
        while ((line = br.readLine()) != null) {
            String[] data = line.split(csvSplitBy);
            String username = data[0];
            String password = data[1];

            // Enter credentials
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("submit")).click();

            // Small delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check result
            if (driver.getPageSource().contains("Logged In Successfully")) {
                System.out.println("✅ Login successful for: " + username);
            } else {
                System.out.println("❌ Login failed for: " + username);
            }

            // Go back to login page
            driver.get("https://practicetestautomation.com/practice-test-login/");
        }

        // Step 5: Cleanup
        br.close();
        driver.quit();
    }
}

