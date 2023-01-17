import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Activation
import katalon.fw.util.Helper

"DATA Start PREPARATION"
strEmail = GlobalVariable.G_Email
strPass =  GlobalVariable.G_Password
"DATA End PREPARATION"

'Enter Email and Password for activation Katalon.'
Window.open(Activation).enterInfoAndClickActive(strEmail, strPass)
					   .handleAfterActivateForTheExperienceUsers()

'Verify user log in success'
Window.open(Activation).verifyUserLogInSuccessfully()

'If any error occurs while running, capture the screenshot for analyze later'
@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed( ) {
	Helper.handleWhenTestCaseFailed()
}