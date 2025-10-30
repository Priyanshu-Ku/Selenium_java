package exam;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class prog20 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        performSearch(new ChromeDriver(), "Chrome");

        System.setProperty("webdriver.edge.driver", "C:\\Users\\Priyanshu\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        performSearch(new EdgeDriver(), "Edge");
    }

    public static void performSearch(WebDriver driver, String browser) {
        try {
            driver.manage().window().maximize();
            driver.get("https://www.myntra.com");
            WebElement search = driver.findElement(By.cssSelector("input.desktop-searchBar"));
            search.sendKeys("Men T-Shirt", Keys.ENTER);
            System.out.println(browser + " Search Done: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

