package com.flinki.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;

public class Homepage extends BasePage {
    
    public Homepage(WebDriver driver)
    {
        super(driver);
    }


    @FindBy(xpath = "//a[@href='/profile']")
    private WebElement profile;

    public ProfilePage clickProfile() throws InterruptedException
    {
        clickElement(profile);
        Thread.sleep(3000);

        return new ProfilePage(driver);
        
    }

    

}
