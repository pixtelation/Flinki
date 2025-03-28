package com.flinki.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            
            addNewRaceEvent.click();
            Thread.sleep(2000);
            Robot robot = new Robot();
            Thread.sleep(2000);
            robot.keyPress(java.awt.event.KeyEvent.VK_DOWN);
            robot.keyRelease(java.awt.event.KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        startDate.sendKeys("20/03/2025");
        Thread.sleep(2000);
        endDate.sendKeys("04/04/2025");
        Thread.sleep(4000);
        // String imagepath = "./src/main/java/com/flinki/Resources/Images/image.png";
        // Thread.sleep(2000);
        // fileUpload.sendKeys(imagepath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
       
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        saveButton.click();
      

        



        
        
     

        

        
        
    
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

}
