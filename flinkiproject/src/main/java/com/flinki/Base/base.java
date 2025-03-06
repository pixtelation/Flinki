package com.flinki.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class base {
   public WebDriver driver;

    @SuppressWarnings("deprecation")
    @BeforeClass
    public void Start()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
