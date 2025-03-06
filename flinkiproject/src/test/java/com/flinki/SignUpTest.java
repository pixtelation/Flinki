package com.flinki;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.SignUp;



public class SignUpTest extends base{


@Test //////////Without T&C
public void SignupWithoutTC()
{
    SignUp sn = new SignUp(driver);
    sn.SignupWithoutTCfx();
}


@Test //////////With T&C
public void SignupWithTC() throws  Exception
{
    SignUp sn = new SignUp(driver);
    sn.SignupWithTCfx();
}



    
}
