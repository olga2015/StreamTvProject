package streamTvProject.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;

import net.thucydides.jbehave.ThucydidesJUnitStories;

public class AccceptanceTestSuite extends ThucydidesJUnitStories {

	public List<String> storyPath() {
		List<String> storyPath = new ArrayList<String>();
		String codeLocation = CodeLocations.codeLocationFromClass(
				this.getClass()).getFile();
		String storyName = System.getProperty("story");
		if (!(storyName == null)) {
			String[] stories = storyName.split(";");
			storyPath = new StoryFinder().findPaths(codeLocation,
					Arrays.asList(stories), Arrays.asList(""));
		} else {
			storyPath = super.storyPaths();
		}
		return storyPath;
	}
}
