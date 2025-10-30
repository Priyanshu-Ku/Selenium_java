package exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class prog15 {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        BufferedReader br = new BufferedReader(new FileReader("login_data.csv"));
        br.readLine(); // skip header

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String user = data[0], pass = data[1];

            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(user);
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(pass);
            driver.findElement(By.id("submit")).click();

            try { Thread.sleep(2000); } catch (InterruptedException e) {}

            if (driver.getPageSource().contains("Logged In Successfully"))
                System.out.println("✅ Login success for: " + user);
            else
                System.out.println("❌ Login failed for: " + user);

            driver.get("https://practicetestautomation.com/practice-test-login/");
        }
        br.close();
        driver.quit();
    }
}
