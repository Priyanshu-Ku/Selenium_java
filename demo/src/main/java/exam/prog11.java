package exam;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class prog11 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver(); // Open Chrome
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Enter username and password
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click(); // Click login

        // Wait for successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean loggedIn = wait.until(ExpectedConditions
                .urlToBe("https://practicetestautomation.com/logged-in-successfully/"));

        System.out.println(loggedIn ? "Login successful!" : "Login failed!");
        Thread.sleep(3000);
        driver.quit();
    }
}
