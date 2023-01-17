package katalon.fw.util

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat
import java.text.DateFormat
import internal.GlobalVariable
import katalon.fw.util.ScreenshotHelper
import katalon.studio.Constants
import com.kms.katalon.core.model.FailureHandling

public class Helper {
	def static String genUniqueString(String prefix = "", String suffix = "", String format = "yyyMMddHHmmss") {
		String strResult = new String();
		try {
			KeywordUtil.logInfo("GenUniqueString - start to generate string")

			//Create date time format object
			DateFormat dateFormat = new SimpleDateFormat(format)
			// Get current date time
			Date date = new Date()
			// Concat prefix + current date time + suffix to create unique string
			strResult = prefix + dateFormat.format(date) + suffix
			KeywordUtil.logInfo("GenUniqueString - end to generate string")

		}
		catch(Exception e) {
			KeywordUtil.logInfo("GenUniqueString - fail to generate string")
		}
		return strResult
	}

	def static handleWhenTestCaseFailed() {
		ScreenshotHelper.takeWindowsScreenshot(GlobalVariable.G_ScreenshotFolder)
		//Handling for any dialog opening, we find any button named Close, Cancel or OK and click on one of them.
		boolean isDisplay = Windows.verifyElementPresent(findWindowsObject("Common/btnCloseCancelOK"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.OPTIONAL)
		while(isDisplay) {
			Windows.click(findWindowsObject("Common/btnCloseCancelOK"))
			isDisplay = Windows.verifyElementPresent(findWindowsObject("Common/btnCloseCancelOK"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.OPTIONAL)
		}

	}
}
