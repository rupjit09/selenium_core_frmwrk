package shopSuite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import shopSuite.TestsuiteBase;
import util.Utility;

public class CheckProductDisplay extends TestsuiteBase{
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	
	@Test
	public void testcaseA2(){
		openBrowser();
		driver.get("http://demo.virtuemart.net/");
		WebElement element1=driver.findElement(By.xpath("//div[@class='featured-view']//div[@class='row']"));
		List<WebElement> no_of_image=element1.findElements(By.xpath("//div[@class='featured-view']//div[@class='row']//img[contains(@src,'0x90')]"));
		List<WebElement> no_of_tooltipimage=element1.findElements(By.xpath("//div[@class='vm3pr-2' or @class='vm3pr-1']//img[contains(@src,'tooltip')]"));
		List<WebElement> no_of_product_details=element1.findElements(By.xpath("//div[@class='featured-view']//div[@class='row']//a[contains(text(),'Product details')]"));
		System.out.println("no of images: "+no_of_image.size());
		System.out.println("no_of_tooltipimage: "+no_of_tooltipimage.size());
		System.out.println("no_of_product_details button:"+no_of_product_details.size());
		System.out.println();
		if(!verifyTitle("Welcome to VirtueMart 3 Sample store")){
			fail=true;
			return;
		}
		
	}
	
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{
		if(!Utility.isCaseRunnable(suiteShopXls, "CheckProductDisplay")){
			log.debug("Skipping CheckProductDisplay as runmode is set to no");
			throw new SkipException("Skipping CheckProductDisplay as runmode is set to no");
		}
	}
	
}
