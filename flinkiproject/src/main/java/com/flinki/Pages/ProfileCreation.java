package com.flinki.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.flinki.Base.BasePage;
import com.flinki.Base.base;
import com.flinki.utils.Log;
import com.github.javafaker.Faker;


public class ProfileCreation extends base {
    private static final Logger logger = Log.getLogger(ProfileCreation.class); 
    // Removed incorrect KeyEvent declaration
        SignUp su = new SignUp(driver);
        Faker faker = new Faker();
      
        // Actions actions = new Actions(driver);
        BasePage bs = new BasePage(driver);
        public ProfileCreation(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    
        /////////////////////////////////////////////////////////////////////Locators////////////////////////////////////////////////////////////////////////////////////////
    
        @FindBy(xpath = "//input[@placeholder = 'Enter first name']")
        WebElement FirstName;
        @FindBy(xpath = "//input[@placeholder = 'Enter last name']")
        WebElement LastName;
        @FindBy(name = "country")
        WebElement Country;
        @FindBy(name = "nationality")
        WebElement Nationality;
    
        @FindBy(xpath = "//ul[@class='check-group-wrap']/li[not(label[normalize-space()='Other'])]")
        List<WebElement> sportsIntersted;
    
        @FindBy(xpath = "//button[normalize-space()='Save & Next']")
        private WebElement SaveNextButton;
    
        @FindBy(xpath = "//input[@name='dateOfBirth']")
        private WebElement datOfBirth;
    
        @FindBy(xpath = "//button[normalize-space()='Save & Next']")
        private WebElement saveNextButton;
    
        @FindBy(xpath = "//button[normalize-space()='Next']")
        private WebElement nextButton;

        @FindBy(xpath = "//div[contains(text(),'First name is required')]")
        private WebElement firstNameError;

        @FindBy(xpath = "//div[contains(text(),'Last name is required')]")
        private WebElement lastNameError;

        @FindBy(xpath = "//div[contains(text(),'Country is required')]")
        private WebElement countryError;

        @FindBy(xpath = "//div[contains(text(),'Nationality is required')]")
        private WebElement nationalityError;

        @FindBy(xpath = "//div[contains(text(),'Date of Birth is required')]")
        private WebElement dobError;
    
        @FindBy(xpath = "//button[normalize-space()='Add New Race/Event']")
        private WebElement addNewRaceEvent;

        @FindBy(xpath = "//div[contains(text(),'Race/Event name is required')]")
        private WebElement raceEventError;
        @FindBy(xpath = "//div[contains(text(),'Start date is required')]")
        private WebElement startDateError;
        @FindBy(xpath = "//div[contains(text(),'End date is required')]")
        private WebElement enddateError;
        @FindBy(xpath = "//div[contains(text(),'Sports qualification is required')]")
        private WebElement sportsError;
        @FindBy(xpath = "//div[contains(text(),'Institution is required')]")
        private WebElement instituteError;
        @FindBy(xpath = "//div[contains(text(),'Date is required')]")
        private WebElement sportsDateError;
        @FindBy(xpath = "//div[@class='css-19bb58m']")
        private WebElement typeOfCreate;
    
        @FindBy(xpath = "//input[@placeholder='Start date']")
        private WebElement startDate;
        @FindBy(xpath = "//input[@placeholder='End date']")
        private WebElement endDate;
    
        @FindBy(xpath = "//input[@name='myfile']")
        private WebElement uploadPhoto;
    
        @FindBy(xpath = "//button[normalize-space()='Save']")
        private WebElement saveButton;

        @FindBy(id = "react-select-2-placeholder")
        private WebElement selectTypeOfCreate;
        @FindBy(xpath="//button[normalize-space()='Add New Qualifications']")
        private WebElement addNewQulifi;

        @FindBy(xpath="(//div[@class='css-hlgwow'])[1]")
        private WebElement createSportsQulif;

        @FindBy(xpath = "(//div[@class='css-hlgwow'])[2]")
        private WebElement createInstitute;
        @FindBy(xpath="//input[@name='date']")
        private WebElement date;
        @FindBy(xpath="//textarea[@id='about']")
        private WebElement about;

        @FindBy(xpath = "//button[normalize-space()='Save & Update']")
        private WebElement saveUpdateButton;

        @FindBy(xpath = "//div[@class='jsx-faece91221e6c5e6 popup-actions-card']//input[@id='flexCheckDefault']")
        private WebElement checkBox;

        @FindBy(xpath = "//button[normalize-space()='Continue To Standard plan']")
        private WebElement standradPlan;

        @FindBy(xpath = "//div[@class='jsx-faece91221e6c5e6 popup-actions']")
        private WebElement popUpScroll;


    
        /////////////////////////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////////////////////////////
        public ProfileCreation CreatePersonalInformation() throws Exception {
            // su.SignupWithTCfx();///////Signup function is processsed
            logger.info("Nevigate to Profile Creation page ");
            bs.uploadImage(uploadPhoto, "Dodge car.jpg");
            logger.info("Upload Profile Photo ");
            FirstName.sendKeys(faker.name().firstName());
            logger.info("Enter First Random Name ");
            LastName.sendKeys(faker.name().lastName());
            logger.info("Enter Last Random  Name ");
            Thread.sleep(1000);
            ////////Country dropdown
            Select selectcountry = new Select(Country);
            List<WebElement> optionsCountry = selectcountry.getOptions();
            Thread.sleep(3000);
            Random rand = new Random();
            int randomIndex = rand.nextInt(optionsCountry.size() - 1) + 1; // Avoid index 0 if it's a placeholder
            selectcountry.selectByIndex(randomIndex);
            logger.info("Select Random Country ");
            // datOfBirth.sendKeys("27/02/2010");
            Thread.sleep(1000);
            datOfBirth.sendKeys(bs.generateRandomDOB(13, 50));
            logger.info("Enter Date of Birth ");
            saveNextButton.click();
            logger.info("Enter Next Buton ");
            Thread.sleep(3000);
            return this;
            
        }
    
        /*---------------------------------------------------------RACE  & EVENTS------------------------------------------------------------------------- */
        public ProfileCreation CreateRacesEvents() throws InterruptedException
    
        {
            Thread.sleep(3000);
            int totalSports = sportsIntersted.size();
            logger.info("Total Available Sports" + totalSports);

                 if (totalSports == 0) {
                     logger.warn("No Sports Found " + totalSports);
                 return this;
                     }

    // Limit the number of selections to available options
            int maxToSelect = Math.min(4, totalSports);
             Random rand = new Random();

             for (int i = 0; i < maxToSelect; i++) {
             int randomIndex = rand.nextInt(sportsIntersted.size()); // Safe now
              WebElement sport = sportsIntersted.get(randomIndex);
              sport.click(); // Click the random sport
              logger.info("Chouse Sports ");
            sportsIntersted.remove(randomIndex); // Remove to avoid duplicate clicks
    }

    SaveNextButton.click();
    logger.info("Click Save & Next Button");
    Thread.sleep(3000);
    return this;


    }
                
    
        
        /*-----------------------------ADD NEW RACE EVENT [If user click  this button ]--------------------------- */
    
        public void  CreateAddNewraceEvent() throws InterruptedException, AWTException
    
        {   
            // JavascriptExecutor js = (JavascriptExecutor) driver;
            // js.executeScript("document.body.style.zoom=60%'");
            logger.info("Upload Profile Photo ");
            addNewRaceEvent.click();
            logger.info("Click add New race events");
            Thread.sleep(3000);
             Actions actions = new Actions(driver);
             actions.moveToElement(selectTypeOfCreate).click().perform();
             Robot robot = new Robot();
             robot.keyPress(KeyEvent.VK_DOWN);
             robot.keyRelease(KeyEvent.VK_DOWN);
             Thread.sleep(500);
             robot.keyPress(KeyEvent.VK_ENTER);
             robot.keyRelease(KeyEvent.VK_ENTER);
             logger.info("Create events");
             Thread.sleep(1000);
             startDate.sendKeys(bs.getRandomStartAndEndDate(0));
             logger.info("Enter Start date Of Create Events ");
        Thread.sleep(2000);
        endDate.sendKeys(bs.getRandomStartAndEndDate(120));
        logger.info("Enter End  date Of Create Events ");
        Thread.sleep(2000);
        bs.scrollToElement(saveButton);
        Thread.sleep(2000);
        bs.uploadImage(uploadPhoto, "image.png");
        logger.info("Upload Photo");
        Thread.sleep(2000);
        saveButton.click();
        logger.info("Click Save Button");
        Thread.sleep(3000);
        nextButton.click();

        
        Thread.sleep(4000);
        


    }

    /* ----------------------------------------SportsQualifications-----------------------------------------------*/
    public ProfileCreation SportsQualifications() throws InterruptedException {
        nextButton.click();
        nextButton.click();
        nextButton.click();
        nextButton.click();
        // Locate the "Standard Plan" section using XPath
        WebElement standardPlanSection = driver.findElement(
                By.xpath("//div[contains(@class, 'jsx-facee91221e6c5e6') and contains(@class, 'col-md-6')]"));

        // Scroll the section into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", standardPlanSection);

        // Wait to ensure the section is fully visible
        Thread.sleep(4000);

        return this;
    }
    


    public ProfileCreation CreateAddNewQulifiactions() throws AWTException, InterruptedException

    {
        try {
            Thread.sleep(2000);

            // Ensure JavaScriptExecutor is initialized
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (js == null) {
                throw new RuntimeException("JavaScript Executor is not initialized properly");
            }
            js.executeScript("document.body.style.zoom='80%'");

            // Click on qualification button
            if (addNewQulifi.isDisplayed()) {
                addNewQulifi.click();
                logger.info(" Clicked on Add New Qualifiction");
            } else {
                throw new NoSuchElementException("  Add New Qualifictioni button not found.");
            }
            Thread.sleep(4000);

            // Move to element and perform action
            Actions actions = new Actions(driver);

            if (createSportsQulif != null && createSportsQulif.isDisplayed()) {
                actions.moveToElement(createSportsQulif).click().perform();
                logger.info(" Moved to createSportsQulif and clicked");
                
            } else {
                logger.warn(" createSportsQulif not found or not visible.");
                throw new NoSuchElementException();
            }

            // Use Robot class for keyboard input
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Move to another element
            Thread.sleep(4000);
            if (createInstitute.isDisplayed()&& createInstitute !=null) {
                actions.moveToElement(createInstitute).click().perform();
                logger.info("Moved to createInstitute and clicked");
            } else {
                logger.warn(" createInstitute not found.");
                throw new NoSuchElementException();
            }

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            // Enter the date
            if (date.isDisplayed()&& date!=null) {
               
                date.sendKeys(bs.generateRandomDOB(15, 20));
            } else {
                logger.warn("Date input field not found.");
                throw new NoSuchElementException();
            }
            Thread.sleep(2000);

            if (about.isDisplayed() && about != null)
            {
                about.clear();
                about.sendKeys("Lorem  Lorem");
            }
            // Click Save and Next
            if (saveUpdateButton.isDisplayed()&& saveUpdateButton!=null) {
                saveUpdateButton.click();
                Thread.sleep(3000);
                System.out.println(" Clicked Save Update Button");
            } else {
                throw new NoSuchElementException(" saveUpdateButton not found.");
            }
            Thread.sleep(2000);
            if (nextButton.isDisplayed()) {
                nextButton.click();
                System.out.println(" Clicked Next Button");
            } else {
                throw new NoSuchElementException(" nextButton not found.");
            }
            Thread.sleep(3000);

        } catch (NoSuchElementException e) {
            System.err.println(" Element Not Found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(" Thread Interrupted: " + e.getMessage());
        } catch (AWTException e) {
            System.err.println(" Robot Class Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" Unexpected Error: " + e.getMessage());
        }
        Thread.sleep(2000);
        return this;
    }

    public ProfileCreation ClickplanToUpgrade() throws InterruptedException {
        try {

            //div[@class='jsx-faece91221e6c5e6 popup-actions']
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollBy(0, 200);", popUpScroll);
            Thread.sleep(2000);

            if (checkBox.isDisplayed()) {
                checkBox.click();
                System.out.println(" Checked the plan checkbox.");
            } else {
                throw new NoSuchElementException(" Plan checkbox not found.");
            }

            if (standradPlan.isDisplayed()) {
                standradPlan.click();
                System.out.println(" Clicked on Standard Plan.");
            } else {
                throw new NoSuchElementException(" Standard Plan option not found.");
            }

        } catch (NoSuchElementException e) {
            System.err.println(" Element Not Found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" Unexpected Error: " + e.getMessage());
        }
        Thread.sleep(4000);
        return this;
    }
    
//=================================Perosnal Information All mandatrory field error message is verify=========================
    public boolean allMandatoryFieldErrors() {
        saveNextButton.click();
        logger.info("Click on Save Next Button ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        boolean allErrorsVisible = firstNameError.isDisplayed() &&
                lastNameError.isDisplayed() &&
                countryError.isDisplayed() &&
                nationalityError.isDisplayed() &&
                dobError.isDisplayed();

        if (!allErrorsVisible) {
            logger.error("One or more error messages not displayed properly.");
        } else {
            logger.info("All mandatory field error messages are displayed.");
        }

        return allErrorsVisible;
    }

//===============================================All Error Message is Verifyed in Race Event Page 
public boolean allmandatoryFieldErrorInRaceEven() throws InterruptedException
    
{
    addNewRaceEvent.click();
    Thread.sleep(2000);
    bs.scrollToElement(saveButton);
    Thread.sleep(2000);
    saveButton.click();
    Thread.sleep(2000);
    bs.scrollToElement(raceEventError);
    logger.info("Click on Save Next Button ");
    Thread.sleep(2000);
    boolean allerrorMessage = raceEventError.isDisplayed() &&
            startDateError.isDisplayed()
            && enddateError.isDisplayed();
    if (!allerrorMessage) {
        logger.error("One or more error messages not displayed properly.");

    } else {
        logger.info("All mandatory field error messages are displayed.");

    }

    return allerrorMessage;
}

public boolean allmandatoryFiledErrorInSports() throws InterruptedException
{
    addNewQulifi.click();
    Thread.sleep(2000);
    bs.scrollToElement(saveUpdateButton);
    Thread.sleep(2000);
    saveUpdateButton.click();
    bs.scrollToElement(sportsDateError);
    boolean allerrorMessage = sportsError.isDisplayed() && instituteError.isDisplayed()
            && sportsDateError.isDisplayed();
            if(!allerrorMessage)
            {
                logger.info("One or more error messages not displayed properly");
            } else {
                logger.info("All mandatory field error messages are displayed.");

            }
            return allerrorMessage;


}
    
}
