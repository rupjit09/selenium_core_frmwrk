package shopSuite;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import util.Utility;

import base.TestBase;

public class TestsuiteBase extends TestBase{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
		log.debug("Initiallized");
		if(!Utility.isSuiteRunnable(suiteXls, "Shop Suite")){
			log.debug("Skipping Shop Suite as runmode is set to no");
			throw new SkipException("Skipping Shop Suite as runmode is set to no");
		}
	}
}
