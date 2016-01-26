package cartSuite;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import util.Utility;
import base.TestBase;

public class TestsuiteBase extends TestBase{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
		log.debug("Initiallized");
		if(!Utility.isSuiteRunnable(suiteXls, "Cart Suite")){
			log.debug("Skipping Cart Suite as runmode is set to no");
			throw new SkipException("Skipping Cart Suite as runmode is set to no");
		}
	}
}
