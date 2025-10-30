package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prog4 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the test form page
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        // Scroll helper
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fill Username
        WebElement username = driver.findElement(By.name("username"));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", username);
        username.sendKeys("John Doe");

        // Fill Password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password123");

        // Fill Comments
        WebElement comment = driver.findElement(By.name("comments"));
        comment.sendKeys("This is a test comment.");

        // Upload File
        WebElement FileUpload = driver.findElement(By.name("filename"));
        FileUpload.sendKeys("C:\\Users\\Priyanshu\\Desktop\\Station Folder\\Apps\\Power Plan.txt"); // Change to a valid
                                                                                                    // file path

        // Select Checkboxes
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb1']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Select Radio Button
        WebElement radioBtn = driver.findElement(By.cssSelector("input[type='radio'][value='rd1']"));
        radioBtn.click();

        // Select Dropdown
        WebElement dropdown = driver.findElement(By.name("dropdown"));
        dropdown.sendKeys("Drop Down Item 1");

        // Submit the form normally
        WebElement submitBtn = driver.findElement(By.cssSelector("input[type='submit']"));
        js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        Thread.sleep(2000);
        submitBtn.click();

        // wait to observe the result
        Thread.sleep(3000);
        // Close the browser
        driver.quit();
    }
}
