package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String  PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    
    // Теперь напишем метод который будет отвечать за выбор нужного драйвера
    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detecte type of the Friver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return  isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return  isPlatform(PLATFORM_IOS);
    }


     
    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:deviceName","AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion","8");
        capabilities.setCapability("appium:automationName","UiAutomator2");
        capabilities.setCapability("appium:appPackage","org.wikipedia");
        capabilities.setCapability("appium:appActivity",".main.MainActivity");
        capabilities.setCapability("appium:app","/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 15 Plus");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/Wikipedia.app");
        return capabilities;
    }

    //Метод который будет сравнивать нашу платформу с той переменной которая приходит ему на вход
    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    //Метод который отвечает за получение переменной окружения
    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }


}
