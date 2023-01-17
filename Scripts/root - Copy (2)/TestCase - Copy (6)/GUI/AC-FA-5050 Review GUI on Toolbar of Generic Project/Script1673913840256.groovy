import com.kms.katalon.core.annotation.TearDownIfError
import com.kms.katalon.core.annotation.TearDownIfFailed
import internal.GlobalVariable as GlobalVariable
import katalon.fw.lib.Window
import katalon.fw.util.Helper
import katalon.studio.Project

"Data Start Preparation"
String projectName = Helper.genUniqueString("ProjectName")
"Create a new Generic Project"
Window.open(Project).createNewGenericProject(projectName)
"Verify some item must be displayed on toolbar"
Window.open(Project).checkCommonToolbarOfGenericProject()
"Close openning project"
Window.open(Project).closeOpneningProject()

@TearDownIfError
@TearDownIfFailed
def handleWhenTestScriptFailed( ) {
	Helper.handleWhenTestCaseFailed()
}