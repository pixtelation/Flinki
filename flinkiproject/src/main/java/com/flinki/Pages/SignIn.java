package com.flinki.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;

public class SignIn extends BasePage{
   

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
