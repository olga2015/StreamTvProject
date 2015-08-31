package streamTvProject.steps;

import java.util.LinkedList;
import java.util.List;

import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import streamTvProject.pages.MainPage;
import static streamTvProject.utility.Utility.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class SportsmenSteps extends ScenarioSteps {

	private static final String LIB_FOLDER = "c:\\StreamTvProject\\lib\\";
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

	@Step
	public void clickCreateNewSportsmen() {
		mainPage.clickCreateNewSportsmen();
	}

	@Step
	public void addOrEditDataSportsman(ExamplesTable valuesTable) {
		List<String> inputData = valuesTable.getHeaders();
		for (String inputValueName : inputData) {
			String inputValue = valuesTable.getRow(0).get(inputValueName);
			switch (inputValueName) {
			case "lastName":
				mainPage.enterLastName(inputValue);
				putInSessionVariable("lastName", inputValue);
				break;
			case "firstName":
				mainPage.enterFirstName(inputValue);
				putInSessionVariable("firstName", inputValue);
				break;
			case "middleName":
				mainPage.enterMiddletName(inputValue);
				putInSessionVariable("middleName", inputValue);
				break;
			case "birthDate":
				mainPage.enterBirthDate(inputValue);
				break;
			case "Region1":
				mainPage.selectRegion1(inputValue);
				putInSessionVariable("Region1", inputValue);
				break;
			case "FST1":
				mainPage.selectFst1(inputValue);
				putInSessionVariable("FST1", inputValue);
				break;
			case "trainer1":
				mainPage.enterTrainer1(inputValue);
				break;

			case "style":
				mainPage.selectStyle(inputValue);
				putInSessionVariable("style", inputValue);
				break;
			case "age":
				mainPage.selectAge(inputValue);
				break;

			case "year":
				mainPage.selectYear(inputValue);
				putInSessionVariable("year", inputValue);
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
	public void findSportsman(String name) {
		mainPage.enterSearchCriteria(name);
		mainPage.clickButtonSearch();
	}

	@Step
	public List<String> getTextFromCells() {
		String textFromCell = "";
		List<String> textFromCells = new LinkedList<String>();
		for (int i = START_TD; i <= LAST_TD; i++) {
			if (i == TD_WITH_PHOTO_AVAILABILITY) {
				continue;
			}
			textFromCell = mainPage.getTextFromCell(i);
			textFromCells.add(textFromCell);
		}
		return textFromCells;
	}

	@Step
	public void editSportsman() {
		mainPage.clickEditFirstRecord();
	}

	@Step
	public void deleteSportsman() {
		mainPage.clickOnDeleteSportsmanProfile();
	}

	@Step
	public List<String> getAllNamesOnPage() {
		return mainPage.getAllNamesOnPage();
	}

	@Step
	public List<String> getAllRegions() {
		return mainPage.getAllregionsOnPage();
	}

	@Step
	public List<String> getAllFstOnPage() {
		return mainPage.getAllFstOnPage();
	}

	@Step
	public void findWithRegionAndFst(ExamplesTable valuesTable) {
		Parameters rowNumberInTable = valuesTable.getRowAsParameters(0);
		String region = rowNumberInTable.valueAs("Region", String.class);
		putInSessionVariable("region", region);
		String fst = rowNumberInTable.valueAs("Fst", String.class);
		putInSessionVariable("fst", fst);
		mainPage.selectRegionFromFilter(region);
		mainPage.selectFstFromFilter(fst);
		mainPage.clickButtonSearch();
	}

	@Step
	public void uploadPhoto(String photoName) {
		String absolutePath = LIB_FOLDER + photoName;
		mainPage.uploadPhoto(absolutePath);
	}

	@Step
	public void uploadAttachment(String attach) {
		putInSessionVariable("attach", attach);
		String absolutePath = LIB_FOLDER + attach;
		mainPage.uploadAttach(absolutePath);
	}

	@Step
	public boolean isImgDisplayed() {
		return mainPage.isImgDisplayed();
	}

	@Step
	public String getAttachName() {
		return mainPage.getAttachName();
	}

	@Step
	public void deleteAttach() {
		mainPage.clickOnDeletAttachment();
	}
}
