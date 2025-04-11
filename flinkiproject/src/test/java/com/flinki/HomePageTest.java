package com.flinki;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.Homepage;
import com.flinki.Pages.MyRaceEventCalender;
import com.flinki.Pages.ProfilePage;
import com.flinki.Pages.SignUp;

@Listeners(TestListener.class)
public class HomePageTest extends base {
    private SignUp signup;
    private ProfilePage profilePage;
    private Homepage homepage;
    private MyRaceEventCalender myRaceEvent;

    @BeforeMethod
public void testSetup()
{
    signup = new SignUp(driver);;
    profilePage = new ProfilePage(driver);
    homepage = new Homepage(driver);
    myRaceEvent = new MyRaceEventCalender(driver);

}
@Test(priority=1,  groups="Functional")
public void verifyAllMandatoryFieldsErrorInRaceEvents() throws Exception
{
    signup.SignIn();
    homepage.clickMyRaceEventCalender();
    myRaceEvent.clickElement(myRaceEvent.createEvent);
    Thread.sleep(3000);
    myRaceEvent.scrollToElement(myRaceEvent.createButton);
    myRaceEvent.clickElement(myRaceEvent.createButton);
    Thread.sleep(3000);
    Assert.assertTrue(myRaceEvent.verifyAllMandatoryFieldErrors(), "Not all error messages are displayed!");
    homepage.signOut();

}
@Test(priority=2, groups="Functional")
public void userCanprintProductAndPrice() throws InterruptedException
{
    signup.SignIn();
    homepage.clickMyListing();
    Thread.sleep(3000);
    homepage.printProductListAndPrice();
    

}





}