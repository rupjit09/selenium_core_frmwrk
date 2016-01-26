package cartSuite;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.ErrorUtil;
import util.Utility;


public class AddProductTest extends TestsuiteBase{
	String[] runmodes=null;
	static int count=-1;
	static boolean skip=false;
	static boolean fail=false;
	static boolean isTestPass=true;
	
	@Test(dataProvider="testDataTest")
	public void testcaseB2(String col1,String col2,String col3,String col4){
		count++;
		if(!runmodes[count].equalsIgnoreCase("y")){
			skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		log.debug("Inside AddProductTest");
		log.debug(col1+"--"+col2+"--"+col3+"--"+col4);
		String expected="rup";
		String actual="rup";
		try{
			Assert.assertEquals(actual, expected);
		}catch(Throwable t){
			fail=true;
			ErrorUtil.addVerificationFailure(t);
		}
	}
    @AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteCartXls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			Utility.dataSetResult(suiteCartXls, this.getClass().getSimpleName(), count+2, "FAIL");
		isTestPass=false;
		}
		else
			Utility.dataSetResult(suiteCartXls, this.getClass().getSimpleName(), count+2, "PASS");
			
		skip=false;
		fail=false;
	}
    @AfterTest
	public void reportTestcaseResult(){
		if(isTestPass){
			Utility.dataSetResult(suiteCartXls,"Testcases", Utility.getRowNum(suiteCartXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteCartXls,"Testcases", Utility.getRowNum(suiteCartXls, this.getClass().getSimpleName()),"FAIL");

	}
	
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{
		if(!Utility.isCaseRunnable(suiteCartXls, "AddProductTest")){
			log.debug("Skipping AddProductTest as runmode is set to no");
			throw new SkipException("Skipping AddProductTest as runmode is set to no");
		}
		
		runmodes=Utility.getDataSetRunmodeTest(suiteCartXls, this.getClass().getSimpleName());
	}
	
	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteCartXls, this.getClass().getSimpleName());
	}
}
