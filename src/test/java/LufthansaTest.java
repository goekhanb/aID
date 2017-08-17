import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by goekh on 30.07.2017.
 */
public class LufthansaTest {
    WebDriver webDriver = null;

    @Before
    public void SetUp(){
        webDriver= new ChromeDriver();
        webDriver.get("http://www.lufthansa.com/de/de/Homepage");
        webDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
    }

    @Test
    public void verifyLufthansaTest(){
        Lufthansa lufthansa = new Lufthansa(webDriver);
        lufthansa.chooseFlightAndHotel("Frankfurt(FRA)");
        lufthansa.searchMyFile("Istanbul, Alle Flughäfen");
        lufthansa.inputText("Di, 01.08.2017","Mi, 09.08.2017");
        lufthansa.chooseTravelTime("5 - 8 Tage");
        lufthansa.clickToZipFile();
        lufthansa.clickOnSubmit();

    }

    @Test
    public void verifyLufthansaLogin(){
        Lufthansa lufthansa = new Lufthansa(webDriver);
        lufthansa.inputUserDataLogin("admin","admin");
    }

    @After
    public void TearDown(){
        if(webDriver==null){webDriver.quit();}
    }
}
