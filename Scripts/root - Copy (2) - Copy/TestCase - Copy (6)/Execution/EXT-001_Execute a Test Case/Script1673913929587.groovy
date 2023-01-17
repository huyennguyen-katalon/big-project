
import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed

import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project
import katalon.studio.TestCase
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

'Step: Open sample project'
Window.open(Project).openProject(GlobalVariable.G_SampleAPIProj);

'Step: Add new test case'
Window.open(TestCase).addNewTestCase(testCaseName,testCaseDesc,testCaseTag)
				.sleepSomeTime() // Sleep sometime
				.switchView("Script") // switch to script view

"Step: Update newly created Test Case with new test step"
Window.open(TestCase).enterTestcaseScript(testCaseName, stepData)

"Step: Run Test Case has been created"
Window.open(TestCase).runCurrentTestCase()
				
"Step: Verify Test Case is executed"
Window.open(TestCase).verifyTestCaseExecuted(testCaseName) // then verify result

'If any error occurs while running, capture the screenshot for analyze later'
@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed( ) {
	Helper.handleWhenTestCaseFailed()
}