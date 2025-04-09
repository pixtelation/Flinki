package com.flinki.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flinki.Base.base;
import com.flinki.Base.configReader;
import com.flinki.utils.Data;
import com.flinki.utils.ExcelUtils;
import com.flinki.utils.Generic;


public class SignUp extends base{

    //public String Image = ".\src\main\java\com\flinki\Resources\Images\image.png";

    ExcelUtils ex = new ExcelUtils();
    Generic signup = new Generic();
    

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

    @FindBy(xpath = "//button[text()='Sign Up / Sign In']")
    WebElement SignUpbtn;

    @FindBy(xpath = "//button[text()='Continue']")
    WebElement AfterOTPContinue;

    //input[@name='myfile']
    @FindBy(xpath = "//input[@name='myfile']")
    WebElement UploadImage;

    @FindBy(xpath = "//h2[contains(text(), 'You must agree to the Privacy Policy & Terms')]")
    private WebElement errorMessage;




    /////////////////////////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////////////////////////////
    public String getErrorMessage()
    {
        return signup.getElementText(errorMessage);
    }

    public boolean  errorMessageDisplayed()
    {
        signup.isElementDisplayed(errorMessage);
        return true;

    }
    
    
    
    public void SignupWithoutTCfx() //// Signup Without T&C
    {
        CMSsignupbtnEL.click();
        SignupEmailEl.click();
        SignupEmailEl.sendKeys(Data.randomEmail);
        SignUpbtn.click();
    //     WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(4));
    //     WebElement toast = wt.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='p-toast-message-text']")));
    //    System.out.println(toast.getText()); 
    }

   

    public ProfileCreation SignupWithTCfx() throws Exception { ////Signup with T&C
        CMSsignupbtnEL.click();

        SignupEmailEl.click();
        SignupEmailEl.sendKeys(Data.randomEmail);
        System.out.println(Data.randomEmail);
        SignupTCCheckbox.click();
        SignUpbtn.click();
        Thread.sleep(7000);

        WebElement enterOtp = driver
                .findElement(By.xpath("//HTML[contains(@lang,'en')]/BODY/DIV[2]/DIV/DIV[2]/DIV/DIV/DIV[2]/DIV/INPUT"));
        enterOtp.click();
        enterOtp.sendKeys("123456");
        Thread.sleep(6000);

        ex.ExcelData();////////Store Data.randomEmail in Excel sheet
        WebElement AfterOTPContinue = driver.findElement(By.xpath("//button[text()='Continue']"));
        Thread.sleep(6000);
        AfterOTPContinue.click();
        Thread.sleep(3000);

        return new ProfileCreation(driver);

        //  OTPYopmail otpobj = new OTPYopmail(driver);////////OTP from Yopmail

    }
    
    public Homepage SignIn() throws InterruptedException
    {
        CMSsignupbtnEL.click();
        SignupEmailEl.click();
        SignupEmailEl.sendKeys(configReader.getProperty("email"));
        SignupTCCheckbox.click();
        SignUpbtn.click();
        Thread.sleep(7000);

        WebElement enterOtp = driver
                .findElement(By.xpath("//HTML[contains(@lang,'en')]/BODY/DIV[2]/DIV/DIV[2]/DIV/DIV/DIV[2]/DIV/INPUT"));
        enterOtp.click();
        enterOtp.sendKeys("123456");
        Thread.sleep(6000);

        
        WebElement AfterOTPContinue = driver.findElement(By.xpath("//button[text()='Continue']"));
        Thread.sleep(6000);
        AfterOTPContinue.click();
        Thread.sleep(3000);

        return new Homepage(driver);

    }






    
    
}
