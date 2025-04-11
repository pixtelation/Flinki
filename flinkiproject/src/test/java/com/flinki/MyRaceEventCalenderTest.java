package com.flinki;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flinki.Base.base;
import com.flinki.Pages.Homepage;
import com.flinki.Pages.MyRaceEventCalender;
import com.flinki.Pages.ProfilePage;
import com.flinki.Pages.SignUp;

@Listeners(TestListener.class)
public class MyRaceEventCalenderTest extends base {

    private SignUp signup;
    private ProfilePage profilePage;
    private Homepage homepage;
    private MyRaceEventCalender myRaceEvent;

    @BeforeMethod
    public void testSetup() {
        signup = new SignUp(driver);
        profilePage = new ProfilePage(driver);
        homepage = new Homepage(driver);
        myRaceEvent = new MyRaceEventCalender(driver);

    }

    @Test(priority=1, groups = "Functional")
    public void userCanAbleToCreateNewEvents() throws InterruptedException
    {
        signup.SignIn();
        homepage.clickMyRaceEventCalender();
        myRaceEvent.clickElement(myRaceEvent.createEvent);
        Thread.sleep(2000);
        myRaceEvent.uploadCoverPhoto("dodgeCoverPhoto.jpg");
        myRaceEvent.uploadProfilePhoto("Profile.jpg");
        myRaceEvent.getSelectVisliblity().enterEventName().chouseSports();



    }

}
