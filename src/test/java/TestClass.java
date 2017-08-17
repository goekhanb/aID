import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by goekh on 28.07.2017.
 */
public class TestClass{

    WebDriver driver = null;


    @Before
    public void SetUp(){
        driver = new ChromeDriver();
        driver.get("https://www.dropbox.com/de");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

    }

    @Test
    public void verifyInputUserTest(){
        WebPage webPage = new WebPage(driver);
        webPage.inputText("goekhan.bastan@hotmail.de","medtronic21");
        webPage.clickOnSubmit();
        webPage.searchMyFile("Vorlage TeX.zip");
        webPage.clickToDownloadButton();
    }


    @After
    public void TearDown(){
        if(driver==null){driver.quit();}
    }
}
