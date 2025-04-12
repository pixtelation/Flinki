package com.flinki.Base;

import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Existingbrowser {

    // If you want to run your code in the same existing browser, follow these steps:
// First, run the following code snippet. This will open a dummy browser window.
// In the terminal, you'll see a localhost address (e.g., localhost:9222).
// Copy that address.
// Open the "Launch.java" or "Base.java" or any base where browser is set to launch and paste the copied address in the line where it says debuggerAddress.
// Initially, provide your URL in the Launch class to open the desired page.
// After the first run, you can comment out the URL part, so it doesn't reopen the browser every time.
// From the next run onward, your tests will continue from the last page where the previous test ended, using the same browser session.
// If needed, you can also manually change the page or state in the browser before running the next test.
// This method saves time and avoids reopening the browser repeatedly.
    
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Capabilities cap = driver.getCapabilities();
        Map<String, Object> mycap = cap.asMap();
        System.out.println(mycap);
    }

}


