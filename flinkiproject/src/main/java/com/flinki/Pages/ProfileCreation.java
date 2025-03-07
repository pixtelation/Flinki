package com.flinki.Pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.github.javafaker.Faker;

import com.flinki.Base.base;


public class ProfileCreation extends base {
     SignUp su = new SignUp(driver);    
     Faker faker = new Faker();


    public ProfileCreation(WebDriver driver)
    {
        this.driver=driver;
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
    

     /////////////////////////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////////////////////////////
    public void PersonalInformation() throws Exception
    {
        // su.SignupWithTCfx();///////Signup function is processsed
        FirstName.sendKeys(faker.name().firstName());
        LastName.sendKeys(faker.name().lastName());
        ////////Country dropdown
        Select selectcountry = new Select(Country);
        List<WebElement> optionsCountry = selectcountry.getOptions();
        if (optionsCountry.size() > 0 && optionsCountry.get(0).getText().equals("Select Country")) {
            optionsCountry.remove(0);
        }

        if (optionsCountry.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(optionsCountry.size());
            WebElement randomOption = optionsCountry.get(randomIndex);
            selectcountry.selectByVisibleText(randomOption.getText());
            System.out.println("SelectedCountry: " + randomOption.getText()); //Optional, for verification
        } else {
            System.out.println("Dropdown has no options.");
        }


        ////////Nationality dropdown
        Select select = new Select(Nationality);
        List<WebElement> options = select.getOptions();
        if (options.size() > 0 && options.get(0).getText().equals("Select Country")) {
            options.remove(0);
        }

        if (options.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(options.size());
            WebElement randomOption = options.get(randomIndex);
            select.selectByVisibleText(randomOption.getText());
            System.out.println("SelectedNationality: " + randomOption.getText()); //Optional, for verification
        } else {
            System.out.println("Dropdown has no options.");
        }




        



    }
    
   
    
}
