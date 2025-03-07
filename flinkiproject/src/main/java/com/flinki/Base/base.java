package com.flinki.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class base {
   public WebDriver driver;

    @SuppressWarnings("deprecation")
    @BeforeClass
    public void Start()
    {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/wadmin/AppData/Local/Google/Chrome/User Data");
        options.addArguments("profile-directory=Default"); // Change if using a different profile
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://sportsxcl-cms.weavers-web.com/");
    }


    // @AfterClass
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }

    
}
