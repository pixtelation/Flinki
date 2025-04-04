package com.flinki.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flinki.Base.BasePage;
import com.github.javafaker.Faker;

public class ProfilePage extends BasePage {
     Faker faker = new Faker();

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    /*----------------------------------------------------------XPATH-------------------------------- */
    
    @FindBy(xpath = "//input[@id='cover-upload']")
    private WebElement coverPhoto;

    @FindBy(xpath = "//div[contains(@class, 'Toastify__toast--success')]//div[contains(text(), 'cover image updated successfully')]")
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

    @FindBy(xpath = "//button[normalize-space()='Add New Sports Qualifications']")
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

    // @FindBy(xpath = "//button[normalize-space()='Save']")
    // private WebElement saveButton;

/*---------------------------------------------METHOD--------------------- */

public String getUserSaveSucessMessage()
{
    return getElementText(userSaveSucessfully);
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
{
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

// Upload cover photo in profile section
    public ProfilePage uploadCoverPhoto() {
        String imagePath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", "flinki",
                "Resources", "Images", "image.png").toAbsolutePath().toString();
        enterText(coverPhoto, imagePath);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        enterText(phoneNumber, randomnumber());
        enterText(email, randomemail());
        scrollToElement(saveButton);
        clickElement(saveButton);

    }



}
