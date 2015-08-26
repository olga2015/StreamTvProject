package streamTvProject.utility;

import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;
import net.thucydides.jbehave.ThucydidesJUnitStories;

import org.jruby.ir.instructions.defined.GetDefinedConstantOrMethodInstr;
import org.openqa.selenium.JavascriptExecutor;

public class Utility {
	public static String getProperty(String propertyName) {
		return new ThucydidesJUnitStories().getEnvironmentVariables()
				.getProperty(propertyName);
	}

}
