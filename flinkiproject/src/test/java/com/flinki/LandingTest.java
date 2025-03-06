package com.flinki;
import java.awt.AWTException;

import org.testng.annotations.*;


import com.flinki.Base.base;
import com.flinki.Pages.LandingPage;

public class LandingTest extends base
{   
    

//////////Search on Landing page
@Test
public void SearchProfile()
{
 LandingPage lg = new LandingPage(driver);
 lg.Searchprofilefx();
}


@Test
public void SearchConnect()
{
 LandingPage lg = new LandingPage(driver);
 lg.SearchConnectfx();   
}


@Test
public void SearchRaces()
{
    LandingPage lg = new LandingPage(driver);
    lg.SearchRaceandEventsfx();
}


@Test
public void SearchMarket()
{
    LandingPage lg = new LandingPage(driver);
    lg.SearchMarketfx();
}



///////////////////View Blogs    
@Test
public void Testview() throws AWTException
{
    LandingPage lg = new LandingPage(driver);
 lg.Viewblogsfx();
    
}

}
