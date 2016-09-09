package webdriverutils;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebdriverBase {
	
	private static volatile WebdriverBase m_instance;
    private WebDriver driver = null;

	private WebdriverBase()
	{
		//driver = new FirefoxDriver();
		this.InitializeWebDriver();
    }
	
    public WebDriver getDriver() {
		return driver;
	}
    
    public static synchronized WebdriverBase getInstance()
    {
        if (m_instance == null)
        {
            synchronized (WebdriverBase.class) 
            {
                if (m_instance == null)
                {
                    m_instance = new WebdriverBase();
                }
            }
        }
        return m_instance;
    }
    
    public void InitializeWebDriver() 
    {
    	//The robot code is here because, in my experience, sometimes the actual mouse pointer interferes with the actions
    	//of the Javascript mouse pointer in Selenium if the actual mouse pointer is on the Webdriver's representation
    	//of the DOM. Moving the mouse pointer away from the browser window alleviates the problem.
    	Robot robot = null;
		try {
			robot = new Robot();
			robot.mouseMove(0, 0);
		} 
		catch (AWTException e) {
			// catch and release; this is a precaution only.
		}
    	
    	driver = new FirefoxDriver();
    }
}
