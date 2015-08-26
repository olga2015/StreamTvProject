package streamTvProject.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://streamtv.net.ua/base/")
public class MainPage extends PageObject {

	private static final String TEXT_AFTER_LOADING_PAGE = "Login";

	@FindBy(xpath = "//fg-input[@id='username']//input")
	private WebElementFacade inputLogin;

	@FindBy(xpath = "//fg-input[@value='user.password']//input")
	private WebElementFacade inputPassword;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElementFacade buttonLogin;

	@FindBy(xpath = "//div[@class='col-lg-12']//button[2]")
	private WebElementFacade buttonCreateNewSportsmen;

	@FindBy(xpath = "//fg-input[@value='wr.lname']//input")
	private WebElementFacade inputLastName;

	@FindBy(xpath = "//fg-input[@value='wr.fname']//input")
	private WebElementFacade inputFirstName;

	@FindBy(xpath = "//fg-input[@value='wr.mname']//input")
	private WebElementFacade inputMiddleName;

	@FindBy(xpath = "//fg-date[@value='wr.dob']//input")
	private WebElementFacade inputBirthDate;

	@FindBy(xpath = "//fg-select[@value='wr.region1']//select")
	private WebElementFacade selectRegion1;

	@FindBy(xpath = "//fg-select[@value='wr.region2']//select")
	private WebElementFacade selectRegion2;

	@FindBy(xpath = "//fg-select[@value='wr.fst1']//select")
	private WebElementFacade selectFst1;

	@FindBy(xpath = "//fg-select[@value='wr.fst2']//select")
	private WebElementFacade selectFst2;

	@FindBy(xpath = "//fg-typeahead[@value='wr.trainer1']//input")
	private WebElementFacade inputTrainer1;

	@FindBy(xpath = "//fg-typeahead[@value='wr.trainer2']//input")
	private WebElementFacade inputTrainer2;

	@FindBy(xpath = "//fg-select[@value='wr.style']//select")
	private WebElementFacade selectStyle;

	@FindBy(xpath = "//fg-select[@value='wr.lictype']//select")
	private WebElementFacade selectAge;

	@FindBy(xpath = "//fg-select[@value='wr.expires']//select")
	private WebElementFacade selectYear;

	@FindBy(xpath = "//f-select[@value='wr.card_state']//select")
	private WebElementFacade selectCardState;

	@FindBy(xpath = "//div[@class='wrestler col-sm-12']//button[1]")
	private WebElementFacade buttonSaveNewSportsmen;

	@FindBy(xpath = "//div[@class='col-lg-12']//input")
	private WebElementFacade inputSearch;

	@FindBy(xpath = "//div[@class='col-lg-12']//button")
	private WebElementFacade buttonSearch;

	@FindBy(xpath = "//div[contains(text(), 'Wrestlers')]")
	private WebElementFacade tabWrestler;

	private static final String SPINNER_LOADER = "//div[class='spinner-loader']";

	private static final String DATA_IN_CELL_FOR_FIRST_RECORD = "//div[@class='col-sm-12 mt80']//tr/td[%d]";

	private static final String CLOSE_WRESTLER_TAB = "//div[contains(text(), '%s')]//ico[@icon='glyphicon-remove']";

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void loginToMainPage(String login, String password) {
		getDriver().manage().window().maximize();
		waitForAnyTextToAppear(TEXT_AFTER_LOADING_PAGE);
		inputLogin.type(login);
		inputPassword.type(password);
		buttonLogin.click();
	}

	public void clickCreateNewSportsmen() {
		buttonCreateNewSportsmen.click();
	}

	public void enterLastName(String lastName) {
		inputLastName.type(lastName);
	}

	public void enterFirstName(String firstName) {
		inputFirstName.type(firstName);
	}

	public void enterMiddletName(String middleName) {
		inputMiddleName.type(middleName);
	}

	public void enterBirthDate(String birthDate) {
		inputBirthDate.type(birthDate);
	}

	public void selectRegion1(String region) {
		selectRegion1.selectByVisibleText(region);
	}

	public void selectRegion2(String region) {
		selectRegion2.selectByVisibleText(region);
	}

	public void selectFst1(String fst) {
		selectFst1.selectByVisibleText(fst);
	}

	public void selectFst2(String fst) {
		selectFst2.selectByVisibleText(fst);
	}

	public void enterTrainer1(String trainer) {
		inputTrainer1.type(trainer);
	}

	public void enterTrainer2(String trainer) {
		inputTrainer2.type(trainer);
	}

	public void selectStyle(String style) {
		selectStyle.selectByVisibleText(style);
	}

	public void selectAge(String age) {
		selectAge.selectByVisibleText(age);
	}

	public void selectYear(String year) {
		selectYear.selectByVisibleText(year);
	}

	public void selectCardStage(String cardStage) {
		selectCardState.selectByVisibleText(cardStage);
	}

	public void clickSaveNewSportsmen() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].style.border='4px solid red'",
				buttonSaveNewSportsmen);
		buttonSaveNewSportsmen.click();
		waitForAbsenceOf(SPINNER_LOADER);
		//
		waitABit(5000);
	}

	public void clickOnWrestlerTab() {
		tabWrestler.click();
	}

	public void clickOnCloseNewWrestlerTab() {
		String lastName = (String) Thucydides.getCurrentSession().get(
				"lastName");
		String closeTabElement = String.format(CLOSE_WRESTLER_TAB, lastName);
		findBy(closeTabElement).then().click();
	}

	public void enterSearchCriteria(String searchCriteria) {
		inputSearch.type(searchCriteria);
	}

	public void clickButtonSearch() {
		buttonSearch.click();
	}

	public String getTextFromCell(int tdNumber) {
		String cellString = String.format(DATA_IN_CELL_FOR_FIRST_RECORD,
				tdNumber);
		return findBy(cellString).then().getText();
	}

}
