import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
public class packagename20221221132905{
	@Keyword
	def compare2Values(String value1, String value2) {
		WebUiBuiltInKeywords.verifyEqual(value1, value2, FailureHandling.STOP_ON_FAILURE)
		
	}
}