package com.flinki.Base;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
     public  WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public JavascriptExecutor jsExecutor;


      
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void clickElement(WebElement element)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }

    }
    
    public void enterText(WebElement element , String text)
    {
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        webElement.click();  // Ensure the field is focused
        webElement.clear();  // Try clearing the field normally
        
        // Double clear using JavaScript in case normal clear() fails
        jsExecutor.executeScript("arguments[0].value = '';", webElement);

        Thread.sleep(500);  // Small delay to ensure the field is clear
        webElement.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text: " + e.getMessage());
        }
    }
    
    public String getElementText(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            System.out.println("Unable to get text: " + e.getMessage());
            return "";
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getPageTittle()
    {
        return driver.getTitle();
    }

    public void scrollToElement(WebElement element) {
        
    
        jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    
        try {
            Thread.sleep(500); // Short delay for smooth scrolling
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     // Method to get the current URL
     public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    // Method to verify if the current URL contains expected text
    public boolean verifyURLContains(String expectedText) {
        return driver.getCurrentUrl().contains(expectedText);
    }

    public Select getSelect(WebElement dropdownElement) {
        return new Select(dropdownElement);
    }
    
    

    public static String randomstring() {
        String randomname = RandomStringUtils.randomAlphabetic(5);
        return randomname;
    }

    public static String randomnumber() {
        String randomphone = RandomStringUtils.randomNumeric(10);
        return randomphone;
    }
    

    public static String randomemail() {
        String randomemail = RandomStringUtils.randomAlphabetic(5) + "@yopmail.com";
        return randomemail;
    }
    
    public void clickRandomElement(List<WebElement> elements) {
        if (!elements.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(elements.size());
            WebElement randomElement = elements.get(randomIndex);
            randomElement.click();
        } else {
            System.out.println("No elements found for locator: " + elements);
        }

    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    



    public void uploadImage(WebElement inputElement, String fileName) {
        try {
            String projectPath = System.getProperty("user.dir");
            String fullPath = projectPath + "/src/main/Resources/Images/" + fileName;
            File file = new File(fullPath);
    
            if (!file.exists()) {
                System.out.println("❌ File not found: " + fullPath);
                return;
            }
    
            inputElement.sendKeys(file.getAbsolutePath());
            Thread.sleep(2000); // Wait for upload if needed
            System.out.println("✅ Image uploaded successfully: " + fileName);
    
        } catch (Exception e) {
            System.out.println("❌ Failed to upload image (" + fileName + "): " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    

}
