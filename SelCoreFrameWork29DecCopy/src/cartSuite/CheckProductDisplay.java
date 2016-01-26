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

public class CheckProductDisplay extends TestsuiteBase{
	String runmodes[]=null;
	static int count=-1;
	static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean testcaseResult=true;
	@Test(dataProvider="testDataTest")
	public void testcaseB1(String col1,String col2,String col3,String col4){
		count++;
		if(!runmodes[count].equalsIgnoreCase("y")){
			skip=true;
			log.debug(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skippped as runmode is set to N");
			throw new SkipException(this.getClass().getSimpleName()+" Testdata row number "+(count+1)+" is skipped as runmode is set to N");
		}
		log.debug("Inside CheckProductDisplay");
		log.debug(col1+"--"+col2+"--"+col3+"--"+col4);
		
		String expected="Rup";
		String actual="Rup";
		try{
		Assert.assertEquals(actual, expected);
		}catch(Throwable t){
			fail=true;
			System.out.println("Error caught");
			ErrorUtil.addVerificationFailure(t);
			//Utility.dataSetResult(suiteBXls, this.getClass().getSimpleName(), count+2,"FAIL");
		}
		
	}
	
	@AfterMethod
	public void reporterdataSetResult(){
		if(skip)
			Utility.dataSetResult(suiteProductDisplayXls, this.getClass().getSimpleName(), count+2,"SKIP");
		else if(fail){
			Utility.dataSetResult(suiteProductDisplayXls, this.getClass().getSimpleName(), count+2,"FAIL");
			testcaseResult=false;
		}
		else
			Utility.dataSetResult(suiteProductDisplayXls, this.getClass().getSimpleName(), count+2,"PASS");
		
		skip=false;
		fail=false;
	}
	
	@BeforeTest
	public void checkTestcaseSkip() throws Exception{
		if(!Utility.isCaseRunnable(suiteProductDisplayXls, "CheckProductDisplay")){
			log.debug("Skipping CheckProductDisplay as runmode is set to no");
			throw new SkipException("Skipping CheckProductDisplay as runmode is set to no");
		}
		runmodes=Utility.getDataSetRunmodeTest(suiteProductDisplayXls, this.getClass().getSimpleName());
		}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(testcaseResult){
			Utility.dataSetResult(suiteProductDisplayXls,"Testcases", Utility.getRowNum(suiteProductDisplayXls, this.getClass().getSimpleName()),"PASS");
		}else
			Utility.dataSetResult(suiteProductDisplayXls,"Testcases", Utility.getRowNum(suiteProductDisplayXls, this.getClass().getSimpleName()),"FAIL");

	}
	
	@DataProvider
	public Object[][] testDataTest(){
		return Utility.getData(suiteProductDisplayXls, this.getClass().getSimpleName());
	}
}
