
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.TearDownIfFailed
import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfPassed
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import katalon.fw.util.App
import katalon.fw.util.ScreenshotHelper

class KataDeskListener {
	/**
	 * 
	 * @param testSuiteContext : related information 
	 * @return
	 */
	@BeforeTestSuite
	def PrepareEnvironment(TestSuiteContext testSuiteContext) {
		App.Start(GlobalVariable.G_KatalonPath, GlobalVariable.G_StartTime)
	}
}
