package katalon.studio

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
import org.openqa.selenium.Keys;
import katalon.fw.lib.BaseWindow

public class TestSuiteCollection extends BaseWindow<TestSuiteCollection>{
	public TestSuiteCollection addNewCollection(String tsCollectionName,String tsCollectionDesc,String tsCollectionTag) {
		KeywordUtil.logInfo("Start: create new test suite collection")
		//Open dialog
		clickMenu("File", "New", "Test Suite Collection");
		//Input data
		enterData(tsCollectionName,tsCollectionDesc,tsCollectionTag);
		//OK to close dialog
		Windows.click(getDialogButton("New", "OK"));
		KeywordUtil.logInfo("End: create new test suite collection")
		return this;
	}

	protected void enterData(String name,String desc, String tag) {
		Windows.setText(getDialogTextBox("New", 1), name);
		Windows.setText(getDialogMultiline("New", "Description"),desc);
		Windows.setText(getDialogTextBox("New", 2),tag);
	}

	public TestSuiteCollection addTestSuiteToCollection(String testSuiteName) {
		KeywordUtil.logInfo("Start: Add test suite to collection")
		//Open add test dialog
		Windows.click(getToolbarButtonByName('Add'),FailureHandling.STOP_ON_FAILURE);
		//select test suite - input keyword to search
		Windows.setText(getDialogTextBox("Test Suite Browser", 1), testSuiteName,FailureHandling.STOP_ON_FAILURE);
		sleepSomeTime();
		//Select test suite
		Windows.sendKeys(getDialogCheckBox("Test Suite Browser", testSuiteName),Keys.chord(Keys.SPACE),FailureHandling.STOP_ON_FAILURE)
		//OK
		Windows.click(getDialogButton("Test Suite Browser", "OK"));
		sleepSomeTime();
		//Save
		Windows.sendKeys(getToolbarButtonByName('Add'),Keys.chord(Keys.CONTROL,'S'),FailureHandling.STOP_ON_FAILURE);
		//End
		KeywordUtil.logInfo("End: Add test suite to collection")
		return this
	}

	public TestSuiteCollection runTestSuiteCollection() {
		Windows.click(getToolbarButtonByName('Execute'),FailureHandling.STOP_ON_FAILURE);
		return this;
	}

	public TestSuiteCollection verifyTestSuiteIsExecuted(String testSuiteName) {
		KeywordUtil.logInfo("Start Verify")
		boolean result = Windows.verifyElementPresent(getJobProgressLabel(testSuiteName),GlobalVariable.G_ShortTimeOut,FailureHandling.STOP_ON_FAILURE)
		if(result) {
			KeywordUtil.markPassed("Test suite collection is executed");
		}
		else {
			KeywordUtil.markFailedAndStop("Test suite collection isn't executed");
		}
		KeywordUtil.logInfo("End Verify")
		return this;
	}
}
