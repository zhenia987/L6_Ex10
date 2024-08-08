package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test  ///Тест на проверку, что больше нет какого-то элемента на странице/экране
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForBackButtonToAppear();
        SearchPageObject.clickBackSearch();
        SearchPageObject.waitForBackButtonToDisappear();
    }


    //L4 Урок 5 Assert Bassic
    @Test //Проверяем что колличество элементов поиска больше чем 0, и если это так, то тест успешно завершится
    public void testAmountOfNotEmptySearch() {


        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipButton();
        SearchPageObject.initSearchInput();
        String serch_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(serch_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        //Проверяем, что колличество найденных элементов больше нуля через assertTrue
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0

        );
    }

    //L4 Урок 6  Assert: assertion error
    @Test //Тест для проверки того что результат поиска пустой
    public void testAmountEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipButton();
        SearchPageObject.initSearchInput();
        String serch_line = "JHVkhs";
        SearchPageObject.typeSearchLine(serch_line);
        SearchPageObject.waitForEmptyResultsLables();
        SearchPageObject.assertThereIsNotResultOfSearch();
    }

}
