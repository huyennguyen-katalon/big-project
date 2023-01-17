import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project
import katalon.studio.TestCase
import katalon.fw.util.App
import katalon.fw.util.ScreenshotHelper

"DATA Start PREPARATION"
testCaseFolder = "Test Cases"
testCaseName ="Execution Smartwait case"

'Step: Open sample web project'
Window.open(Project).openProject(GlobalVariable.G_SampleWebProject)

'Config not using Smart Wait'
Window.open(Project).openProjectSettingsAndEnableSmartWait(false)

'Open Test Cases folder in KS'
Window.open(TestCase).doubleClickTestCase(testCaseFolder).sleepSomeTime()

'Open Smart Wait test case'
Window.open(TestCase).doubleClickTestCase(testCaseName).sleepSomeTime()

'Run current test case'
Window.open(TestCase).runCurrentTestCase()

'Verify Test Case is executed and its status is FAILED'
Window.open(TestCase).verifyTestCaseExecuted(testCaseName)

'Capture the screenshot'
ScreenshotHelper.takeWindowsScreenshot(GlobalVariable.G_ScreenshotFolder)

'Clear test result'
Window.open(TestCase).verifyCurrentTestCaseFailed().clearAllTestsResult()

'Config using Smart Wait'
Window.open(Project).openProjectSettingsAndEnableSmartWait(true)

'Run current test case again'
Window.open(TestCase).runCurrentTestCase()

'Verify Test Case is executed and its status is PASSED'
Window.open(TestCase).verifyTestCaseExecuted(testCaseName)

'Capture the screenshot'
ScreenshotHelper.takeWindowsScreenshot(GlobalVariable.G_ScreenshotFolder)

'Clear test result'
Window.open(TestCase).verifyCurrentTestCasePassed().clearAllTestsResult()

'Close openning project'
Window.open(Project).closeOpneningProject()

'If any error occurs while running, capture the screenshot for analyze later'
@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed() {
    Helper.handleWhenTestCaseFailed()
}

