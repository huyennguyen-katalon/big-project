//KeywordUtil.logInfo("Start to count the total times of Test Case: " + testCasePath)
import java.nio.file.Files as Files
import java.nio.file.Path as Path
import java.nio.file.Paths as Paths
import org.apache.commons.io.FileUtils as FileUtils
import katalon.fw.util.ScreenshotHelper
import internal.GlobalVariable as GlobalVariable

/*
 * Please refer document for more detail in here:
 * https://katalon.atlassian.net/wiki/spaces/KSR/pages/2444558925/How+to+make+embedded+Screenshot+into+HTML+file+when+working+with+Windows+Automation
 * 
 */

fileType = '.png'

fileName = 'D:\\1MyProjects\\Kat\\MyAutoCode\\katalon-gen4-qengineer-automation\\Reports\\20230110_105956\\Sample\\20230110_105956\\execution0.log'

fileNameW = 'D:\\1MyProjects\\Kat\\MyAutoCode\\katalon-gen4-qengineer-automation\\Reports\\20230110_105956\\Sample\\20230110_105956\\execution1.log'

isFound = true

int counter = 0

try {
	//KeywordUtil.logInfo("Start to read the file: "+ fileName)
	BufferedReader reader = new BufferedReader(new FileReader(fileName))

	isContains = false

	totalExtraLine = 0

	for (String line; (line = reader.readLine()) != null; ) {
		template = screenshotTemplate

		counter++
		
		if(line.contains("${ScreenshotHelper.TAKING_SCREENSHOT}: ${GlobalVariable.G_ScreenshotFolder}")) {		
			isContains = true

			int temp = counter

			println(temp )//Identify row need insert step screenshot
				
			//parse line to get file name
			String currLine = line

			int index = currLine.lastIndexOf('\\')

			screenshotName = currLine.substring(index + 1)

			println(screenshotName)

			screenshotName = (screenshotName.split('<')[0])

			println(screenshotName)

			//parse template to relace file name
			template = screenshotTemplate.replace('ScreenshotName.png', screenshotName)

			println(template)

			//add template before the line found
			Path myPath = Paths.get(fileNameW)

			List<String> lines = Files.readAllLines(myPath)

			lines.add((counter + totalExtraLine) - 9, template)

			totalExtraLine += 41

			Files.write(myPath, lines) //
		} 
	}
	
	FileUtils.copyFile(new File(fileNameW), new File(fileName))

	if (isContains == true) {
		isFound = true
	} //KeywordUtil.logInfo("End to read the file: "+ fileName)
	else {
		isFound = false
	}
}
catch (Exception e) {
	print(e.toString())
}