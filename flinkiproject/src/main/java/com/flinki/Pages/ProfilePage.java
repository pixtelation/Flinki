package com.flinki.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;
import com.flinki.utils.Log;
import com.github.javafaker.Faker;

public class ProfilePage extends BasePage {
     private static final Logger logger = Log.getLogger(ProfilePage.class); 
     Faker faker = new Faker();

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    /*----------------------------------------------------------XPATH-------------------------------- */
    
    @FindBy(xpath = "//input[@id='cover-upload']")
    private WebElement coverPhoto;
    @FindBy(xpath = "//input[@id='profile-upload']")
    private WebElement profilePhoto;

    @FindBy(xpath="//div[@id='3']")
    private WebElement coverPhotoUploadSucessMessage;

    @FindBy(xpath = "//button[normalize-space()='Edit']")
    private WebElement editbutton;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastname;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//h2[contains(text(), 'User save successfully')]")
    private WebElement userSaveSucessfully;

    @FindBy(xpath = "(//button[@class='add-new-race-btn'])[2]")
    private WebElement addNewSportsQulify;

    @FindBy(xpath = "(//div[@class='css-hlgwow'])[1]")
    private WebElement createSports;
    @FindBy(xpath = "(//div[@class='css-hlgwow'])[2]")
    private WebElement createInstitue;

    @FindBy(xpath = "//input[@name='date']")
    private WebElement date;

    @FindBy(xpath = "//textarea[@id='about']")
    private WebElement about;
    @FindBy(xpath = "//button[normalize-space()='Save & Update']")
    private WebElement saveUpdateButton;

    @FindBy(xpath = "//input[@placeholder='Phone number']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement email;

    @FindBy(xpath = "//div[@class='sportstable-wrapper']//button[@class='three-dots-button']")
    private List<WebElement> threeDotButton;;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[@class='dropdown-item'][normalize-space()='Edit']")
    private WebElement edit;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement yesButton;

    // @FindBy(xpath = "//button[normalize-space()='Save']")
    // private WebElement saveButton;

/*---------------------------------------------METHOD--------------------- */

public String getUserSaveSucessMessage()
{
    return getElementText(userSaveSucessfully);
}

public String getCoverPhotoSucessMessage()
{
    return getElementText(coverPhotoUploadSucessMessage);
}

public ProfilePage clickEditButton()
{
    clickElement(editbutton);
    try {
        Thread.sleep(6000);
    } catch (InterruptedException e) {

        e.printStackTrace();
    }
    return this;
}

    //Edit personal name information in profile section 
public ProfilePage editPersonalNameInformation() throws InterruptedException
{
    enterText(firstName, faker.name().firstName());
    Thread.sleep(2000);
    enterText(lastname, faker.name().lastName());
    scrollToElement(saveButton);
    clickElement(saveButton);

    try {
        Thread.sleep(5000); // Short delay for smooth scrolling
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    return this;
}
    //Edit personal sports information in profile section 
public ProfilePage editPersonalSportsInformation() throws AWTException, InterruptedException
{   Thread.sleep(2000);
    scrollToElement(addNewSportsQulify);
    clickElement(addNewSportsQulify);
    if (createSports != null && createSports.isDisplayed()) {
                actions.moveToElement(createSports).click().perform();
                System.out.println(" Moved to createSportsQulif and clicked");
            } else {
                throw new NoSuchElementException(" createSportsQulif not found or not visible.");
            }

            // Use Robot class for keyboard input
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Move to another element
            Thread.sleep(4000);

            if (createInstitue.isDisplayed()&& createInstitue !=null) {
                actions.moveToElement(createInstitue).click().perform();
                System.out.println(" Moved to createInstitute and clicked");
            } else {
                throw new NoSuchElementException(" createInstitute not found.");
            }

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            // Enter the date
            if (date.isDisplayed()&& date!=null) {
               
                date.sendKeys("27/02/2010");
                System.out.println(" Entered date: 27/02/2010");
            } else {
                throw new NoSuchElementException(" Date input field not found.");
            }
            Thread.sleep(2000);

            if (about.isDisplayed() && about != null)
            {
                about.clear();
                about.sendKeys("Lorem  Lorem");
            }
            scrollToElement(saveUpdateButton);
            clickElement(saveUpdateButton);
            Thread.sleep(2000);

    return this;
}



    public String getSucessCoverPhotoUploadMessage() {
        return getElementText(coverPhotoUploadSucessMessage);
    }


    // Contact Details in profile section 
    public void EditContactDetails() throws InterruptedException
    {
        clickElement(editbutton);
        Thread.sleep(3000);
        scrollToElement(phoneNumber);
        enterText(phoneNumber, randomnumber(10));
        enterText(email, randomemail());
        scrollToElement(saveButton);
        clickElement(saveButton);

    }

    public void uploadCoverPhoto() {
        try {
            Path path = Paths.get("src/main/resources/Images/coverPhoto.png").toAbsolutePath();
            File file = path.toFile();

            if (!file.exists()) {
                System.out.println("Cover photo file not found: " + file.getAbsolutePath());
                return;
            }

            coverPhoto.sendKeys(file.getAbsolutePath());
            Thread.sleep(2000);
            System.out.println("Cover photo uploaded successfully.");

        } catch (Exception e) {
            System.out.println("Failed to upload cover photo: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public void coverImageUpload()
    {
        uploadImage(coverPhoto, "dodgeCoverPhoto.jpg");

    }

    public void profileImageUpload()
    {
        uploadImage(profilePhoto, "Profile.jpg");
    }

    public void deleteSportsQulifications()
    {
        scrollToElement(addNewSportsQulify);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Element is not fount" + e.getMessage());
        }
        for (WebElement webElement : threeDotButton) {

            try {
                clickElement(webElement);
                Thread.sleep(1000);
                clickElement(deleteButton);
                clickElement(yesButton);

            } catch (Exception e) {
                System.out.println("Element is not fount" + e.getMessage());

            }

        }
    }
    
    public void addNewSports() throws AWTException, InterruptedException
    {
        scrollToElement(addNewSportsQulify);
        Thread.sleep(2000);
        clickElement(addNewSportsQulify);
        if (createSports != null && createSports.isDisplayed()) {
            actions.moveToElement(createSports).click().perform();
            System.out.println(" Moved to createSportsQulif and clicked");
        } else {
            throw new NoSuchElementException(" createSportsQulif not found or not visible.");
        }

        // Use Robot class for keyboard input
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Move to another element
        Thread.sleep(4000);

        if (createInstitue.isDisplayed()&& createInstitue !=null) {
            actions.moveToElement(createInstitue).click().perform();
            System.out.println(" Moved to createInstitute and clicked");
        } else {
            throw new NoSuchElementException(" createInstitute not found.");
        }

        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        // Enter the date
        if (date.isDisplayed()&& date!=null) {
           
            date.sendKeys("27/02/2010");
            System.out.println(" Entered date: 27/02/2010");
        } else {
            throw new NoSuchElementException(" Date input field not found.");
        }
        Thread.sleep(2000);

        if (about.isDisplayed() && about != null)
        {
            about.clear();
            about.sendKeys("Lorem  Lorem");
        }
        scrollToElement(saveUpdateButton);
        clickElement(saveUpdateButton);
        Thread.sleep(2000);


    }

    

    

}
