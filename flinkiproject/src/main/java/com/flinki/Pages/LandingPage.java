package com.flinki.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flinki.Base.base;

import java.awt.*;



public class LandingPage extends base {
   

    /////////////////////////////////////////////////////////////////////Constructor////////////////////////////////////////////////////////////////////////////////////////
    public  LandingPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    

    /////////////////////////////////////////////////////////Locators//////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "(//span[@id='basic-addon1']/following-sibling::input)[1]")//// Search Box
    WebElement searchboxEl1;

    @FindBy(xpath = "(//span[@id='basic-addon1']/following-sibling::input)[2]")//// Search Box Connect
    WebElement searchboxEl2;

    @FindBy(xpath = "(//span[@id='basic-addon1']/following-sibling::input)[3]")//// Search Box Races
    WebElement searchboxEl3;

    @FindBy(xpath = "(//span[@id='basic-addon1']/following-sibling::input)[4]")//// Search Box Events
    WebElement searchboxEl4;


    @FindBy(xpath = "//button[@id='connect-tab']")//// Search Connect Tab
    WebElement searchconnectEl;

    @FindBy(xpath = "//button[@id='races-tab']")/// Search Races Tab
    WebElement searchraceandeventsEl;

    @FindBy(xpath = "//button[@id='marketplace-tab']") ///////Search Race Tab
    WebElement searchmarketEl;

    @FindBy(xpath = "//input[@value='Search']")////// Search Button
    WebElement searchbtnEl;

    @FindBy(xpath = "(//a[@class='btn'])[2]") //////View all blogs
    WebElement ViewEl;





    /////////////////////////////////////////////////////////////////Methods//////////////////////////////////////////////////////////////////////////////////
    
    public void Searchprofilefx()
    {
        searchboxEl1.click();
        searchboxEl1.sendKeys("Tom");
        searchbtnEl.click();
    }

    public void SearchConnectfx()
    {
        searchconnectEl.click();
        searchboxEl2.click();
        searchboxEl2.sendKeys("Harry");
        searchbtnEl.click();
    }

    public void SearchRaceandEventsfx()
    {
        searchraceandeventsEl.click();
        searchboxEl3.click();
        searchboxEl3.sendKeys("Race");
        searchbtnEl.click();
    }

    public void SearchMarketfx()
    {
        searchmarketEl.click();
        searchboxEl4.click();
        searchboxEl4.sendKeys("Race");
        searchbtnEl.click();
    }


    public void Viewblogsfx() throws AWTException
    {
        Robot rb = new Robot();
        rb.mouseWheel(8);
        ViewEl.click();
    }




    

}
