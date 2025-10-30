package exam;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prog14 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fill form fields
        driver.findElement(By.name("username")).sendKeys("John Doe");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("comments")).sendKeys("This is a test comment.");

        // Upload file (change path if needed)
        driver.findElement(By.name("filename"))
              .sendKeys("C:\\Users\\Priyanshu\\Desktop\\Station Folder\\Apps\\Power Plan.txt");

        // Select checkbox and radio button
        WebElement cb = driver.findElement(By.cssSelector("input[value='cb1']"));
        if (!cb.isSelected()) cb.click();
        driver.findElement(By.cssSelector("input[value='rd1']")).click();

        // Select dropdown option
        driver.findElement(By.name("dropdown")).sendKeys("Drop Down Item 1");

        // Submit form
        WebElement submit = driver.findElement(By.cssSelector("input[type='submit']"));
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        Thread.sleep(2000);
        submit.click();

        Thread.sleep(3000);
        driver.quit();
    }
}

