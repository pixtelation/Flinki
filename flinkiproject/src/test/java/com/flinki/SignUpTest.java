package com.flinki;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.SignUp;


@Listeners(TestListener.class)
public class SignUpTest extends base{


@Test //////////Without T&C
public void SignupWithoutTC()
{
    SignUp sn = new SignUp(driver);
    sn.SignupWithoutTCfx();
    Assert.assertTrue(sn.errorMessageDisplayed(), "Not displayed ");
    Assert.assertEquals(sn.getErrorMessage(), "You must agree to the Privacy Policy & Terms.","Error message not matched ");
    
}


@Test //////////With T&C
public void SignupWithTC() throws  Exception
{
    SignUp sn = new SignUp(driver);
    sn.SignupWithTCfx();
}



    
}
