package katalon.studio

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.RenderingHints.Key

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import	org.openqa.selenium.Keys;
import internal.GlobalVariable
import katalon.fw.lib.BaseWindow

public class TestSuite extends BaseWindow<TestSuite> {

	/**
	 * Add new test suite
	 * @param testSuiteName
	 * @param testSuiteDesc
	 * @return
	 */
	public TestSuite addNewTestSuite(String testSuiteName,String testSuiteDesc) {
		KeywordUtil.logInfo("Start: create new test suite")
		//Open new test suite dialog
		clickMenu("File", "New", "Test Suite");
		//Input data
		enterData(testSuiteName, testSuiteDesc);
		//Click OK
		Windows.click(getDialogButton("New","OK"),FailureHandling.STOP_ON_FAILURE);

		KeywordUtil.logInfo("End: End create new test suite");
		return this;
	}

	/**
	 * Add test case to test suite
	 * @param testCaseName: test case name
	 * @return
	 */
	public TestSuite addTestCaseIntoTestSuite(String testCaseName) {
		KeywordUtil.logInfo("Start: Add test case to test suite")
		//Open add test dialog
		Windows.click(getToolbarButtonByName('Add'),FailureHandling.STOP_ON_FAILURE);
		//select test case - input keyword to search
		Windows.setText(getDialogTextBox("Test Case Browser", 1), testCaseName,FailureHandling.STOP_ON_FAILURE);
		sleepSomeTime();
		//Select test case
		Windows.sendKeys(getDialogCheckBox("Test Case Browser", testCaseName),Keys.chord(Keys.SPACE),FailureHandling.STOP_ON_FAILURE)
		//OK
		Windows.click(getDialogButton("Test Case Browser", "OK"));
		sleepSomeTime();
		//Save
		Windows.sendKeys(getToolbarButtonByName('Add'),Keys.chord(Keys.CONTROL,'S'),FailureHandling.STOP_ON_FAILURE);
		//End
		KeywordUtil.logInfo("End: Add test case to test suite")

		return this;
	}

	/**
	 * Input data to create a test suite
	 * @param testSuiteName
	 * @param testSuiteDesc
	 */
	protected void enterData(String testSuiteName,String testSuiteDesc) {
		KeywordUtil.logInfo("Start: enter data for new test suite")
		//Set test suite name to 1st textbox of new dialog
		Windows.setText(getDialogTextBox('New',1), testSuiteName,FailureHandling.STOP_ON_FAILURE);
		//Set description to multiline textbox
		Windows.setText(getDialogMultiline('New','Description'),testSuiteDesc,FailureHandling.STOP_ON_FAILURE);

		KeywordUtil.logInfo("End: enter data for new test suite")

	}

	/**
	 * Verify test case is added to test suite
	 * @param testCaseName
	 * @param timeout
	 * @return
	 */
	public TestSuite verifyTestCaseAddedToTestSuite(String testCaseName,int timeout) {
		KeywordUtil.logInfo("Start: Verify test case added to test suite")
		Windows.verifyElementPresent(getTestCaseInTestSuite(testCaseName),timeout,FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("End: Verify test case added to test suite")
		return this;
	}
}
