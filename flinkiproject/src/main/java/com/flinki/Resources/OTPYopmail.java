package com.flinki.Resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.flinki.Base.base;

public class OTPYopmail extends base {
   
    public  OTPYopmail( WebDriver driver) 
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
 
         // Use Robot class to paste OTP
         Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         robot.delay(1000); // Small delay to ensure field is focused
 
         // Press CTRL + V (Paste)
         robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
 
         // Press ENTER if needed
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
 
         System.out.println(Otp + ":OTP pasted successfully!");
        
         try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WebElement AfterOTPContinue = driver.findElement(By.xpath("//button[text()='Continue']"));
         AfterOTPContinue.click();
    }
    
}
