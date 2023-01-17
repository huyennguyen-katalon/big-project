
import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed

import internal.GlobalVariable as GlobalVariable

import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project
import katalon.studio.TestCase
import katalon.studio.TestSuite
import katalon.studio.TestSuiteCollection
import katalon.fw.util.App

"DATA Start PREPARATION"
//Generate test case name
testCaseName = Helper.genUniqueString("TC Name"); 
//Generate test case description
testCaseDesc = Helper.genUniqueString("TC Description");
//Generate test case tag
testCaseTag =  Helper.genUniqueString("TC Tag");
//Step data
stepData = "WebUI.verifyEqual(1, 1, FailureHandling.STOP_ON_FAILURE)"
//Test suite name
testSuiteName = Helper.genUniqueString("TS Name");
//Test suites description
testSuiteDesc = Helper.genUniqueString("TS Desc")
//Test suite collection
testSuiteCollection = Helper.genUniqueString("TS Collection Name")
//Test suite collection
testSuiteCollectionDesc = Helper.genUniqueString("TS Collection Desc")
//Test suite collection
testSuiteCollectionTag = Helper.genUniqueString("TS Collection Tag")
"DATA End PREPARATION"

"Precondition#1 - An existing project is opened"

'Open dialog to create new test case'
//Page.nav(MainPage).addNewTestCase();
'Step: Open sample project'
Window.open(Project).openProject(GlobalVariable.G_SampleAPIProj);
"End Precondition#1"

"Precondition#2 - A new test case is created"

'Add new test case'
Window.open(TestCase).addNewTestCase(testCaseName,testCaseDesc,testCaseTag)
				.sleepSomeTime() // Sleep sometime
				.switchView("Script") // switch to script view
				;
"Update newly created Test Case with new test step"
Window.open(TestCase).enterTestcaseScript(testCaseName, stepData);

"End Precondition#2"
'Step: Add new test suite'
Window.open(TestSuite).addNewTestSuite(testSuiteName, testSuiteDesc);
Window.open(TestSuite).addTestCaseIntoTestSuite(testCaseName);
Window.open(TestSuite).verifyTestCaseAddedToTestSuite(testCaseName, GlobalVariable.G_ShortTimeOut);

'Step: add new test suite collection'
Window.open(TestSuiteCollection).addNewCollection(testSuiteCollection, testSuiteCollectionDesc, testSuiteCollectionTag);

'Step: add test suite to collection'
Window.open(TestSuiteCollection).addTestSuiteToCollection(testSuiteName);

'Step: run test suite collection'
Window.open(TestSuiteCollection).runTestSuiteCollection()
		.sleepSomeTime(GlobalVariable.G_ShortTimeOut);
		
'Step: verify'
Window.open(TestSuiteCollection).verifyTestSuiteIsExecuted(testSuiteCollection);

'If any error occurs while running, capture the screenshot for analyze later'
@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed( ) {
	Helper.handleWhenTestCaseFailed()
}