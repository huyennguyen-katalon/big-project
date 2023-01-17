package katalon.fw.util

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling


public class App {
	/**
	 * Start application Application
	 */
	def static void Start(String path, int waitTime) {
		KeywordUtil.logInfo("Begin to start Application Under Test: ${path}");
		//Set current folder to path
		String folder = getParentDirPath(path);
		KeywordUtil.logInfo("Current folder: ${folder}");
		Runtime.getRuntime().exec("powershell.exe cd ${folder}");
		// Start program
		Windows.startApplication(path,FailureHandling.STOP_ON_FAILURE);
		// Waiting for starting
		Windows.delay(waitTime);
		KeywordUtil.logInfo("End starting");

	}

	def static String getParentDirPath(String fileOrDirPath) {
		boolean endsWithSlash = fileOrDirPath.endsWith("\\");
		//fileOrDirPath.lastIndexOf(str, fromIndex)
		return fileOrDirPath.substring(0, fileOrDirPath.lastIndexOf("\\",
				endsWithSlash ? fileOrDirPath.length() - 2 : fileOrDirPath.length() - 1));
	}

	/**
	 * Terminate an application
	 */
	def static void Terminate(String exeName ) {
		try {
			Runtime.getRuntime().exec("taskkill /IM ${exeName}");
		}
		catch(Exception e) {
			KeywordUtil.logInfo("Error when closing ${e}");
		}
	}
}
