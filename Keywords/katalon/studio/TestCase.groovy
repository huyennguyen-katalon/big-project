package katalon.studio

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testobject.WindowsTestObject
import katalon.fw.lib.BaseWindow
import org.openqa.selenium.Keys
import com.kms.katalon.core.util.KeywordUtil
import katalon.studio.Constants

public class TestCase extends BaseWindow<TestCase>  {

	/**
	 * add new test case
	 * @param tcName: test case name
	 * @param tcDesc: test case description
	 * @param tcTag: test case tag
	 * @return TestCase class
	 */ 
	public TestCase addNewTestCase(String tcName,String tcDesc="", String tcTag="") {
		KeywordUtil.logInfo("Start to create new Test Case")
		//Open File -> New > Test case
		this.clickMenu("File", "New","Test Case")
		//wait dialog open
		sleepSomeTime()
		//Input data for new test case
		this.enterDataForNewTestCase(tcName, tcDesc, tcTag)
		//Click OK to create test case
		Windows.click(getDialogButton('New', 'OK'),FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("End to create new Test Case")

		return this
	}

	/**
	 * Input script for test case
	 * @param testcaseName: test case name
	 * @param stepData: step data
	 * @return TestCase class
	 */
	public TestCase enterTestcaseScript(String testcaseName,String stepData) {
		//get text editor object
		WindowsTestObject obj = getScriptWindow(testcaseName)
		//Page down to move to last row
		Windows.sendKeys(obj,Keys.chord( Keys.PAGE_DOWN), FailureHandling.STOP_ON_FAILURE)
		//End key to move to end of row
		Windows.sendKeys(obj,Keys.chord( Keys.END),FailureHandling.STOP_ON_FAILURE)
		//Enter to add new row
		Windows.sendKeys(obj,Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
		//Input step data
		Windows.setText(obj, stepData, FailureHandling.STOP_ON_FAILURE)
		//Save
		Windows.sendKeys(obj, Keys.chord(Keys.CONTROL,'S'),FailureHandling.STOP_ON_FAILURE)
		return this

	}

	/**
	 * Enter data to create new test case 
	 * @param testCaseName
	 * @param testcaseDesc
	 * @param testCaseTag
	 */
	protected void enterDataForNewTestCase(String testCaseName, String testcaseDesc, String testCaseTag){
		KeywordUtil.logInfo("Start to enter data for new Test Case")
		//Enter test case name
		Windows.setText(getTextboxByName('Name'), testCaseName, FailureHandling.STOP_ON_FAILURE)
		//Enter test description
		Windows.setText(getMultilineTextBox('Description'), testcaseDesc, FailureHandling.STOP_ON_FAILURE)
		//Enter test tag
		Windows.setText(getTextboxByName('Tag'), testCaseTag, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo("End to enter data for new Test Case")
	}

	/**
	 * Run current test case
	 */
	public TestCase runCurrentTestCase() {

		//Click on Run button
		KeywordUtil.logInfo("Click on Run button")
		Windows.click(getSplitButton("Run (Ctrl  + Shift + A)"),FailureHandling.STOP_ON_FAILURE)
		return this
	}

	/**
	 * Verify test case is executed
	 * @param testCaseName: test case name
	 * @return
	 */
	public TestCase verifyTestCaseExecuted(String testCaseName) {
		KeywordUtil.logInfo("Start Verify")

		//Verify test case name is presented in progress dialog
		boolean result = Windows.verifyElementPresent(getJobProgressLabel(testCaseName),GlobalVariable.G_ShortTimeOut,FailureHandling.STOP_ON_FAILURE)
		if(result) {
			KeywordUtil.markPassed("Test case  is executed")
		}
		else {
			KeywordUtil.markFailedAndStop("Test case isn't executed")
		}
		KeywordUtil.logInfo("End Verify")
		return this
	}

	public TestCase verifyCurrentTestCasePassed() {
		KeywordUtil.logInfo("Start Verify")
		//Verify test case name is presented in progress dialog
		boolean result = Windows.verifyElementPresent(getCurrentTestCasePass(),Constants.WAIT_TIMEOUT_THIRTY_SECONDS,FailureHandling.STOP_ON_FAILURE)
		if(result) {
			KeywordUtil.markPassed("Test case runs pass")
		}
		else {
			KeywordUtil.markFailedAndStop("Test case run failed")
		}
		KeywordUtil.logInfo("End Verify")
		return this
	}

	public TestCase verifyCurrentTestCaseFailed() {
		KeywordUtil.logInfo("Start Verify")
		//Verify test case name is presented in progress dialog
		boolean result = Windows.verifyElementPresent(getCurrentTestCaseFail(),Constants.WAIT_TIMEOUT_THIRTY_SECONDS,FailureHandling.STOP_ON_FAILURE)
		if(result) {
			KeywordUtil.markPassed("Test case runs failed")
		}
		else {
			KeywordUtil.markFailedAndStop("Test case run pass")
		}
		KeywordUtil.logInfo("End Verify")
		return this
	}

	public TestCase doubleClickTestCase(String testCaseName) {
		Windows.doubleClick(getTreeItemByName(testCaseName))
		return this
	}

	public TestCase clearAllTestsResult() {
		KeywordUtil.logInfo("Clear all test results")
		Windows.click(getButtonByName("Delete All Terminated Launches"))
		return this
	}
}
