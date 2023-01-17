package katalon.fw.util

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import com.kms.katalon.core.windows.keyword.helper.WindowsActionHelper
import katalon.fw.util.Helper

class ScreenshotHelper {

	public static final String TAKING_SCREENSHOT = "Taking Screenshot"
	public static final String TAKING_SCREENSHOT_SUCCESSFULLY = "Taking Screenshot successfully"

	/**
	 * Take Windows Screenshot
	 * @param screenshotLocation : Where screenshot should be saved?
	 * @return fileName : The full path + file name.png
	 */
	@Keyword
	def static String takeWindowsScreenshot(String screenshotLocation=null) {
		String fileName = screenshotLocation + "\\" + Helper.genUniqueString() + ".png"
		KeywordUtil.logInfo("${TAKING_SCREENSHOT}: ${fileName}")
		WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).takeScreenshot(fileName)
		KeywordUtil.markPassed("${TAKING_SCREENSHOT_SUCCESSFULLY}")
		return fileName
	}
}