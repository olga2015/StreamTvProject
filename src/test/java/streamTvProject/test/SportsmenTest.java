package streamTvProject.test;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;
import streamTvProject.steps.SportsmenSteps;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SportsmenTest {

	@Steps
	SportsmenSteps sportsmenSteps;

	@Given("User opens Login Page and login to the base page")
	public void loginToPage() {
		sportsmenSteps.loginToSite();
	}

	@When("add new sportsmen with data:$inputDataTable")
	public void createNewSportsmenWithInputData(ExamplesTable inputDataTable) {
		sportsmenSteps.createNewSpertsmen(inputDataTable);

	}

	@Then("user finds added sportsmen and checks saved data")
	public void findSportsmanAndVerifyData() {
		sportsmenSteps.findSportsman();
		List<String> actDataOnPage = sportsmenSteps.getTextFromCells();
		String expLastName = (String) Thucydides.getCurrentSession().get(
				"lastName");
		String expFirstName = (String) Thucydides.getCurrentSession().get(
				"firstName");
		String expMiddleName = (String) Thucydides.getCurrentSession().get(
				"middleName");
		String expRegion1 = (String) Thucydides.getCurrentSession().get(
				"Region1");
		String expFst1 = (String) Thucydides.getCurrentSession().get("FST1");
		String expYear = (String) Thucydides.getCurrentSession().get("year");
		String expStyle = (String) Thucydides.getCurrentSession().get("style");
		StringBuilder firstItemInList = new StringBuilder();
		firstItemInList.append(expLastName).append(" ").append(expFirstName)
				.append(" ").append(expMiddleName);
		List<String> expList = Arrays.asList(firstItemInList.toString(),
				expRegion1, expFst1, expYear, expStyle);
		assertThat("Data in the first record is not as expected",
				actDataOnPage, equalTo(expList));
	}

}
