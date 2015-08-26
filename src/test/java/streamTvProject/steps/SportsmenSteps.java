package streamTvProject.steps;

import java.util.LinkedList;
import java.util.List;

import org.jbehave.core.model.ExamplesTable;

import streamTvProject.pages.MainPage;
import static streamTvProject.utility.Utility.*;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class SportsmenSteps extends ScenarioSteps {

	private static final int TD_WITH_PHOTO_AVAILABILITY = 6;
	private static final int LAST_TD = 7;
	private static final int START_TD = 2;
	MainPage mainPage;

	@Step
	public void loginToSite() {
		mainPage.open();
		String login = getProperty("test.login");
		String password = getProperty("test.passw");
		mainPage.loginToMainPage(login, password);
	}

	@SuppressWarnings("unchecked")
	@Step
	public void createNewSpertsmen(ExamplesTable valuesTable) {
		List<String> inputData = valuesTable.getHeaders();
		mainPage.clickCreateNewSportsmen();
		for (String inputValueName : inputData) {
			String inputValue = valuesTable.getRow(0).get(inputValueName);
			switch (inputValueName) {
			case "lastName":
				mainPage.enterLastName(inputValue);
				Thucydides.getCurrentSession().put("lastName", inputValue);
				break;
			case "firstName":
				mainPage.enterFirstName(inputValue);
				Thucydides.getCurrentSession().put("firstName", inputValue);
				break;
			case "middleName":
				mainPage.enterMiddletName(inputValue);
				Thucydides.getCurrentSession().put("middleName", inputValue);
				break;
			case "birthDate":
				mainPage.enterBirthDate(inputValue);
				break;
			case "Region1":
				mainPage.selectRegion1(inputValue);
				Thucydides.getCurrentSession().put("Region1", inputValue);
				break;
			case "FST1":
				mainPage.selectFst1(inputValue);
				Thucydides.getCurrentSession().put("FST1", inputValue);
				break;
			case "trainer1":
				mainPage.enterTrainer1(inputValue);
				break;

			case "style":
				mainPage.selectStyle(inputValue);
				Thucydides.getCurrentSession().put("style", inputValue);
				break;
			case "age":
				mainPage.selectAge(inputValue);
				break;

			case "year":
				mainPage.selectYear(inputValue);
				Thucydides.getCurrentSession().put("year", inputValue);
				break;
			case "cardAvailability":
				mainPage.selectCardStage(inputValue);
				break;

			default:
				throw new IllegalArgumentException(
						"Input criteria parameter is not in the list of paramters");
			}
		}
		//
		waitABit(3000);
		mainPage.clickSaveNewSportsmen();
		mainPage.clickOnCloseNewWrestlerTab();
	}

	@Step
	public void findSportsman() {
		String lastName = (String) Thucydides.getCurrentSession().get(
				"lastName");
		mainPage.enterSearchCriteria(lastName);
		mainPage.clickButtonSearch();

	}

	@Step
	public List<String> getTextFromCells() {
		String textFromCell = "";
		List<String> textFromCells = new LinkedList<>();
		for (int i = START_TD; i <= LAST_TD; i++) {
			if (i == TD_WITH_PHOTO_AVAILABILITY) {
				continue;
			}
			textFromCell = mainPage.getTextFromCell(i);
			textFromCells.add(textFromCell);
		}
		return textFromCells;
	}
}
