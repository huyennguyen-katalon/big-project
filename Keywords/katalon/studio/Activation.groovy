package katalon.studio

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import com.kms.katalon.core.testobject.WindowsTestObject
import katalon.fw.lib.BaseWindow
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import katalon.fw.util.ScreenshotHelper
import katalon.studio.Constants

public class Activation extends BaseWindow<Activation> {

	public Activation enterInfoAndClickActive(String strEmail, String strPwd) {
		KeywordUtil.logInfo("Start to fill data for activation")
		Windows.sendKeys(getTextboxByName("Email"), strEmail)
		Windows.setEncryptedText(getPwdTextbox(), strPwd)
		Windows.click(getButtonByName("Activate"))
		Windows.delay(30)
		KeywordUtil.logInfo("End to fill data for activation")
		return this
	}

	public Activation handleAfterActivateForTheExperienceUsers() {
		KeywordUtil.logInfo("Start action after activation for the first time")
		boolean isFirstTime = Windows.verifyElementPresent(findWindowsObject("Activation/btnFirstTime_Continue"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.OPTIONAL)
		if(isFirstTime) {
			ScreenshotHelper.takeWindowsScreenshot(GlobalVariable.G_ScreenshotFolder)
			KeywordUtil.logInfo("Select No option")
			Windows.click(getRadioButtonByName("No"))
			KeywordUtil.logInfo("Click on Continue button")
			Windows.click(findWindowsObject("Activation/btnFirstTime_Continue"))
			KeywordUtil.logInfo("Close Dialog")
			Windows.click(getButtonByName("Close"))
		}
		KeywordUtil.logInfo("End action after activation for the first time")
	}


	public Activation verifyUserLogInSuccessfully() {
		KeywordUtil.logInfo("Start to verify for activation")
		Windows.verifyElementNotPresent(getTextboxByName("Email"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS)
		KeywordUtil.logInfo("End to verify for activation")
		return this
	}
}
