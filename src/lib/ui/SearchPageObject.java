package lib.ui;
//В этом классе будут использоваться методы для поиска

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

//Укажем что этот метод наследуется от MainPageObject
public class SearchPageObject extends MainPageObject{

    //Запишем константы которые используются в поиске
    private static final String
            SEARCH_SKIP = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_BACK_BUTTON = "xpath://*[@content-desc='Navigate up']",
            SEARCH_CLOSE_BUTTON = "id:org.wikipedia:id/search_close_bt",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";


    //Иницилизируем аппиум драйвер из mainPageObject
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver); //Таким образов мы берем драйвер из MainPageObject
    }



    /*Это так называемый метод templated */
    //Напишем еще метод для SUBSTRING
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*Это так называемый метод templated */


    //Напишем метод, который будет тапать по Skip
    public void initSkipButton()
    {
        this.waitForElementForClick(SEARCH_SKIP,"Can not find Skip button", 5);
    }

    //Напишем метод, который будет инициализировать процесс поиска
    public void initSearchInput()
    {
        this.waitForElementForClick(SEARCH_INIT_ELEMENT, "Error find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Error find search input after clicking search init element");
    }

    //Напишем метод, который будет вводить какое то значение в строку
    public void typeSearchLine(String search_line)
    {
        this.waitForElementSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search line", 5);
    }

    //Метод для поиска результата
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Can not find result with substring " + substring);
    }

    //Метод для ожидания кнопки Стрелка-назад
    public void waitForBackButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_BACK_BUTTON, "Cannot find  Back button", 5);
    }
    //Тут же пишем метод, который будет ожидание отсутствия этой кнопки "Стрелка-назад" по окончании теста
    public void waitForBackButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_BACK_BUTTON, "Back button is still present", 5);
    }
    //Метод для клика по кнопке Стрелка-назад
    public void clickBackSearch()
    {
        this.waitForElementForClick(SEARCH_BACK_BUTTON, "Cannot find and click Back button", 5);
    }

    //Метод для клика по статье, которую находит в поиске
    public void clickByArticleWithSubstring (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementForClick(search_result_xpath, "Can not find and click result with substring " + substring, 10);
    }


    //Метод для подсчета колличества статей Урок 5-07
    public  int getAmountOfFoundArticle()
    {
       this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Can not find enything by the request ",
                15
        );
        //Получаем колличество найденных элементов через метод getAmountElements
       return this.getAmountElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLables()
    {
     this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot finde empty result element", 15);
    }

    public  void assertThereIsNotResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

}
