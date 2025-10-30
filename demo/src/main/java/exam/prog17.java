package exam;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class prog17 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ilovepdf.com/pdf_to_word");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Click "Select PDF file" button
        WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("pickfiles")));
        js.executeScript("arguments[0].click();", uploadBtn);
        Thread.sleep(2000);

        // Upload file
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(
            "C:\\Users\\Priyanshu\\Desktop\\Station Folder\\3rd YEAR\\5th Sem\\DSP\\22CSE554_DATA SCIENCE USING PYTHON.pdf");
        System.out.println("File selected, waiting for conversion...");

        // Click Convert button
        WebElement convertBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("processTask")));
        js.executeScript("arguments[0].click();", convertBtn);
        System.out.println("Converting file...");

        // Wait for download button
        WebElement downloadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("a.downloader_btn, a#pickfiles")));
        System.out.println(downloadBtn.isDisplayed() ? "Ready for download!" : "Upload failed!");

        js.executeScript("arguments[0].click();", downloadBtn);
        System.out.println("Download started!");
        Thread.sleep(5000);
        driver.quit();
    }
}
