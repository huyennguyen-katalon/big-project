

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project
import katalon.studio.TestCase
import katalon.fw.util.App
import katalon.studio.Activation

"DATA Start PREPARATION"
strEmail = "ltmaithuong@gmail.com"
strPass =  "Fc5L+jUiy6WOK711jibG9A=="
"DATA End PREPARATION"

App.Start(GlobalVariable.G_KatalonPath, GlobalVariable.G_StartTime)

Window.open(Activation).enterInfoAndClickActive(strEmail, strPass)
						.handleAfterActivate()

Window.open(Activation).verifyUserIsLogging()

