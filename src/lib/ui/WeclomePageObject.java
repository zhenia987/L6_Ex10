package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WeclomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeButton[@name='Узнать подробнее о Википедии']",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Новые способы изучения\"]",
    STEP_AND_OR_EDIT_PREFERED_LANG_LINK = "xpath://XCUIElementTypeStaticText[@name='Добавить или изменить предпочтительные языки']",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Узнать подробнее о сборе данных']",
    NEXT_LINK = "xpath://XCUIElementTypeStaticText[@name='Далее'']",
    GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Начать']";




     public WeclomePageObject(AppiumDriver driver)
     {
         super(driver);
     }

     //Метод для ожидания ссылки Public More
    public void waitForLerningMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "15", 15);
    }

    //Метод для ожидания заголовка страницы Новые способы изучения
    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Can note find text Новые способы изучения ",15);
    }

    //Метод для ожидания ссылки "Добавить или изменить предпочтительные языки2
    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_AND_OR_EDIT_PREFERED_LANG_LINK, "Cannot find link Добавить или изменить предпочтительные языки", 15);
    }

    //Метод для ожидания ссылки "Узнать подробнее о сборе данны"
    public void waitForLearnMoreAboutDateCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find link Узнать подробнее о сборе данны", 15);

    };

    //Метод по клику по кнопке Next
    public void clickNextButton()
    {
        this.waitForElementForClick(NEXT_LINK, "Cannot find and click for Next Button", 15);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementForClick(GET_STARTED_BUTTON, "Can not find button Начать", 15);
    }



}
