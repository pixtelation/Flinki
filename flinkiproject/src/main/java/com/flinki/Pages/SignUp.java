package com.flinki.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


import com.flinki.Base.base;
import com.flinki.Resources.Data;
import com.flinki.Resources.ExcelUtils;
import com.flinki.Resources.OTPYopmail;


public class SignUp extends base{

    //public String Image = ".\src\main\java\com\flinki\Resources\Images\image.png";

    ExcelUtils ex = new ExcelUtils();
    

     /////////////////////////////////////////////////////////////////////Constructor////////////////////////////////////////////////////////////////////////////////////////
    public SignUp(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

 /////////////////////////////////////////////////////////////////////Locators////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "(//a[@class='btn'])[1]")
    WebElement CMSsignupbtnEL;

    @FindBy(xpath = "//input[@required and @class='form-control']")
    WebElement SignupEmailEl;

    @FindBy(xpath = "//input[@id='flexCheckDefault' and @type='checkbox']")
    WebElement SignupTCCheckbox;

    @FindBy(xpath = "//button[text()='Sign Up']")
    WebElement SignUpbtn;

    @FindBy(xpath = "//button[text()='Continue']")
    WebElement AfterOTPContinue;

    //input[@name='myfile']
    @FindBy(xpath = "//input[@name='myfile']")
    WebElement UploadImage;




     /////////////////////////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////////////////////////////
    public void SignupWithoutTCfx() //// Signup Without T&C
    {
        CMSsignupbtnEL.click();
        SignupEmailEl.click();
        SignupEmailEl.sendKeys(Data.randomEmail);
        SignUpbtn.click();
        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement toast = wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='p-toast-message-text']")));
       System.out.println(toast.getText()); 
    }

   

    public void SignupWithTCfx() throws Exception {  ////Signup with T&C
        CMSsignupbtnEL.click();
        SignupEmailEl.click();
        SignupEmailEl.sendKeys(Data.randomEmail);
        System.out.println(Data.randomEmail);
        SignupTCCheckbox.click();
        SignUpbtn.click();
         
        ex.ExcelData();////////Store Data.randomEmail in Excel sheet

         
        OTPYopmail otpobj = new OTPYopmail(driver);////////OTP from Yopmail
         
         
//           String originalWindow = driver.getWindowHandle();

//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("window.open('about:blank', '_blank');");

//         Set<String> allWindows = driver.getWindowHandles();
//         for (String window : allWindows) {
//             if (!window.equals(originalWindow)) {
//                 driver.switchTo().window(window);
//                 break;
//             }
//         }

//         driver.get("https://yopmail.com/en/");


//         WebElement emailInput = driver.findElement(By.xpath("//INPUT[contains(@class,'ycptinput')]"));
//         emailInput.sendKeys(Data.randomEmail)
// ;

//         WebElement login = driver.findElement(By.xpath("//I[normalize-space(.)='\uE5C8']"));
//         login.click();

//         Thread.sleep(2000);

//         driver.switchTo().frame("ifmail");  // Replace with the actual iframe ID or index
//         WebElement otp = driver.findElement(By.xpath("//STRONG[text()]"));
//         String Otp = otp.getText();
//         // Copy OTP to Clipboard
//         StringSelection stringSelection = new StringSelection(Otp);
//         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

//         driver.switchTo().defaultContent(); // Switch back to main content
//         driver.switchTo().window(originalWindow);
//         WebElement enterOtp = driver.findElement(By.xpath("//HTML[contains(@lang,'en')]/BODY/DIV[2]/DIV/DIV[2]/DIV/DIV/DIV[2]/DIV/INPUT[1]"));
//         enterOtp.click();
 
//          // Use Robot class to paste OTP
//          Robot robot = new Robot();
//          robot.delay(1000); // Small delay to ensure field is focused
 
//          // Press CTRL + V (Paste)
//          robot.keyPress(KeyEvent.VK_CONTROL);
//          robot.keyPress(KeyEvent.VK_V);
//          robot.keyRelease(KeyEvent.VK_V);
//          robot.keyRelease(KeyEvent.VK_CONTROL);
 
//          // Press ENTER if needed
//          robot.keyPress(KeyEvent.VK_ENTER);
//          robot.keyRelease(KeyEvent.VK_ENTER);
 
//          System.out.println(Otp + ":OTP pasted successfully!");
        
//          Thread.sleep(5000);
//          AfterOTPContinue.click();
        // UploadImage.sendKeys(Image);

       
    }






    
    
}
