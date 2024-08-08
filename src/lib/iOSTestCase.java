package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

//В этот класс вынесем инициализацию наших тестов, а так же остановку и очистку
//Так же добавим после названия класса extends TestCase - это позволит нам использовать TestCase из junit.framework.TestCase
public class iOSTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String  PLATFORM_ANDROID = "android";

    //Изменим вместо private на protected, так как этот аппиум драйвер будет использоваться в других классах
    protected AppiumDriver driver;

    //Вынесем в отдельную перемменую URL, который используется ниже в driver
    private static String AppiumURL = "http://127.0.0.1:4723/";


    //Убрали аннотацию  @Before

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }

    //Убрали аннотацию   @After
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


    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("appium:deviceName","AndroidTestDevice");
            capabilities.setCapability("appium:platformVersion","8");
            capabilities.setCapability("appium:automationName","UiAutomator2");
            capabilities.setCapability("appium:appPackage","org.wikipedia");
            capabilities.setCapability("appium:appActivity",".main.MainActivity");
            capabilities.setCapability("appium:app","/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/org.wikipedia.apk");


        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 15 Plus");
            capabilities.setCapability("appium:platformVersion", "17.4");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:app", "/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/Wikipedia.app");

        } else {
        throw new Exception( "Cannot get run platform from env variable. Platform value " + platform);

    }
        return capabilities;
    }

}
