package com.flinki.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.flinki.Base.base;
import com.flinki.Resources.Generic;
import com.github.javafaker.Faker;


public class ProfileCreation extends base {
    // Removed incorrect KeyEvent declaration
        SignUp su = new SignUp(driver);
        Faker faker = new Faker();
        Generic Profile = new Generic();
        // Actions actions = new Actions(driver);
        
      
        
    
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
    
        @FindBy(xpath = "//button[normalize-space()='Add New Race/Event']")
        private WebElement addNewRaceEvent;
    
        @FindBy(xpath = "//div[@class='css-19bb58m']")
        private WebElement typeOfCreate;
    
        @FindBy(xpath = "//input[@placeholder='Start date']")
        private WebElement startDate;
        @FindBy(xpath = "//input[@placeholder='End date']")
        private WebElement endDate;
    
        @FindBy(xpath = "//input[@type='file']")
        private WebElement fileUpload;
    
        @FindBy(xpath = "//button[normalize-space()='Save']")
        private WebElement saveButton;

        @FindBy(id = "react-select-2-placeholder")
        private WebElement selectTypeOfCreate;
        @FindBy(xpath="//button[normalize-space()='Add New Qualifications']")
        private WebElement addNewQulifi;

        @FindBy(xpath="(//div[@class='css-hlgwow'])[1]")
        private WebElement createSportsQulif;

        @FindBy(id = "react-select-7-placeholder")
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


    
        /////////////////////////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////////////////////////////
        public ProfileCreation PersonalInformation() throws Exception {
            // su.SignupWithTCfx();///////Signup function is processsed
            FirstName.sendKeys(faker.name().firstName());
            LastName.sendKeys(faker.name().lastName());
            Thread.sleep(3000);
            ////////Country dropdown
            Select selectcountry = new Select(Country);
            List<WebElement> optionsCountry = selectcountry.getOptions();
            Thread.sleep(3000);
            Random rand = new Random();
            int randomIndex = rand.nextInt(optionsCountry.size() - 1) + 1; // Avoid index 0 if it's a placeholder
            selectcountry.selectByIndex(randomIndex);
            datOfBirth.sendKeys("27/02/2010");
            saveNextButton.click();
            return this;
    
        }
    
        /*---------------------------------------------------------RACE  & EVENTS------------------------------------------------------------------------- */
        public ProfileCreation RacesEvents()
    
        {
    
            System.out.println("Available sports: " + sportsIntersted.size());
    
            Random rand = new Random();
    
            for (int i = 0; i < 4; i++) {
                int randomIndex = rand.nextInt(sportsIntersted.size()); // Pick a random index
                WebElement sport = sportsIntersted.get(randomIndex);
                sport.click(); // Click the random sport
                sportsIntersted.remove(randomIndex); // Remove to avoid duplicate clicks
            }
    
            SaveNextButton.click();
            return this;
        }
                
    
        
        /*-----------------------------ADD NEW RACE EVENT [If user click  this button ]--------------------------- */
    
        public ProfileCreation addNewraceEvent() throws InterruptedException, AWTException
    
        {   
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='60%'");
    
            addNewRaceEvent.click();
            Thread.sleep(2000);
             Actions actions = new Actions(driver);
             actions.moveToElement(selectTypeOfCreate).click().perform();
             Robot robot = new Robot();
             robot.keyPress(KeyEvent.VK_DOWN);
             robot.keyRelease(KeyEvent.VK_DOWN);
             Thread.sleep(500);
             robot.keyPress(KeyEvent.VK_ENTER);
             robot.keyRelease(KeyEvent.VK_ENTER);

        startDate.sendKeys("20/03/2025");
        Thread.sleep(2000);
        endDate.sendKeys("04/04/2025");
        Thread.sleep(4000);
        // String imagepath = "./src/main/java/com/flinki/Resources/Images/image.png";
        // Thread.sleep(2000);
        // fileUpload.sendKeys(imagepath);
        saveButton.click();
        Thread.sleep(3000);
        nextButton.click();

        
    
        return this;


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
        Thread.sleep(2000);

        return this;
    }
    


    public ProfileCreation addNewQulifiactions() throws AWTException, InterruptedException

    {
        try {
            Thread.sleep(2000);

            // Ensure JavaScriptExecutor is initialized
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (js == null) {
                throw new RuntimeException("JavaScript Executor is not initialized properly");
            }
            js.executeScript("document.body.style.zoom='60%'");

            // Click on qualification button
            if (addNewQulifi.isDisplayed()) {
                addNewQulifi.click();
                System.out.println("✅ Clicked on addNewQualifi");
            } else {
                throw new NoSuchElementException("❌ addNewQulifi button not found.");
            }
            Thread.sleep(4000);

            // Move to element and perform action
            Actions actions = new Actions(driver);

            if (createSportsQulif != null && createSportsQulif.isDisplayed()) {
                actions.moveToElement(createSportsQulif).click().perform();
                System.out.println("✅ Moved to createSportsQulif and clicked");
            } else {
                throw new NoSuchElementException("❌ createSportsQulif not found or not visible.");
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
            if (createInstitute.isDisplayed()) {
                actions.moveToElement(createInstitute).click().perform();
                System.out.println("✅ Moved to createInstitute and clicked");
            } else {
                throw new NoSuchElementException("❌ createInstitute not found.");
            }

            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Enter the date
            if (date.isDisplayed()) {
                date.clear();
                date.sendKeys("27/02/2010");
                System.out.println("✅ Entered date: 27/02/2010");
            } else {
                throw new NoSuchElementException("❌ Date input field not found.");
            }

            // Click Save and Next
            if (saveUpdateButton.isDisplayed()) {
                saveUpdateButton.click();
                System.out.println("✅ Clicked Save Update Button");
            } else {
                throw new NoSuchElementException("❌ saveUpdateButton not found.");
            }

            if (nextButton.isDisplayed()) {
                nextButton.click();
                System.out.println("✅ Clicked Next Button");
            } else {
                throw new NoSuchElementException("❌ nextButton not found.");
            }

        } catch (NoSuchElementException e) {
            System.err.println("🚨 Element Not Found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("🚨 Thread Interrupted: " + e.getMessage());
        } catch (AWTException e) {
            System.err.println("🚨 Robot Class Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("🚨 Unexpected Error: " + e.getMessage());
        }

        return this;
    }

    public void planToUpgrade() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='60%'");

            if (checkBox.isDisplayed()) {
                checkBox.click();
                System.out.println("✅ Checked the plan checkbox.");
            } else {
                throw new NoSuchElementException("❌ Plan checkbox not found.");
            }

            if (standradPlan.isDisplayed()) {
                standradPlan.click();
                System.out.println("✅ Clicked on Standard Plan.");
            } else {
                throw new NoSuchElementException("❌ Standard Plan option not found.");
            }

        } catch (NoSuchElementException e) {
            System.err.println("🚨 Element Not Found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("🚨 Unexpected Error: " + e.getMessage());
        }
    }


}
