package com.flinki.Pages;

import java.awt.AWTException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;
import com.flinki.utils.Log;

public class  MyRaceEventCalender extends BasePage {
      private static final Logger logger = Log.getLogger(Homepage.class); 
    public MyRaceEventCalender(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//button[normalize-space()='Create New Event']")
    public WebElement createEvent;
    @FindBy(xpath = "//input[@type='file'][@id='cover-upload']/parent::div")
    private WebElement coverPhoto;
    @FindBy(xpath = "//input[@id='profile-upload']")
    private WebElement profilePhoto;
    @FindBy(xpath="//select[@name='privacyType']")
    private WebElement selectVisiblity;
    @FindBy(xpath="//input[@placeholder='Event name']")
    private WebElement eventName;
    @FindBy(xpath = "//div[@class='select__value-container css-hlgwow']")
    private WebElement sportName;
    @FindBy(xpath="//div[@class='css-19bb58m']/parent::div")
    private WebElement typeOfCreate;
    @FindBy(xpath="//select[@name='timezone']")
    private WebElement selectTimezone;
    @FindBy(xpath = "//input[@placeholder='Start date*']")
    private WebElement startDate;
    @FindBy(xpath = "//input[@placeholder='Start time*']")
    private WebElement startTime;
    @FindBy(xpath = "//input[@placeholder='Address*']")
    private WebElement addreses;
    @FindBy(xpath="//input[@placeholder='Email*']")
    private WebElement email;
    @FindBy(xpath="//input[@placeholder='Phone*']")
    private WebElement phone;
    @FindBy(xpath="//input[@placeholder='Event Website']")
    private WebElement eventWebSite;
    @FindBy(xpath="//input[@placeholder='Event Registration Link']")
    private WebElement eventRegistrationLink;
    @FindBy(xpath="//textarea[@placeholder='Event description']")
    private WebElement eventDescripton;
    @FindBy(xpath="//button[@type='submit']")
    public WebElement createButton;
    @FindBy(xpath="//input[@id='flexCheckDefault']")
    private WebElement additonalfieldsCheckBox;
    @FindBy(xpath="//div[contains(text(),'is required')]")
    private List<WebElement> allerrorMessage;


//==================================Verify all Mandatory field error message in Create Events page  ====================================
public boolean verifyAllMandatoryFieldErrors() {
    List<String> expectedErrors = Arrays.asList(

            "Privacy type is required",
            "Event name is required",
            "Sport is required",
            "Sport type is required",
            "Timezone is required",
            "Start date is required",
            "Start time is required",
            "Address is required",
            "Email is required",
            "Phone is required"
            
            );


    List<String> actualErrors = allerrorMessage.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

    return actualErrors.containsAll(expectedErrors);
}

//======================Upload cover Photo========================
public MyRaceEventCalender uploadCoverPhoto(String path)
{
    try {
        Thread.sleep(500);
        uploadImage(coverPhoto, path);
        Thread.sleep(500);
        logger.info("Upload Cover Photo Sucesfully");

    } catch (Exception e) {
        logger.warn("Failed to Upload Cover Photo ");

    }

    return this;

}
//======================Upload Profile  Photo========================
public MyRaceEventCalender uploadProfilePhoto(String path)
{
    try {
        uploadImage(profilePhoto, path);
        Thread.sleep(500);
        logger.info("Upload Profile Photo Sucesfully");

    } catch (Exception e) {
        logger.warn("Failed to Upload Profile Photo ");

    }

    return this;
}
 //======================Select Visiblity ========================  
public MyRaceEventCalender getSelectVisliblity()
{
    try {

        getSelect(selectVisiblity).selectByIndex(1);
        logger.info("Select  Visiblity ");

    
    } catch (Exception e) {
        logger.warn("Unable to Select visiblity");

} 
    return this;

}
//======================Enter Random Event name ========================
public MyRaceEventCalender enterEventName()
{
    try {
        enterText(eventName, randomstring());
        logger.info("Enter event name ");

    } catch (Exception e) {
        logger.warn("Unable to Enter Event name ");
    }
    return this;
}
//===========================Chouse sports form keyboard actions============================
public MyRaceEventCalender chouseSports() 
{
    try {
        selectDropdownUsingKeyboard(sportName);
        logger.info("Select Sport Sucesfulluy ");
    } catch (AWTException e) {
        logger.warn("Unable to use Keyboard Action ");

    }
    return this;
            
}

}
