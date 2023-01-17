package katalon.fw.lib

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.testobject.WindowsTestObject.LocatorStrategy
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class BaseElement {

	/**
	 * Get TreeView Item by name
	 * @param name
	 */
	public WindowsTestObject getTreeItemByName(String name) {
		return findWindowsObject("Common/treeItemByName",[('name'):name]);
	}

	/**
	 * Get window test object - mnuLevel1
	 * @param name: display text of menu item
	 * @return
	 */
	public WindowsTestObject getLevel1Menu(String name) {
		return findWindowsObject("MenuBar/mnuLevel1",[('name'):name]);
	}

	/**
	 * Get window test object - mnuLevel2
	 * @param name: display text of menu item
	 * @return
	 */
	public WindowsTestObject getLevel2Menu(String name) {
		return findWindowsObject("MenuBar/mnuLevel2",[('name'):name]);
	}

	/**
	 * Get window test object - mnuLevel3
	 * @param name : display text of menu item
	 * @return
	 */
	public WindowsTestObject getLevel3Menu(String name) {
		return findWindowsObject("MenuBar/mnuLevel3",[('name'):name]);
	}

	/**
	 * Get textbox with locator strategy = Class name
	 * @param name
	 * @return
	 */
	public WindowsTestObject getTextboxByClass(String name) {
		return findWindowsObject("Common/txtByClass",[('name'):name]);
	}

	/**
	 * Get textbox by name
	 * @param name
	 */
	public WindowsTestObject getTextboxByName(String name) {
		return findWindowsObject("Common/txtByName",[('name'):name]);
	}

	/**
	 * Get password text box
	 * @param name
	 */
	public WindowsTestObject getPwdTextbox(String name) {
		return findWindowsObject("Object Repository/Activation/txtPassword");
	}

	public WindowsTestObject getComboBoxByName(String name) {
		return findWindowsObject("Common/cbboxByName",[('name'):name]);
	}

	public WindowsTestObject getCheckBoxByName(String name) {
		return findWindowsObject("Common/ckbByName",[('name'):name]);
	}

	/**
	 * Get button with locator strategy = Name
	 * @param name
	 * @return
	 */
	public WindowsTestObject getButtonByName(String name) {
		return findWindowsObject("Common/btnByName",[('name'):name]);
	}

	/**
	 * Get Radio Button with locator strategy = Name
	 * @param strName
	 * @return
	 */
	public WindowsTestObject getRadioButtonByName(String strName) {
		return findWindowsObject("Common/radButtonByName",[('name'):strName]);
	}

	/**
	 * Get window title bar by tag name
	 * @param name
	 * @return
	 */
	public WindowsTestObject getTitleBarByTag(String name) {
		return findWindowsObject("Common/windowTitle",[('name'):name]);
	}

	public WindowsTestObject getTabItem(String name) {
		return findWindowsObject("Common/tabByName",[('name'):name]);
	}

	public WindowsTestObject getMultilineTextBox(String name) {
		return findWindowsObject("Common/txtMultilineByName",[('name'):name]);
	}

	public WindowsTestObject getScriptWindow(String headerName) {
		return findWindowsObject("Common/txtScript",[('headerName'):headerName]);
	}

	public WindowsTestObject getJobProgressLabel(String testCaseName) {
		return findWindowsObject("Katalon/RightPanel/JobProgress/lblTestCaseExecuted",[('testCaseName'):testCaseName]);
	}

	public WindowsTestObject getCurrentTestCasePass() {
		return findWindowsObject("Katalon/RightPanel/JobProgress/lblTestCasePassed");
	}

	public WindowsTestObject getCurrentTestCaseFail() {
		return findWindowsObject("Katalon/RightPanel/JobProgress/lblTestCaseFailed");
	}

	/**
	 * Get split button
	 * @param name
	 * @return
	 */
	public WindowsTestObject getSplitButton(String name) {
		return findWindowsObject("Common/btnToolbarSplitButton",[('name'):name]);
	}

	/**
	 * Get a button on a specify dialog
	 * @param dlgTitle
	 * @param name
	 * @return
	 */
	public WindowsTestObject getDialogButton(String dlgTitle,String name) {
		return findWindowsObject("Common/btnByWindowName",[('windowName'):dlgTitle,('name'):name]);
	}

	/**
	 * Get a textbox on a specify dialog
	 * @param dlgTitle
	 * @param position
	 * @return
	 */
	public WindowsTestObject getDialogTextBox(String dlgTitle, int position) {
		return findWindowsObject("Common/txtByWindow",[('windowName'):dlgTitle,('id'):position]);
	}

	/**
	 * Get toolbar button by name
	 * @param name
	 * @return
	 */
	public WindowsTestObject getToolbarButtonByName(String name) {
		return findWindowsObject("Common/toolbarButtonByName",[('name'):name]);
	}

	/**
	 * get dialog checkbox
	 * @param dlgTitle
	 * @param name
	 * @return
	 */
	public WindowsTestObject getDialogCheckBox(String dlgTitle, String name) {
		return findWindowsObject("Common/ckbByWindowName",[('windowName'):dlgTitle,('name'):name]);
	}

	/**
	 * Get multiline textbox on a dialog
	 * @param dlgTitle
	 * @param name
	 * @return
	 */
	public WindowsTestObject getDialogMultiline(String dlgTitle,String name) {
		return findWindowsObject("Common/txtMultilineByWindow",[('windowName'):dlgTitle,('name'):name]);
	}

	/**
	 * get test case in a test suite
	 * @param testCaseName
	 * @return
	 */
	public WindowsTestObject getTestCaseInTestSuite(String testCaseName) {
		return findWindowsObject("Common/lstTestCaseItem",[('name'):testCaseName]);
	}
}

