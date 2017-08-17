import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by goekh on 30.07.2017.
 */
public class Lufthansa extends BasePage {


    @FindBy(how= How.ID,using = "flightmanager-tab-2")
    private WebElement flightAndHotel;

    @FindBy(how = How.ID,using = "flightmanagerHolidayFormdeparture")
    private WebElement flightFrom;

    @FindBy(how=How.ID,using = "flightmanagerHolidayFormDestination")
    private WebElement flightDestination;

    @FindBy(how=How.ID,using="flightmanagerHolidayFormOutboundDateDisplay")
    private WebElement flightDateOutboundFlight;

    @FindBy(how=How.XPATH,using="(//button[@type='button'])[43]")
    private WebElement flightOut;

    @FindBy(how=How.XPATH,using="//tr[2]/td[3]/button")
    private WebElement flightIn;

    @FindBy(how=How.ID,using = "flightmanagerHolidayFormduration")
    private WebElement travelTime;

    @FindBy(how=How.XPATH,using="//div[5]/div/div/div/button")
    private WebElement indexMinus;

    @FindBy(how = How.XPATH,using = "//div[3]/button")
    private WebElement searchTravelBtn;

    /*********************LOGIN PART**************************************/

    @FindBy(how=How.LINK_TEXT,using= "Login")
    private WebElement clickToLogin;

    @FindBy(how=How.ID,using = "lid_input_23")
    private WebElement userName;

    @FindBy(how=How.ID,using = "lid_input_24")
    private WebElement password;

    @FindBy(how=How.XPATH,using = "//div[5]/div/button")
    private WebElement submitBtn;


    public Lufthansa(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    @Override
    public void inputText(String a, String b) {
        flightDateOutboundFlight.click();
        flightIn.click();
        flightIn.click();
    }

    @Override
    public void clickOnSubmit() {searchTravelBtn.click();}

    @Override
    public void clickToZipFile() {indexMinus.click();}

    @Override
    public void clickToDownloadButton() {}

    @Override
    public void searchMyFile(String myFile) {
        flightDestination.sendKeys(myFile);

    }

    @Override
    public void inputUserDataLogin(String a, String b) {

        clickToLogin.click();
        userName.sendKeys(a);
        password.sendKeys(b);
        submitBtn.click();
    }

    public void chooseFlightAndHotel(String x){
            flightAndHotel.click();
            flightFrom.click();
            flightFrom.sendKeys(x);
    }

    public void chooseTravelTime(String y){
            travelTime.click();
            travelTime.sendKeys(y);
    }


}
