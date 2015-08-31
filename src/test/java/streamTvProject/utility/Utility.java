package streamTvProject.utility;

import net.thucydides.core.Thucydides;
import net.thucydides.jbehave.ThucydidesJUnitStories;

public class Utility {
	public static String getProperty(String propertyName) {
		return new ThucydidesJUnitStories().getEnvironmentVariables()
				.getProperty(propertyName);
	}

	public static String getFromSessionStringVariable(String key) {
		return (String) Thucydides.getCurrentSession().get(key);
	}

	@SuppressWarnings("unchecked")
	public static void putInSessionVariable(String key, Object value) {
		Thucydides.getCurrentSession().put(key, value);
	}

}
