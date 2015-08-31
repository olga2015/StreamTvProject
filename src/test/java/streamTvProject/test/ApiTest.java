package streamTvProject.test;

import static com.jayway.restassured.RestAssured.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import static streamTvProject.utility.Utility.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTest {
	private String loginToSiteAndGetSessionId() {
		baseURI = "http://streamtv.net.ua/base";
		return given().body("{\"username\":\"auto\",\"password\":\"test\"}")
				.when().post("/php/login.php").thenReturn()
				.getCookie("PHPSESSID");
	}

	@Given("create sportsman")
	public void createSportsman() {
		String sessionId = loginToSiteAndGetSessionId();

		String idOfCreatedSportsman = given()
				.cookie("PHPSESSID", sessionId)
				.body("{\"new\":\"true\",\"id_wrestler\":\"0\",\"card_state\":\"1\",\"lname\":\"1\",\"fname\":\"1\",\"dob\":\"01-01-1990\",\"mname\":\"1\",\"region1\":\"3\",\"fst1\":\"3\",\"style\":\"2\",\"lictype\":\"1\",\"expires\":\"2016\"}")
				.when().post("/php/wrestler/create.php").body().jsonPath()
				.getString("id");
		putInSessionVariable("idOfCreatedSportsman", idOfCreatedSportsman);
		System.out.println("Id of created sportsman is:  "
				+ idOfCreatedSportsman);
	}

	@When("read sportsman")
	public void readSportsman() {
		String sessionId = loginToSiteAndGetSessionId();
		String idOfCreatedSportsman = getFromSessionStringVariable("idOfCreatedSportsman");
		String lastNameFromRead = given().cookie("PHPSESSID", sessionId).when()
				.get("/php/wrestler/read.php?id=" + idOfCreatedSportsman)
				.body().jsonPath().getString("lname");
		putInSessionVariable("lastNameFromRead", lastNameFromRead);
		System.out.println("Last tname from read action is: "
				+ lastNameFromRead);
	}

	@Then("verify read name equals to $expName")
	public void verifyReadName(@Named("expName") String expName) {
		String lastNameFromRead = getFromSessionStringVariable("lastNameFromRead");
		assertThat("", expName, equalTo(lastNameFromRead));
	}

	@Then("update sportsman and verify success answer:$updateValues")
	public void updateSportsman(ExamplesTable updateValues) {
		String sessionId = loginToSiteAndGetSessionId();
		String idOfCreatedSportsman = getFromSessionStringVariable("idOfCreatedSportsman");
		Parameters rowNumberInTable = updateValues.getRowAsParameters(0);
		String mname = rowNumberInTable.valueAs("mname", String.class);
		putInSessionVariable("mname", mname);
		String dob = rowNumberInTable.valueAs("dob", String.class);
		putInSessionVariable("dob", dob);
		given().cookie("PHPSESSID", sessionId)
				.body("{\"new\":false,\"id_wrestler\":"
						+ idOfCreatedSportsman
						+ ",\"attaches\":[],\"card_state\":\"1\",\"lname\":\"Auto\",\"fname\":\"Auto\",\"dob\":\""
						+ dob
						+ "\",\"mname\":\""
						+ mname
						+ "\",\"region1\":\"4\",\"fst1\":\"3\",\"style\":\"1\",\"expires\":\"2017\",\"lictype\":\"2\"}")
				.when().post("/php/wrestler/update.php").then().statusCode(200);
	}

	@Then("delete sportsman and verify success answer from server")
	public void deleteSportsman() {
		String sessionId = loginToSiteAndGetSessionId();
		String idOfCreatedSportsman = getFromSessionStringVariable("idOfCreatedSportsman");
		given().cookie("PHPSESSID", sessionId).when()
				.get("/php/wrestler/delete.php?id=" + idOfCreatedSportsman)
				.then().statusCode(200);

	}
}
