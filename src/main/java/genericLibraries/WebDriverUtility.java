package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods to perform web driver actions
 * @author Rahul
 */
public class WebDriverUtility {
        WebDriver driver;
        
        /**
    	 * This method is used to launch user desired browser
    	 * 
    	 * @param browser
    	 * @return
    	 */
    	public WebDriver launchBrowser(String browser) {

    		switch (browser) {
    		case "chrome":
    			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
    			driver = new ChromeDriver();
    			break;

    		case "firefox":
    			WebDriverManager.firefoxdriver().setup();
    			driver = new FirefoxDriver();

    		case "edge":
    			WebDriverManager.edgedriver().setup();
    			driver = new EdgeDriver();
    			break;

    		default:
    			System.out.println("Invalid Browser Info");

    		}
    		driver.manage().window().maximize();
    		return driver;

    	}

    	/**
    	 * This method is used to navigate to the application
    	 * 
    	 * @param url
    	 */
    	public void navigateToApp(String url) {
    		driver.get(url);
    	}

    	/**
    	 * This method is an implicitly wait Statement
    	 * 
    	 * @param time
    	 */
    	public void waitTillElementFound(long time) {
    		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    	}

    	/**
    	 * This method is used to wait until element is visisble
    	 * 
    	 * @param time
    	 * @param element
    	 * @return
    	 */
    	public WebElement explicitWait(long time, WebElement element) {
    		WebDriverWait wait = new WebDriverWait(driver, time);
    		return wait.until(ExpectedConditions.visibilityOf(element));

    	}

    	/**
    	 * This method is used to wait until element is enabled
    	 * 
    	 * @param time
    	 * @param element
    	 * @return
    	 */
    	public WebElement explicitWait(WebElement element, long time) {
    		WebDriverWait wait = new WebDriverWait(driver, time);
    		return wait.until(ExpectedConditions.elementToBeClickable(element));

    	}

    	/**
    	 * This method is used to wait until element is enabled
    	 * 
    	 * @param time
    	 * @param element
    	 * @return
    	 */
    	public Boolean explicitWait(String title, long time) {
    		WebDriverWait wait = new WebDriverWait(driver, time);
    		return wait.until(ExpectedConditions.titleContains(title));

    	}

    	/**
    	 * This method is used to double click an element
    	 * 
    	 * @param element
    	 * @param target
    	 */
    	public void doubleClickElement(WebElement element) {
    		Actions actions = new Actions(driver);
    		actions.doubleClick(element).perform();
    	}

    	/**
    	 * This method is used to right click an element
    	 * 
    	 * @param element
    	 * @param target
    	 */
    	public void rightClick(WebElement element) {
    		Actions actions = new Actions(driver);
    		actions.contextClick(element).perform();
    	}

    	/**
    	 * This method is used to drag and drop an element
    	 * 
    	 * @param element
    	 * @param target
    	 */
    	public void dragAndDropElement(WebElement element, WebElement target) {
    		Actions actions = new Actions(driver);
    		actions.dragAndDrop(element, target).perform();
    	}

    	/**
    	 * This method is used to select an element from drop down based on index
    	 * 
    	 * @param element
    	 * @param index
    	 */
    	public void handleDropdown(WebElement element, int index) {
    		Select select = new Select(element);
    		select.selectByIndex(index);
    	}

    	/**
    	 * This method is used to select an element from drop down based on value
    	 * 
    	 * @param element
    	 * @param value
    	 */
    	public void handleDropdown(WebElement element, String value) {
    		Select select = new Select(element);
    		select.selectByValue(value);
    	}

    	/**
    	 * This method is used to select an element from drop down based on text
    	 * 
    	 * @param text
    	 * @param element
    	 */
    	public void handleDropdown(String text, WebElement element) {
    		Select select = new Select(element);
    		select.selectByVisibleText(text);
    	}

    	/**
    	 * This method is used to switch to frame based on index
    	 * 
    	 * @param index
    	 */
    	public void switchToFrame(int index) {
    		driver.switchTo().frame(index);
    	}

    	/**
    	 * This method is used to switch to frame based on id or name attribute
    	 * 
    	 * @param idOrName
    	 */
    	public void switchToFrame(String idOrName) {
    		driver.switchTo().frame(idOrName);
    	}

    	/**
    	 * This method is used to switch to frame based on frame element reference
    	 * 
    	 * @param frameElement
    	 */
    	public void switchToFrame(WebElement frameElement) {
    		driver.switchTo().frame(frameElement);
    	}

    	/**
    	 * This method is used to switch back from frame
    	 */
    	public void switchBackFromFrame() {
    		driver.switchTo().defaultContent();
    	}

    	/**
    	 * This method is used to capture window Screenshot
    	 * 
    	 * @param driver
    	 * @param className
    	 * @param jutil
    	 */
    	public void takeScreenshot(WebDriver driver, String className, JavaUtility jutil) {
    		TakesScreenshot ts = (TakesScreenshot) driver;
    		File src = ts.getScreenshotAs(OutputType.FILE);
    		File dest = new File("./Screenshot" + className + "_" + jutil.getCurrentTime() + ".png");
    		try {
    			FileUtils.copyFile(src, dest);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}

    	/**
    	 * This method is to scroll till element
    	 * 
    	 * @param element
    	 */
    	public void scrollTillelement(WebElement element) {
    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("argumnets[0].scrollIntoView(true)", element);
    	}

    	/**
    	 * This method is to handle alert
    	 * 
    	 * @param status
    	 */
    	public void handleAlert(String status) {
    		Alert alert = driver.switchTo().alert();
    		if (status.equalsIgnoreCase("ok"))
    			alert.accept();
    		else
    			alert.dismiss();
    	}

    	/**
    	 * This method is used to switch to child Browser
    	 */
    	public void handleChildBrowser() {
    		Set<String> set = driver.getWindowHandles();
    		for (String windowID : set) {
    			driver.switchTo().window(windowID);
    		}
    	}

    	/**
    	 * This method return parent browser address
    	 * 
    	 * @return
    	 */
    	public String getParentWindowID() {
    		return driver.getWindowHandle();
    	}

    	/**
    	 * This method is used to switch to specified window
    	 * 
    	 * @param windowID
    	 */
    	public void switchtoWindow(String windowID) {
    		driver.switchTo().window(windowID);
    	}

    	/**
    	 * This method is used to close current window
    	 */
    	public void closeCurrentWindow() {
    		driver.close();
    	}

    	/**
    	 * This method is used to close all the windows
    	 */
    	public void closeAllWindows() {
    		driver.quit();
    	}
}
