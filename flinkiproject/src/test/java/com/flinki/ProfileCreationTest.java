package com.flinki;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.ProfileCreation;
import com.flinki.Pages.ProfilePage;
import com.flinki.Pages.SignUp;
import com.flinki.utils.Data;
import com.flinki.utils.ExcelUtils;

@Listeners(TestListener.class)
public class ProfileCreationTest extends base {
    private SignUp signup;
    private ProfileCreation profilecreation;
    private ProfilePage profilePage;
   
    
    

    @BeforeMethod
public void testSetup()
{
    signup = new SignUp(driver);
    profilecreation = new ProfileCreation(driver);
    profilePage = new ProfilePage(driver);
   
}
    @Test(priority=1)
    public void verifyAllErrorMessageInProfileCreation() throws Exception
    {    signup.SignupWithTCfx();
        Assert.assertTrue(profilecreation.allMandatoryFieldErrors(), "Some error messages are missing");
       
    }


    @Test(priority=2)
    public void VerifyUserCanCreateProfileCreation() throws Exception
    {

        signup.SignupWithTCfx();
        profilecreation.CreatePersonalInformation();
        profilecreation.CreateRacesEvents();
        profilecreation.CreateAddNewraceEvent();
        profilecreation.CreateAddNewQulifiactions();
        profilecreation.ClickplanToUpgrade();
        ExcelUtils.writeToCell("flinkiproject/src/main/Resources/String.xlsx", "TestData", 1, 0, Data.getRandomEmail());

    }

    @Test(priority = 3)
    public void verifyAllMandatoryErrorMessageInRacesEvents() throws Exception
    {
        signup.SignupWithTCfx();
        profilecreation.CreatePersonalInformation();
        profilecreation.CreateRacesEvents();
        Assert.assertTrue(profilecreation.allmandatoryFieldErrorInRaceEven(), "Some error messages are missing");
    }

    @Test(priority = 3)
    public void verifyAllMandatoryErrorMesssageInSports() throws Exception
    {
        signup.SignupWithTCfx();
        profilecreation.CreatePersonalInformation();
        profilecreation.CreateRacesEvents();
        profilecreation.CreateAddNewraceEvent();
        Assert.assertTrue(profilecreation.allmandatoryFiledErrorInSports(), "Some error messages are missing");
    }

    

    @Test(priority = 4)
    public void VerifyUserCanEditName() throws InterruptedException
    {
        signup.SignIn().clickProfile().clickEditButton();
        Thread.sleep(3000);
        profilePage.editPersonalNameInformation();

    }
    
    @Test(priority = 5)
    public void VerifyUserCanEditSports() throws InterruptedException, AWTException
    {
        signup.SignIn().clickProfile();
        profilePage.editPersonalSportsInformation();

    }

    @Test(priority = 6)
    public void VerifyUsercanEditContactDetails() throws InterruptedException
    {
        signup.SignIn().clickProfile();
        profilePage.EditContactDetails();

    }

    
    
    
    
}
