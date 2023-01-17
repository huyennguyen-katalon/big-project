package katalon.fw.lib

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

public class BaseWindow <T> extends BaseElement {

	protected T	sleepSomeTime(int seconds = 1) {
		Windows.delay(seconds);
		return this;
	}

	/**
	 * Open level 1 menu ( File / Action...)
	 * @param level1 : display text of menu 
	 * @return
	 */
	protected T clickMenu(String level1) {
		//Get object

		WindowsTestObject obj = getLevel1Menu(level1);
		// Click on object to open menu
		Windows.click(obj,FailureHandling.STOP_ON_FAILURE);
		return this;
	}

	/**
	 * Open level 2 menu. Ex: File > New
	 * @param level1 : display text of level 1
	 * @param level2 : display text of level 2
	 * @return
	 */
	protected T clickMenu(String level1,String level2) {
		//Get object
		WindowsTestObject objlvl1 = getLevel1Menu(level1);
		WindowsTestObject objlvl2 = getLevel2Menu(level2);
		//Open level 1 menu
		Windows.click(objlvl1,FailureHandling.STOP_ON_FAILURE);
		//sleepSomeTime();
		//Open level 2 menu
		Windows.click(objlvl2,FailureHandling.STOP_ON_FAILURE);
		return this;
	}

	/**
	 * Open level 3 menu
	 * @param level1
	 * @param level2
	 * @param level3
	 * @return
	 */
	protected T clickMenu(String level1,String level2, String level3) {
		//Get object
		WindowsTestObject objlvl1 = getLevel1Menu(level1);
		WindowsTestObject objlvl2 = getLevel2Menu(level2);
		WindowsTestObject objlvl3 = getLevel2Menu(level3);
		//Open level 1 menu
		Windows.click(objlvl1,FailureHandling.STOP_ON_FAILURE);
		sleepSomeTime();
		//Open level 2 menu
		Windows.click(objlvl2,FailureHandling.STOP_ON_FAILURE);
		sleepSomeTime();
		//Open level 3 menu
		Windows.click(objlvl3,FailureHandling.STOP_ON_FAILURE);
		sleepSomeTime();
		return this;
	}

	protected T switchView(String viewName) {
		//Get tab item object
		WindowsTestObject obj = getTabItem(viewName);
		//Click on object
		int offsetX = 0;
		int offsetY = 0;
		if(viewName.toLowerCase() == "script") {
			offsetX = 106;
			offsetY = 15;
			Windows.clickElementOffset(obj,offsetX,offsetY,FailureHandling.STOP_ON_FAILURE);
		}
		else if (viewName.toLowerCase() == "manual")
		{
			Windows.click(obj,FailureHandling.STOP_ON_FAILURE);
		}
	}
}

