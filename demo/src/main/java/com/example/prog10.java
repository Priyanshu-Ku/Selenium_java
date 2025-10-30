package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class prog10 {

    public static void main(String[] args) {
        // Chrome setup (works online)
        //WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        performSearch(chromeDriver, "Chrome");

        // Edge setup using local EdgeDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Priyanshu\\Downloads\\edgedriver_win64\\msedgedriver.exe"); // <-- Update path to your downloaded EdgeDriver
        WebDriver edgeDriver = new EdgeDriver();
        performSearch(edgeDriver, "Edge");
    }

    public static void performSearch(WebDriver driver, String browserName) {
        try {
            driver.manage().window().maximize();
            driver.get("https://www.myntra.com");

            WebElement searchBox = driver.findElement(By.cssSelector("input.desktop-searchBar"));
            searchBox.sendKeys("Men T-Shirt");
            searchBox.sendKeys(Keys.ENTER);

            System.out.println(browserName + " Search Completed: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
