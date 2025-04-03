package com.flinki.Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager; 

public class base {
   public WebDriver driver;

    @SuppressWarnings("deprecation")
    @BeforeClass
    public void Start()
    {

        //ChromeOptions options = new ChromeOptions();
        // options.addArguments("user-data-dir=C:/Users/wadmin/AppData/Local/Google/Chrome/User Data");
        // options.addArguments("profile-directory=Default"); // Change if using a different profile
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        // driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // driver.get("https://sportsxcl-cms.weavers-web.com/");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("debuggerAddress", "localhost:46443");
        driver = new ChromeDriver(options);
        driver.get("https://sportsxcl-cms.weavers-web.com/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'");

    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


   

    
}
