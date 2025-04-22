package com.flinki.Base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class BasePage {
    public  WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public JavascriptExecutor jsExecutor;
    public static final Faker faker = new Faker();
    public Robot robot;


      
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }
    

 //=========================================Click WebElement ==========================================

    public void clickElement(WebElement element)
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }   

    }
//==========================================Enter text=================================================

    public void enterText(WebElement element , String text)
    {
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            webElement.click(); // Ensure the field is focused
            webElement.clear(); // Try clearing the field normally

            // Double clear using JavaScript in case normal clear() fails
            jsExecutor.executeScript("arguments[0].value = '';", webElement);

            Thread.sleep(500); // Small delay to ensure the field is clear
            webElement.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to enter text: " + e.getMessage());
        }
    }
    
//============================================Get Element text in Webpage ==============================
    
public String getElementText(WebElement element) {
    try {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    } catch (Exception e) {
        System.out.println("Unable to get text: " + e.getMessage());
        return "";
    }
}
    
//===========================================Element displayed In Webpage===================================

public boolean isElementDisplayed(WebElement element) {
    try {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
    
    public String getPageTittle()
    {
        return driver.getTitle();
    }

    
// ==========================================Scroll to Element =========================================
public void scrollToElement(WebElement element) {

    jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

    try {
        Thread.sleep(500); // Short delay for smooth scrolling
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    

// ==========================================Method to get the current URL=========================================
public String getCurrentURL() {
        
    return driver.getCurrentUrl();
    }

// =========================================Method to verify if the current URL contains expected text========================
    public boolean verifyURLContains(String expectedText) {
        return driver.getCurrentUrl().contains(expectedText);
    }

    public Select getSelect(WebElement dropdownElement) {
        return new Select(dropdownElement);
    }


    
    
//================================================Random String Genarate===============================================
public static String randomstring() {
    String randomname = RandomStringUtils.randomAlphabetic(5);
    return randomname;
}
    

//================================================Random Number Generate===============================================

public static String randomnumber(int num) {
    String randomphone = RandomStringUtils.randomNumeric(num);
    return randomphone;
}
    

    
//=================================================Random Email Genrate=======================================================

public static String randomemail() {
    String randomemail = RandomStringUtils.randomAlphabetic(5) + "@yopmail.com";
    return randomemail;
}
    

//==========================================Click random Element in List==========================================
 
 public void clickRandomElement(List<WebElement> elements) {
     if (!elements.isEmpty()) {
         Random rand = new Random();
         int randomIndex = rand.nextInt(elements.size());
         WebElement randomElement = elements.get(randomIndex);
         randomElement.click();
     } else {
         System.out.println("No elements found for locator: " + elements);
     }

 }
    


 //===============================================Scroll to Bottam==================================================

public void scrollToBottom() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
}
    

    


//===============================================Upload image==============================================

public void uploadImage(WebElement inputElement, String fileName) {
    try {
        String projectPath = System.getProperty("user.dir");
        String fullPath = projectPath + "/src/main/Resources/Images/" + fileName;
        File file = new File(fullPath);

        if (!file.exists()) {
            System.out.println(" File not found: " + fullPath);
            return;
        }

        inputElement.sendKeys(file.getAbsolutePath());
        Thread.sleep(2000); // Wait for upload if needed
        System.out.println(" Image uploaded successfully: " + fileName);

    } catch (Exception e) {
        System.out.println(" Failed to upload image (" + fileName + "): " + e.getMessage());
        e.printStackTrace();
    }
}
    

//=============================================Wait for page load==================================================

public void waitForPageLoad() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                    .equals("complete"));
}
    

    
//=============================================Generate random date of birth========================================

public String generateRandomDOB(int minAge, int maxAge) {
    if (minAge > maxAge) {
        throw new IllegalArgumentException("minAge should be less than maxAge");
    }

    // Calculate date ranges
    LocalDate toDate = LocalDate.now().minusYears(minAge); // youngest allowed
    LocalDate fromDate = LocalDate.now().minusYears(maxAge); // oldest allowed

    // Convert to java.util.Date
    Date from = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date to = Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Generate random date between from and to
    Date randomDate = faker.date().between(from, to);

    // Format to dd/MM/yyyy
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    return formatter.format(randomDate);
}

// ===================================Date filed range Between  Start date and End Date=====================


 // Generates a random date between two dates
 public static LocalDate getRandomDateBetween(LocalDate start, LocalDate end) {
     long days = ChronoUnit.DAYS.between(start, end);
     long randomDays = ThreadLocalRandom.current().nextLong(days + 1); // inclusive
     return start.plusDays(randomDays);
 }
    

 public static LocalDate[] getStartAndEndDate(LocalDate minStart, LocalDate maxStart, int maxDaysBetween) {
     LocalDate startDate = getRandomDateBetween(minStart, maxStart);
     LocalDate maxEndDate = startDate.plusDays(maxDaysBetween);
     LocalDate endDate = getRandomDateBetween(startDate, maxEndDate);
     return new LocalDate[] { startDate, endDate };
 }

 public static String RandomDOB(int minAge, int maxAge) {
     if (minAge > maxAge) {
         throw new IllegalArgumentException("minAge should be less than maxAge");
     }

     LocalDate toDate = LocalDate.now().minusYears(minAge); // youngest allowed
     LocalDate fromDate = LocalDate.now().minusYears(maxAge); // oldest allowed

     Date from = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
     Date to = Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

     Date randomDate = faker.date().between(from, to);

     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
     return formatter.format(randomDate);
 }
  

 public String[] getRandomStartAndEndDate(int maxGapDays) {
     // Start date: today to today + maxGapDays
     LocalDate startMin = LocalDate.now();
     LocalDate startMax = startMin.plusDays(maxGapDays);

     // Convert to Date
     Date fromStart = Date.from(startMin.atStartOfDay(ZoneId.systemDefault()).toInstant());
     Date toStart = Date.from(startMax.atStartOfDay(ZoneId.systemDefault()).toInstant());

     // Generate random start date (today -> +maxGapDays)
     Date randomStart = faker.date().between(fromStart, toStart);

     // Convert start date to LocalDate to set range for end date
     LocalDate startLocal = randomStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     LocalDate endMax = startLocal.plusDays(maxGapDays);

     // Convert to Date for faker
     Date endFrom = Date.from(startLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
     Date endTo = Date.from(endMax.atStartOfDay(ZoneId.systemDefault()).toInstant());

     // Generate random end date (start -> start + maxGapDays)
     Date randomEnd = faker.date().between(endFrom, endTo);

     // Format dates
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
     String formattedStart = formatter.format(randomStart);
     String formattedEnd = formatter.format(randomEnd);

     return new String[] { formattedStart, formattedEnd };
 }
// =======================Select DropDwon using Keyboard =======================
 public void selectDropdownUsingKeyboard(WebElement dropdownElement) throws AWTException {
    Actions actions = new Actions(driver);
    actions.moveToElement(dropdownElement).click().perform();

    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_DOWN);
    robot.keyRelease(KeyEvent.VK_DOWN);

    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
}
    


public void acceptAlert() {
    try {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    } catch (NoAlertPresentException e) {
        System.out.println("No alert found: " + e.getMessage());
    }
}

public void dismissAlert() {
    try {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    } catch (NoAlertPresentException e) {
        System.out.println("No alert found: " + e.getMessage());
    }
}

public void sendTextToAlert(String text) {
    try {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    } catch (NoAlertPresentException e) {
        System.out.println("No alert found: " + e.getMessage());
    }
}


    

    }
    
    
    

