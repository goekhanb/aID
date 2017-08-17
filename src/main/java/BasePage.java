import org.openqa.selenium.WebDriver;


/**
 * Created by goekh on 28.07.2017.
 */
abstract public class BasePage {

    protected WebDriver webDriver=null;

    public BasePage(){}

    public abstract void inputText(String a, String b);
    public abstract void clickOnSubmit();
    public abstract void clickToZipFile();
    public abstract void clickToDownloadButton();
    public abstract void searchMyFile(String myFile);
    public abstract void inputUserDataLogin(String a,String b);
}
