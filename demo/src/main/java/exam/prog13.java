package exam;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class prog13 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.myntra.com"); // Open Myntra

        driver.findElement(By.linkText("Men")).click(); // Click "Men" category
        Thread.sleep(2000);
        System.out.println("Now at: " + driver.getCurrentUrl()); // Print current page URL

        driver.navigate().back(); // Go back to homepage
        Thread.sleep(2000);
        System.out.println("Back to: " + driver.getCurrentUrl());

        driver.quit(); // Close browser
    }
}

