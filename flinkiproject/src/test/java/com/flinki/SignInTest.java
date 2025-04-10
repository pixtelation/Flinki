package com.flinki;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.SignIn;
@Listeners(TestListener.class)
public class SignInTest extends base {

     @Test
    public void SignIn()
    {
        SignIn sn = new SignIn(driver);
        sn.SignInfx();

    }
    
    
    
}
