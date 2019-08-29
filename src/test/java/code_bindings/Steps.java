package code_bindings;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	
	WebDriver driver;

	@Given("^user is on homepage$")
	public void user_is_on_homepage() throws Throwable {
		String exePath = "/Applications/chromedriver";
//		String exePath = "/Frontend_UI⁩/chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://www.theguardian.com/tone/news");
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id=\"top\"]/div[6]/div/div/div[2]/div[3]/button")).click();	//	throw new PendingException();
	}
	
	

	@When("^user copies news article text and searches for that text with google$")
	public void user_copies_first_news_article_text() throws Throwable {
		Thread.sleep(100);
		driver.findElement(By.cssSelector(".fc-item--standard-mobile .u-faux-block-link__overlay")).click();
		String myOrderText = driver.findElement(By.cssSelector(".content__headline")).getText();
		Thread.sleep(100);
		((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1)); //switches to the newly opened tab, Will control on tab as according to index
	    driver.get("https://www.google.co.uk");
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")).sendKeys(myOrderText);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("center:nth-child(1) > .gNO89b")).click();
	}
	

	@Then("^search results are displayed and user checks and verifies matching results$")
	public void search_results_list_is_dispalyed() throws Throwable {

		String mySearchText = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[2]/div/div[1]/input")).getText();
		Assert.assertTrue(driver.getPageSource().contains(mySearchText));
		if(driver.getPageSource().contains(mySearchText))
		{
		    System.out.println("success, let's do a bit more check around presented news");
		}
		
		else
		{
			System.out.println("no results found, might be a fake news");
		}	
		
		driver.findElements(By.cssSelector(".gLFyf")).size();	
		Thread.sleep(100);
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(0));
	    String mySearchText1 = driver.findElement(By.cssSelector(".caption--main")).getText();
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.google.co.uk");
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")).sendKeys(mySearchText1);
		Thread.sleep(100);
		driver.findElement(By.cssSelector("center:nth-child(1) > .gNO89b")).click();
		
		if(driver.getPageSource().contains(mySearchText))
		{
		    System.out.println("success again, news looks legitimate");
		}

		else
		{
			System.out.println("again, no results found, might be fake");
		}	

	}

}
