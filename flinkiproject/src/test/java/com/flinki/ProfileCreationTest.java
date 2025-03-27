package com.flinki;

import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.ProfileCreation;
import com.flinki.Pages.SignUp;

public class ProfileCreationTest extends base {


    @Test
    public void ProfileCreation() throws Exception
    {
     SignUp sn = new SignUp(driver);
     ProfileCreation pf = new ProfileCreation(driver);  
     sn.SignupWithTCfx();
     pf.PersonalInformation().RacesEvents().addNewraceEvent();
    }
    
}
