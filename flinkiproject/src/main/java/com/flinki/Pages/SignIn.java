package com.flinki.Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;
import com.flinki.utils.Log;

public class SignIn extends BasePage {
   private static final Logger logger = Log.getLogger(SignIn.class); 
  
   

    public SignIn(WebDriver driver)
    {
      super(driver);
    }


    @FindBy(xpath = "//a[@class='btn line-btn']")
    WebElement signinbtn;

    @FindBy(xpath = "//h2[normalize-space()='Welcome']")
    WebElement welcomeText;



    public void SignInfx()
    {
      signinbtn.click();
    }

    public String getWelComeText()
    {
       return getElementText(welcomeText);
      
    }
    
}
