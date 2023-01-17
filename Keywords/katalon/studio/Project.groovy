package katalon.studio

import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
import katalon.fw.lib.BaseWindow
import	org.openqa.selenium.Keys

public class Project extends BaseWindow<Project> {

	/**
	 * Open a specify project by full path
	 * @param path : the full path of project you want to open
	 * @return Project class
	 */
	public Project openProject(String path) {

		//Open File > Open Project
		this.clickMenu("File","Open Project")
		//Wait until select folder dialog is displayed
		Windows.waitForElementPresent(getTextboxByClass("Edit"),GlobalVariable.G_ShortTimeOut,FailureHandling.STOP_ON_FAILURE)
		//Set path to text box
		Windows.setText(getTextboxByClass("Edit"), path,FailureHandling.STOP_ON_FAILURE)
		sleepSomeTime()
		//Click Select Folder
		Windows.sendKeys(getTextboxByClass("Edit"),Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
		sleepSomeTime()
		Windows.click(getButtonByName("Select Folder"),FailureHandling.STOP_ON_FAILURE)
		//Wait for project is opened
		Windows.delay(GlobalVariable.G_ShortTimeOut,FailureHandling.STOP_ON_FAILURE)
		//Verify project is opened
		verifyProjectOpened(path)
		return this
	}

	/**
	 * Create  a new Generic project by full path
	 * @param projectName: Name of new project
	 * @param strSavePath: full path where to save the new project
	 * @return Project class 
	 */
	public Project createNewGenericProject(String strProjectName, String strSavePath = null) {
		//Open File > New > Project
		KeywordUtil.logInfo("Open menu File > New > Project")
		this.clickMenu("File","New", "Project")
		//Wait until New Project dialog is displayed
		KeywordUtil.logInfo("Wait for Open Projetc dialog appears")
		Windows.waitForElementPresent(getTextboxByName("Name"),Constants.WAIT_TIMEOUT_THIRTY_SECONDS,FailureHandling.CONTINUE_ON_FAILURE)
		//Set Project Name
		KeywordUtil.logInfo("Set New Project name")
		Windows.setText(getTextboxByName("Name"), strProjectName,FailureHandling.STOP_ON_FAILURE)
		//Select Generic
		KeywordUtil.logInfo("Select Generic radio button")
		Windows.click(getRadioButtonByName("Generic"))
		//Enter Location
		if(strSavePath != null) {
			KeywordUtil.logInfo("Click Browser... button")
			Windows.click(getButtonByName("Browse..."))
			KeywordUtil.logInfo("Set where to save this new generic project")
			Windows.setText(getTextboxByClass("Edit"), strSavePath,FailureHandling.STOP_ON_FAILURE)
			sleepSomeTime()
			//Click Select Folder
			KeywordUtil.logInfo("Press Enter")
			Windows.sendKeys(getTextboxByClass("Edit"),Keys.chord(Keys.ENTER),FailureHandling.STOP_ON_FAILURE)
			sleepSomeTime()
			KeywordUtil.logInfo("Click Select Folder button")
			Windows.click(getButtonByName("Select Folder"),FailureHandling.STOP_ON_FAILURE)
		}
		//Click OK button
		KeywordUtil.logInfo("Click OK button")
		Windows.click(getButtonByName("OK"))
		sleepSomeTime(Constants.WAIT_TIMEOUT_THIRTY_SECONDS)
		KeywordUtil.logInfo("Wait for Test Ops dialog appears")
		Windows.verifyElementPresent(getButtonByName("Refresh"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS,FailureHandling.CONTINUE_ON_FAILURE)
		KeywordUtil.logInfo("Click Close button on Test Ops dlg")
		Windows.click(getButtonByName("Close"), FailureHandling.OPTIONAL)
		//Verify project is opened
		verifyProjectOpened(strProjectName)
		return this
	}

	/**
	 * Verify project is opened or not
	 * @param projectName
	 * @return Project class
	 */
	public Project verifyProjectOpened(String projectName) {

		KeywordUtil.logInfo("Start verify project opened")
		//Wait project open
		Windows.waitForElementPresent(getTitleBarByTag("TitleBar"),GlobalVariable.G_ShortTimeOut,FailureHandling.OPTIONAL)
		//get text
		String title = Windows.getText(getTitleBarByTag("TitleBar"))
		//Compare
		boolean result = title.contains(projectName)
		if(result)
		{
			KeywordUtil.markPassed("Project is opened")
		}
		else {
			KeywordUtil.markFailedAndStop("Project isn't opened")
		}

		KeywordUtil.logInfo("Current title = ${projectName}")
		KeywordUtil.logInfo("End verify project opened")
		return this
	}

	public Project closeOpneningProject() {
		KeywordUtil.logInfo("Start closing Project")
		clickMenu("Project","Close")
		KeywordUtil.logInfo("End closing Project")
	}

	public Project openProjectSettingsAndEnableSmartWait(boolean enableSmartWait) {
		KeywordUtil.logInfo("Start opening Project Settings and config Smart Wait option")
		clickMenu("Project","Settings")
		KeywordUtil.logInfo("Double click on Execution tree item")
		Windows.doubleClick(getTreeItemByName("Execution"))
		KeywordUtil.logInfo("Click on WebUI tree item")
		Windows.click(getTreeItemByName("WebUI"))
		KeywordUtil.logInfo("Click on Default Smart Wait combo box")
		Windows.click(getComboBoxByName("Default Smart Wait"))
		if(enableSmartWait) {
			KeywordUtil.logInfo("Press E key")
			Windows.sendKeys(getComboBoxByName("Default Smart Wait"),Keys.chord('E', Keys.ENTER))
		}else {
			KeywordUtil.logInfo("Press D key")
			Windows.sendKeys(getComboBoxByName("Default Smart Wait"),Keys.chord('D', Keys.ENTER))
		}
		KeywordUtil.logInfo("Click Apply and Close button")
		Windows.click(getButtonByName("Apply and Close"))
		KeywordUtil.logInfo("End opening Project Settings and config Smart Wait option")
		return this
	}

	public Project checkCommonToolbarOfGenericProject() {
		def lstButtons = ["Save", "Save All", "Spy Web", "Record Web", "Spy Windows Object", "Create new Test Case", "Build CMD", "Stop", "Katalon TestCloud", "Katalon TestOps"] as String[]
		def lstSplitButtons = ["Git", "Spy Mobile", "Record Mobile", "Record Windows Actions", "Run (Ctrl  + Shift + A)", "Run (Ctrl + Shift + D)"] as String[]
		def lstCheckBoxes = ["Keyword", "Debug"] as String[]
		int j = 0
		boolean isDisplay = true

		KeywordUtil.logInfo("Begin verifying items on Toolbar")
		for(j = 0 ; j < lstButtons.size() ; j++) {
			KeywordUtil.logInfo("Checking toolbar item : " + lstButtons[j])
			isDisplay = Windows.verifyElementPresent(getButtonByName(lstButtons[j]), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.CONTINUE_ON_FAILURE)
			if(!isDisplay) {
				KeywordUtil.logInfo("Toolbar item : " + lstButtons[j] + " not found")
			}
		}

		for(j = 0 ; j < lstSplitButtons.size() ; j++) {
			KeywordUtil.logInfo("Checking toolbar item : " + lstSplitButtons[j])
			isDisplay = Windows.verifyElementPresent(getSplitButton(lstSplitButtons[j]), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.CONTINUE_ON_FAILURE)
			if(!isDisplay) {
				KeywordUtil.logInfo("Toolbar item : " + lstSplitButtons[j] + " not found")
			}
		}

		for(j = 0 ; j < lstCheckBoxes.size() ; j++) {
			KeywordUtil.logInfo("Checking toolbar item : " + lstCheckBoxes[j])
			isDisplay = Windows.verifyElementPresent(getCheckBoxByName(lstCheckBoxes[j]), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.CONTINUE_ON_FAILURE)
			if(!isDisplay) {
				KeywordUtil.logInfo("Toolbar item : " + getCheckBoxByName[j] + " not found")
			}
		}

		KeywordUtil.logInfo("Checking toolbar item : User Logging")
		isDisplay = Windows.verifyElementPresent(findWindowsObject("Activation/userLogging"), Constants.WAIT_TIMEOUT_THIRTY_SECONDS, FailureHandling.CONTINUE_ON_FAILURE)
		if(!isDisplay) {
			KeywordUtil.logInfo("Toolbar item : userLogging not found")
		}
		KeywordUtil.logInfo("Finish verifying items on Toolbar")

		return this
	}

}
