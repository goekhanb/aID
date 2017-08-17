import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by goekh on 28.07.2017.
 */
public class WebPage extends BasePage {


    @FindBy(how=How.XPATH,using = "//div[2]/input")
    private WebElement inputUserName;
    public WebElement getInputUserName(){return inputUserName;}

    @FindBy(how = How.XPATH, using = "//div[2]/div[2]/input")
    private WebElement inputPassword;
    public WebElement getInputPassword(){return inputPassword;}

    @FindBy(how = How.CSS,using = "button.login-button.button-primary")
    private WebElement submitLoginBtn;
    public WebElement getSubmitLoginBtn(){return submitLoginBtn;}

    @FindBy(how=How.LINK_TEXT,using = "Anmelden")
    private WebElement clickToLogin;

    @FindBy(how=How.LINK_TEXT,using = "Vorlage TeX.zip")
    private WebElement clickToFile;

    @FindBy(how=How.CLASS_NAME,using = "filename")
    private WebElement clickToCheckBox;

    @FindBy(how=How.CLASS_NAME,using = "search-bar__text-input")
    private WebElement searchMyFile;

    @FindBy(how = How.XPATH,using = "//form/button")
    private WebElement searchMyFileBtn;

    @FindBy(how=How.LINK_TEXT,using = "Vorlage TeX.zip")
    private WebElement clickToFileDownload;

    @FindBy(how=How.XPATH,using = "//div[@id='browse-react-file-viewer-container']" +
            "/div/div/div[2]/div/div/div/div/div/div[3]/button")
    private WebElement clickToFileDownload__;


    public WebPage(WebDriver driver){
        PageFactory.initElements(driver,this);}

    @Override
    public void inputText(String a, String b) {
        clickToLogin.click();
        inputUserName.sendKeys(a);
        inputPassword.sendKeys(b);

    }
    @Override
    public void clickOnSubmit(){ submitLoginBtn.click();}

    @Override
    public void clickToZipFile() {
        clickToCheckBox.click();
        clickToFile.click();
   }

    @Override
    public void clickToDownloadButton(){
        clickToFileDownload.click();
        clickToFileDownload__.click();
    }

    @Override
    public void inputUserDataLogin(String a, String b) {

    }

    @Override
    public void searchMyFile(String myFile){
        searchMyFile.sendKeys(myFile);
        searchMyFileBtn.click();
    }

}
