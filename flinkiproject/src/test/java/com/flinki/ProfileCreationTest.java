package com.flinki;

import java.awt.AWTException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.ProfileCreation;
import com.flinki.Pages.ProfilePage;
import com.flinki.Pages.SignUp;

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
    public void VerifyUserCanCreateProfileCreation() throws Exception
    {
       
        signup.SignupWithTCfx();
        profilecreation.CreatePersonalInformation();
        profilecreation.CreateRacesEvents();
        profilecreation.CreateAddNewraceEvent().CreateAddNewQulifiactions().ClickplanToUpgrade(); 
    }

    @Test(priority = 2)
    public void VerifyUserCanEditName() throws InterruptedException
    {
        signup.SignIn().clickProfile().clickEditButton();
        Thread.sleep(3000);
        profilePage.editPersonalNameInformation();

    }
    
    @Test(priority = 3)
    public void VerifyUserCanEditSports() throws InterruptedException, AWTException
    {
        signup.SignIn().clickProfile();
        profilePage.editPersonalSportsInformation();

    }

    @Test(priority = 4)
    public void VerifyUsercanEditContactDetails() throws InterruptedException
    {
        signup.SignIn().clickProfile();
        profilePage.EditContactDetails();

    }
    
    
    
    
}
