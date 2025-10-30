package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class prog7 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open iLovePDF PDF to Word page
        driver.get("https://www.ilovepdf.com/pdf_to_word");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until the upload area is visible
        WebElement selectFileButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a#pickfiles")));

        // Scroll to the button and click using JS (some clicks are blocked by overlays)
        js.executeScript("arguments[0].scrollIntoView(true);", selectFileButton);
        js.executeScript("arguments[0].click();", selectFileButton);

        // Wait briefly for the file input to appear
        Thread.sleep(2000);

        // Find the actual hidden input element and send file path
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(
                "C:\\Users\\Priyanshu\\Desktop\\Station Folder\\3rd YEAR\\5th Sem\\DSP\\22CSE554_DATA SCIENCE USING PYTHON.pdf");

        System.out.println("File selected successfully, waiting for upload...");

        // Wait for the Convert button to appear and click it
        WebElement convertButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("processTask")));
        js.executeScript("arguments[0].scrollIntoView(true);", convertButton);
        convertButton.click();

        System.out.println("Converting... please wait");

        // Wait for the download button (this confirms upload & conversion)
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.downloader_btn, a#pickfiles")));

        if (downloadButton.isDisplayed()) {
            System.out.println("File uploaded and ready for download!");
        } else {
            System.out.println("File upload failed.");
        }

        // Scroll and click Download
        js.executeScript("arguments[0].scrollIntoView(true);", downloadButton);
        js.executeScript("arguments[0].click();", downloadButton);

        System.out.println("Conversion complete. File downloaded automatically!");

        // Wait to observe the result
        Thread.sleep(5000);
        driver.quit();
    }
}
