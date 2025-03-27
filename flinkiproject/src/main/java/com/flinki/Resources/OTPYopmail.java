package com.flinki.Resources;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flinki.Base.base;

public class OTPYopmail extends base {
   
    public  OTPYopmail( WebDriver driver) throws InterruptedException 
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        
        String originalWindow = driver.getWindowHandle();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('about:blank', '_blank');");

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        driver.get("https://yopmail.com/en/");

        WebElement emailInput = driver.findElement(By.xpath("//INPUT[contains(@class,'ycptinput')]"));
        
        emailInput.sendKeys(Data.randomEmail);

        WebElement login = driver.findElement(By.xpath("//I[normalize-space(.)='\uE5C8']"));
        login.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        
        }
        Thread.sleep(5000);
        driver.switchTo().frame("ifmail");  // Replace with the actual iframe ID or index
        WebElement otp = driver.findElement(By.xpath("//STRONG[text()]"));
        String Otp = otp.getText();
        // Copy OTP to Clipboard
        StringSelection stringSelection = new StringSelection(Otp);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        driver.switchTo().defaultContent(); // Switch back to main content
        driver.switchTo().window(originalWindow);
        WebElement enterOtp = driver.findElement(By.xpath("//HTML[contains(@lang,'en')]/BODY/DIV[2]/DIV/DIV[2]/DIV/DIV/DIV[2]/DIV/INPUT[1]"));
        enterOtp.click();
        Thread.sleep(3000);
        List<WebElement> otpFields = driver.findElements(By.xpath("//input[@type='text']"));

// Ensure we have enough fields to enter OTP
if (otpFields.size() >= Otp.length()) {
    for (int i = 0; i < Otp.length(); i++) {
        otpFields.get(i).sendKeys(Character.toString(Otp.charAt(i)));
    }
} else {
    System.out.println("Error: Not enough OTP input fields found!");
}
    
 
         System.out.println(Otp + ":OTP pasted successfully!");
        
         try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        WebElement AfterOTPContinue = driver.findElement(By.xpath("//button[text()='Continue']"));
         AfterOTPContinue.click();
    }
    
}
