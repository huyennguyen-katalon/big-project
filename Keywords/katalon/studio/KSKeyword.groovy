package katalon.studio

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import katalon.fw.lib.BaseWindow
import	org.openqa.selenium.Keys;

public class KSKeyword extends BaseWindow<KSKeyword> {

	public KSKeyword addNewKeyword(String packageName,String className) {
		KeywordUtil.logInfo("Start: create new test suite collection")
		//Open dialog
		clickMenu("File", "New", "Keyword");
		//Input data
		enterData(packageName,className);
		//OK to close dialog
		Windows.click(getDialogButton("New", "OK"));

		KeywordUtil.logInfo("End: create new test suite collection")
		return this;
	}

	protected  void enterData(String packageName,String className) {
		KeywordUtil.logInfo("Start to enter data for new Package")
		Windows.setText(getDialogTextBox("New", 1), packageName);
		if(className.length()>0)
			Windows.setText(getDialogTextBox("New", 2),className);
		KeywordUtil.logInfo("End to enter data for new Package")
	}

	public KSKeyword provideKeywordSteps(String className, String keywordData, int timeOut = GlobalVariable.G_ShortTimeOut){
		KeywordUtil.logInfo("Start to clear all existing data in the class file")

		Windows.click(getScriptWindow(className),FailureHandling.STOP_ON_FAILURE)

		Windows.sendKeys(getScriptWindow(className),Keys.chord(Keys.CONTROL,'A'),FailureHandling.STOP_ON_FAILURE);

		Windows.sendKeys(getScriptWindow(className),Keys.chord(Keys.DELETE),FailureHandling.STOP_ON_FAILURE);

		sleepSomeTime();
		Windows.setText(getScriptWindow(className),keywordData,FailureHandling.STOP_ON_FAILURE);

		KeywordUtil.logInfo("Save Data for keyword")
		Windows.sendKeys(getScriptWindow(className),Keys.chord(Keys.CONTROL,'S'),FailureHandling.STOP_ON_FAILURE);

		return this;
	}

	protected String getSampleKeyword(String className) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("import com.kms.katalon.core.annotation.Keyword\n")
				.append("import com.kms.katalon.core.model.FailureHandling\n")
				.append("import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords\n")
				.append("public class ").append(className).append("{\n")
				.append("@Keyword\n")
				.append("def compare2Values(String value1, String value2){\n")
				.append("WebUiBuiltInKeywords.verifyEqual(value1, value2, FailureHandling.STOP_ON_FAILURE)\n");
		return contentBuilder.toString();
	}
}
