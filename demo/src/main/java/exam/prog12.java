package exam;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class prog12 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);

        String[][] data = {
            {"invaliduser", "Password123"},
            {"student", "wrongpass"},
            {"", "Password123"},
            {"student", ""},
            {"", ""}
        };

        for (String[] creds : data) {
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("password")).clear();

            driver.findElement(By.id("username")).sendKeys(creds[0]);
            driver.findElement(By.id("password")).sendKeys(creds[1]);
            driver.findElement(By.id("submit")).click();

            Thread.sleep(1500);
            try {
                String err = driver.findElement(By.id("error")).getText();
                System.out.println("❌ Failed [" + creds[0] + "/" + creds[1] + "] → " + err);
            } catch (Exception e) {
                System.out.println("⚠ No error for [" + creds[0] + "/" + creds[1] + "]");
            }
            driver.get(url);
        }

        driver.quit();
        System.out.println("✅ Negative login tests completed!");
    }
}
