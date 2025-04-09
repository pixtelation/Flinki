package com.flinki.Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;
import com.flinki.utils.Log;

public class Homepage extends BasePage {
    private static final Logger logger = Log.getLogger(Homepage.class); 
    
    public Homepage(WebDriver driver)
    {
        super(driver);
    }


    @FindBy(xpath = "//a[@href='/profile']")
    private WebElement profile;
    @FindBy(xpath = "//a[@href='/connect']")
    private WebElement connect;
    @FindBy(xpath = "//a[normalize-space()='Races and Events']")
    private WebElement raceEvents;
    @FindBy(xpath = "//a[normalize-space()='Marketplace']")
    private WebElement marketPlace;
    @FindBy(xpath = "//a[normalize-space()='My Groups & Clubs']")
    private WebElement myGroupClubs;
    @FindBy(xpath = "//a[normalize-space()='My Race/Event Calendar']")
    private WebElement myRaceEventCalender;
    @FindBy(xpath = "//a[normalize-space()='My Listings']")
    private WebElement myListing;


    @FindBy(xpath = "//button[@class='profile-button']")
    private WebElement username;

    @FindBy(xpath = "//button[normalize-space()='Sign Out']")
    private WebElement signOutButton;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    private WebElement signOutContinueButton;


    public ProfilePage clickProfile()
    {
        try {
            logger.info(" Navigating to Profile...");
            clickElement(profile);
            logger.info("Click Profile Link ");
            waitForPageLoad();
            return new ProfilePage(driver);
        } catch (Exception e) {
            logger.error(" Failed to click Profile: " + e.getMessage());
            throw e;
        }

    }

    public void clickConnect()
    {   logger.info(" Clicking on Connect tab");
        clickElement(connect);

    }

    public void clickRaceEvents()
    {
        clickElement(raceEvents);

    }

    public void clickMarketPlace()
    {
        clickElement(marketPlace);
    }

    public void clickMyGroupClubs()
    {
        clickElement(myGroupClubs);
    }

    public void clickMyRaceEventCalender()
    {
        clickElement(myRaceEventCalender);
    }

    public void clickMyListing()
    {
        clickElement(myListing);
    }
    
    public SignIn signOut()
    {
        clickElement(username);
        clickElement(signOutButton);
        clickElement(signOutContinueButton);
        return new SignIn(driver);

    }


    

}
