package com.flinki.Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager; 

public class base {
   
   
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }



    @Parameters("browser")
    @BeforeMethod
    public void Start()
    {

        

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.setExperimentalOption("debuggerAddress", "localhost:64379");
        driver = new ChromeDriver(options);
        driver.get("https://sportsxcl-cms.weavers-web.com/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


   

    
}
