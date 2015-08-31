package streamTvProject.test;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import net.thucydides.core.annotations.Steps;
import streamTvProject.steps.SportsmenSteps;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static streamTvProject.utility.Utility.*;
import static org.junit.Assert.*;

public class SportsmenTest {

	@Steps
	SportsmenSteps sportsmenSteps;

	@Given("User opens Login Page and login to the base page")
	public void loginToPage() {
		sportsmenSteps.loginToSite();
	}

	@When("delete sportsman with name $sportsmanName")
	public void deleteSportsmanName(@Named("sportsmanName") String sportsmanName) {
		sportsmenSteps.findSportsman(sportsmanName);
		int recordsCount = sportsmenSteps.getAllNamesOnPage().size();
		if (recordsCount > 0) {
			for (int i = 0; i < recordsCount; i++) {
				sportsmenSteps.editSportsman();
				sportsmenSteps.deleteSportsman();
				sportsmenSteps.findSportsman(sportsmanName);
			}
		}
	}

	@When("add new sportsmen with data:$inputDataTable")
	public void createNewSportsmenWithInputData(ExamplesTable inputDataTable) {
		sportsmenSteps.clickCreateNewSportsmen();
		sportsmenSteps.addOrEditDataSportsman(inputDataTable);

	}

	@When("find current sportsman and edit with values:$inputDataTable")
	public void findAndEditSportsmanData(ExamplesTable inputDataTable) {
		String name = getFromSessionStringVariable("lastName");
		sportsmenSteps.findSportsman(name);
		sportsmenSteps.editSportsman();
		sportsmenSteps.addOrEditDataSportsman(inputDataTable);
	}

	@When("find current sportsman and delete profile")
	public void findAndDeleteSportsman() {
		String name = getFromSessionStringVariable("lastName");
		sportsmenSteps.findSportsman(name);
		sportsmenSteps.editSportsman();
		sportsmenSteps.deleteSportsman();
	}

	@When("finds data with filters:$inputDataTable")
	public void findDataWithFilters(ExamplesTable inputDataTable) {
		sportsmenSteps.findWithRegionAndFst(inputDataTable);
	}

	@When("finds sportsman and uploads photo with name $photoName")
	public void uploadPhoto(@Named("photoName") String photoName) {
		String name = getFromSessionStringVariable("lastName");
		sportsmenSteps.findSportsman(name);
		sportsmenSteps.editSportsman();
		sportsmenSteps.uploadPhoto(photoName);
	}

	@When("finds sportsman and uploads attachment with name $attach")
	public void uploadAttach(@Named("attach") String attach) {
		String name = getFromSessionStringVariable("lastName");
		sportsmenSteps.findSportsman(name);
		sportsmenSteps.editSportsman();
		sportsmenSteps.uploadAttachment(attach);
	}

	@When("deletes attachment")
	public void deleteAttach() {
		sportsmenSteps.deleteAttach();
	}

	@Then("user finds added sportsmen and checks saved data")
	public void findSportsmanAndVerifyData() {
		String name = getFromSessionStringVariable("lastName");
		sportsmenSteps.findSportsman(name);
		List<String> actDataOnPage = sportsmenSteps.getTextFromCells();
		String expLastName = getFromSessionStringVariable("lastName");
		String expFirstName = getFromSessionStringVariable("firstName");
		String expMiddleName = getFromSessionStringVariable("middleName");
		String expRegion1 = getFromSessionStringVariable("Region1");
		String expFst1 = getFromSessionStringVariable("FST1");
		String expYear = getFromSessionStringVariable("year");
		String expStyle = getFromSessionStringVariable("style");
		StringBuilder firstItemInList = new StringBuilder();
		firstItemInList.append(expLastName).append(" ").append(expFirstName)
				.append(" ").append(expMiddleName);
		List<String> expList = Arrays.asList(firstItemInList.toString(),
				expRegion1, expFst1, expYear, expStyle);
		assertThat("Data in the first record is not as expected",
				actDataOnPage, equalTo(expList));
	}

	@Then("verify data has been correctly selected")
	public void checkFilteredData() {
		String expRegion = getFromSessionStringVariable("region");
		String expFst = getFromSessionStringVariable("fst");
		List<String> actRegions = sportsmenSteps.getAllRegions();
		List<String> actFsts = sportsmenSteps.getAllFstOnPage();
		for (String actRegion : actRegions) {
			assertThat("Regions are filtered not correctly", actRegion,
					equalTo(expRegion));
		}
		for (String actFst : actFsts) {
			assertThat("FST are filtered not correctly", actFst,
					equalTo(expFst));
		}
	}

	@Then("verify image has been uploded on the Page")
	public void checkImgUploaded() {
		assertTrue("Image is not displayed on Page",
				sportsmenSteps.isImgDisplayed());
	}

	@Then("verify correct attachment was uploaded")
	public void checkCorrectAttachmentUploaded() {
		String expAttachName = getFromSessionStringVariable("attach");
		String actAttachName = sportsmenSteps.getAttachName();
		assertThat("Not correct attachment has been uploaded", actAttachName,
				equalTo(expAttachName));
	}

}
