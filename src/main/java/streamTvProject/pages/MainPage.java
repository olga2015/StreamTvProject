package streamTvProject.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://streamtv.net.ua/base/")
public class MainPage extends PageObject {

	private static final int TD_NUMBER_WITH_NAME = 2;

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

	@FindBy(xpath = "//button[@ng-click='delete()']")
	private WebElementFacade buttonDeleteSportsmen;

	@FindBy(xpath = "//div[@class='col-lg-12']//input")
	private WebElementFacade inputSearch;

	@FindBy(xpath = "//div[@class='col-lg-12']//button")
	private WebElementFacade buttonSearch;

	@FindBy(xpath = "//div[contains(text(), 'Wrestlers')]")
	private WebElementFacade tabWrestler;

	@FindBy(xpath = "//div[@class='modal-footer ng-scope']/button[@class='btn btn-success']")
	private WebElementFacade buttonAcceptModalDialog;

	@FindBy(xpath = "//button[@ng-click='resetFilters()']/following-sibling::select[1]")
	private WebElementFacade selectRegionFromFilter;

	@FindBy(xpath = "//button[@ng-click='resetFilters()']/following-sibling::select[2]")
	private WebElementFacade selectFstFromFilter;

	@FindBy(xpath = "//input[@uploader='photoUploader']")
	private WebElementFacade buttonUploadPhoto;

	@FindBy(xpath = "//div[@uploader='photoUploader']/img")
	private WebElementFacade img;

	@FindBy(xpath = "//input[@uploader='attachUploader']")
	private WebElementFacade buttonUploadAttach;

	@FindBy(xpath = "//tr[@ng-repeat='attach in wr.attaches']//a")
	private WebElementFacade tdWithAttach;

	@FindBy(xpath = "//ico[@ng-click='deleteAttach($index)']/span")
	private WebElementFacade buttonDeleteAttach;

	private static final String SPINNER_LOADER = "//div[contains(@class,'spinner-loader')]";
	private static final String DATA_IN_CELL_FOR_FIRST_RECORD = "//tr[@ng-click='openWrestler(wrestler)']/td[%d]";
	private static final String CLOSE_WRESTLER_TAB = "//div[contains(text(), '%s')]//ico[@icon='glyphicon-remove']";
	private static final String TEXT_ON_SPORTSMAN_TAB = "Wrestler info";
	private static final String NAMES_IN_CELLS = "//div[@class='col-sm-12 mt80']//tr/td[2]";
	private static final String REGIONS_IN_CELLS = "//div[@class='col-sm-12 mt80']//tr/td[3]";
	private static final String FST_IN_CELLS = "//div[@class='col-sm-12 mt80']//tr/td[4]";
	private static final String IMG_CONTAINS_DATA = "//div[@uploader='photoUploader']/img[contains(@src, 'data:image/png')]";
	private static final String TR_WITH_ATTACH = "//tr[@ng-repeat='attach in wr.attaches']";

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
		highlightElement(buttonSaveNewSportsmen);
		buttonSaveNewSportsmen.click();
		waitForAbsenceOf(SPINNER_LOADER);
	}

	private void highlightElement(WebElementFacade element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].style.border='4px solid red'", element);
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
		waitForAbsenceOf(SPINNER_LOADER);
	}

	public String getTextFromCell(int tdNumber) {
		String cellString = String.format(DATA_IN_CELL_FOR_FIRST_RECORD,
				tdNumber);
		return findBy(cellString).then().getText();
	}

	public void clickEditFirstRecord() {
		String cellString = String.format(DATA_IN_CELL_FOR_FIRST_RECORD,
				TD_NUMBER_WITH_NAME);
		findBy(cellString).then().click();
		waitForAnyTextToAppear(TEXT_ON_SPORTSMAN_TAB);
	}

	public void clickOnDeleteSportsmanProfile() {
		highlightElement(buttonDeleteSportsmen);
		buttonDeleteSportsmen.click();
		buttonAcceptModalDialog.click();
	}

	private List<String> getAllDataFromColumnOnPage(String element) {
		List<WebElementFacade> cellsWebElements = findAll(element);
		List<String> dataInCells = new LinkedList<String>();
		for (WebElementFacade dataInOneCell : cellsWebElements) {
			highlightElement(dataInOneCell);
			dataInCells.add(dataInOneCell.getText());
		}
		return dataInCells;
	}

	public List<String> getAllNamesOnPage() {
		return getAllDataFromColumnOnPage(NAMES_IN_CELLS);
	}

	public List<String> getAllregionsOnPage() {
		return getAllDataFromColumnOnPage(REGIONS_IN_CELLS);
	}

	public List<String> getAllFstOnPage() {
		return getAllDataFromColumnOnPage(FST_IN_CELLS);
	}

	public void selectRegionFromFilter(String region) {
		selectRegionFromFilter.selectByVisibleText(region);
	}

	public void selectFstFromFilter(String fst) {
		selectFstFromFilter.selectByVisibleText(fst);
	}

	public void uploadPhoto(String absolutePath) {
		upload(absolutePath).to(buttonUploadPhoto);
		waitFor(IMG_CONTAINS_DATA);
	}

	public boolean isImgDisplayed() {
		return img.isDisplayed();
	}

	public void uploadAttach(String absolutePath) {
		upload(absolutePath).to(buttonUploadAttach);
		waitFor(TR_WITH_ATTACH);
	}

	public String getAttachName() {
		return tdWithAttach.getText();
	}

	public void clickOnDeletAttachment() {
		buttonDeleteAttach.click();
	}
}
