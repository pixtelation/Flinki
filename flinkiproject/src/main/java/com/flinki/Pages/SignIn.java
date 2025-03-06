package com.flinki.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flinki.Base.base;

public class SignIn extends base{
   

    public SignIn(WebDriver driver)
    {
      this.driver=driver;
      PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[@class='btn line-btn']")
    WebElement signinbtn;


    public void SignInfx()
    {
      signinbtn.click();
    }
    
}
