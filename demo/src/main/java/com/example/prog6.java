package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class prog6 {

    public static void main(String[] args) {
        // Step - 1 : Launch Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        // Step - 2 : Create WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step - 3 : Click the 'Remove' button
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeButton.click();

        // Step - 4 : Wait until the checkbox is removed
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        System.out.println("Checkbox removed dynamically");

        // Step - 5 : Wait for and click the 'Add' button
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add')]")));
        addButton.click();

        // Step - 6 : Wait for the new checkbox to appear
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
        System.out.println("Checkbox added back dynamically");

        // Step - 7 : Verify the checkbox is displayed and onteract with it
        if(checkbox.isDisplayed()) {
            checkbox.click();
            System.out.println("Checkbox clicked successfully after dynamix load");
        }

        // Step - 8 : Close the browser
        driver.quit();
    }
}