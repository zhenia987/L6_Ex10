package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

//В этот класс вынесем инициализацию наших тестов, а так же остановку и очистку
//Так же добавим после названия класса extends TestCase - это позволит нам использовать TestCase из junit.framework.TestCase
public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String  PLATFORM_ANDROID = "android";


    //Изменим вместо private на protected, так как этот аппиум драйвер будет использоваться в других классах
    protected AppiumDriver driver;

    protected Platform Platform;


    //Убрали аннотацию  @Before

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        this.Platform = new Platform();
        driver = this.Platform.getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();

    }

    //Метод для поворота
    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int second)
    {
        driver.runAppInBackground(Duration.ofSeconds(second));
    }


}
