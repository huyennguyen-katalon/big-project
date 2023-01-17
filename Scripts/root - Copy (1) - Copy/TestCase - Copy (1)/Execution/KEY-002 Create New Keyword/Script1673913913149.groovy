import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed

import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project
import katalon.studio.TestCase
import katalon.studio.TestSuite
import katalon.studio.TestSuiteCollection
import katalon.studio.KSKeyword
import katalon.fw.util.App

"Data Start Preparation"
String packageName = ""
String className = Helper.genUniqueString("PackageName").toLowerCase()
String testCaseStep = "CustomKeywords.'CLASSNAME.compare2Values'('samevalue','samevalue')"
testCaseStep = testCaseStep.replace('CLASSNAME', className)

testCaseName = Helper.genUniqueString("TC Name")
testCaseDesc = Helper.genUniqueString("TC Description")
testCaseTag = Helper.genUniqueString("TC Tag")
String keywordData = Window.open(KSKeyword).getSampleKeyword(className)
"Data End Preparation"

'Open sample project'
Window.open(Project).openProject(GlobalVariable.G_SampleAPIProj);

'Step: new keyword'

Window.open(KSKeyword).addNewKeyword(packageName, className)
Window.open(KSKeyword).provideKeywordSteps(className, keywordData)

'Step: create new test case'
Window.open(TestCase).addNewTestCase(testCaseName, testCaseDesc, testCaseTag)
				.sleepSomeTime() // Sleep sometime
				.switchView("Script") // switch to script view
				;
"Step: Update newly created Test Case with new test step"
Window.open(TestCase).enterTestcaseScript(testCaseName, testCaseStep)

"Run Test Case has been created"
Window.open(TestCase).runCurrentTestCase()

"Wait for running"
Window.open(TestCase).sleepSomeTime(GlobalVariable.G_ShortTimeOut) // Wait for test case
				;
"Verify Test Case is executed"
Window.open(TestCase).verifyTestCaseExecuted(testCaseName) // then verify result

'If any error occurs while running, capture the screenshot for analyze later'
@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed( ) {
	Helper.handleWhenTestCaseFailed()
}