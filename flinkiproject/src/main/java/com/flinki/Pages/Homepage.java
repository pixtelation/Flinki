package com.flinki.Pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//div[@class='listing-card']")
    private List<WebElement> productList;

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

    public MyRaceEventCalender clickMyRaceEventCalender()
    {
        clickElement(myRaceEventCalender);
        logger.info("Click My Race/Events calender");
        return new MyRaceEventCalender(driver);
    }

    public void clickMyListing()
    {
        clickElement(myListing);
    }
    
    public SignIn signOut()
    {
        clickElement(username);
        logger.info("Click  Profile Link");
        clickElement(signOutButton);
        logger.info("Clicked SignOut Button");
        clickElement(signOutContinueButton);
        logger.info("Clicked Continue Button");
        return new SignIn(driver);

    }

    public void printProductListAndPrice()
    {
        for (WebElement card : productList) {
            try {

                String name = card.findElement(By.tagName("h5")).getText();
                String price = card.findElement(By.tagName("p")).getText();
                System.out.println("Product Name: " + name + " | Price: " + price);

                
            } catch (Exception e) {
                System.out.println("Element not found in this card. Skipping...");
            }
            
        }
    }


    

    

}
