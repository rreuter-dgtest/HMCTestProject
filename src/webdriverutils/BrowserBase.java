package webdriverutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;;

public class BrowserBase {
	
	public void GoToURL(String url) {
		WebdriverBase.getInstance().getDriver().get(url);
		this.WaitForJavascriptLoadComplete();
	}
	
    public void closeBrowser() {
    	WebdriverBase.getInstance().getDriver().close();
    }
	
	protected WebElement getWebElement(String cssSelector) {
		this.waitForWebElement(cssSelector);
		return WebdriverBase.getInstance().getDriver().findElement(By.cssSelector(cssSelector));
	}
	
	protected List<WebElement> getWebElements(String cssSelector) {
		this.waitForWebElement(cssSelector);
		return WebdriverBase.getInstance().getDriver().findElements(By.cssSelector(cssSelector));
	}
	
	protected List<WebElement> getWebElementInsideParentWebElement(WebElement parentElement, String cssSelector) {
		return parentElement.findElements(By.cssSelector(cssSelector));
	}
	
	protected void typeIntoWebElement(WebElement element, String textToType) {
		element.sendKeys(textToType);
	}
	
	protected void typeIntoWebElement(String cssSelector, String textToType) {
		this.typeIntoWebElement(this.getWebElement(cssSelector), textToType);
	}
	
    protected void WaitForJavascriptLoadComplete() {
    	Wait<WebDriver> wait = new WebDriverWait(WebdriverBase.getInstance().getDriver(), 30);
    	wait.until(drv -> ((JavascriptExecutor) drv).executeScript("return document.readyState").equals("complete"));
    }
    
    protected void waitForWebElement(String cssSelector) {
    	WebDriverWait wait = new WebDriverWait(WebdriverBase.getInstance().getDriver(), 30);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
    
    protected boolean browserTitleContains(String titleText) {
    	if (WebdriverBase.getInstance().getDriver().getTitle().trim().toLowerCase().contains(titleText.trim().toLowerCase())) {
    		return true;
    	}
    	return false;
    }
    
    protected void clickWebElement(WebElement element) {
    	element.click();
    }
    
    protected WebElement getParentOfWebElement(WebElement element) {
    	return element.findElement(By.xpath(".."));
    }
}
