package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    //Инициализируем конструктор классов к которому будут обращаться все наши тесты
    public MainPageObject(AppiumDriver driver)
     {
        this.driver = driver;
    }

    //Метод для проверки отсутвия элемента на странице
    public boolean waitForElementNotPresent (String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //Метод для проверки что элемент находится на странице
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 15);
    }

    public WebElement waitForElementForClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, 15);
        element.click();
        return element;
    }

    public WebElement waitForElementSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, 15);
        element.sendKeys(value);
        return element;
    }
    // метод по очистке поля вводя
    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, 15);
        element.clear();
        return element;

    }
    //Пишем метод для свайпа вверх
    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver); //тут надо выбирать TouchAction из Appium, тк может быть еще из Selenium
        //Определим размер экран девайс. Используем Demension из пакета Selenium (driver.manage().window().getSize() - тут получаем параметры экарана
        Dimension size = driver.manage().window().getSize();
        //Далее задаем переменные с координатами по оси х и у
        int x = size.width / 2; // ставим координату в середине экрана
        int start_y = (int) (size.height * 0.8); // точка по у на 80% внизу экрана. Все в скобках для перевода из формата дабл в инт
        int end_y = (int) (size.height * 0.2); // точка по у на 20% сверху экрана

        //Для свайпа нам нужно нажать на экран, подождать и провести пальцем вверх. Команда perform отсылает всю нашу последовательсноть действий на выполнение
        action.press(PointOption.point(x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).moveTo(PointOption.point(x, end_y)).release().perform();
    }


    //Пишем метод который будет свайпат страницу быстро, так тут сразу указываем время свайпа с прошлого меитода, проверять что после свайпа на странице нет нужного элемента и свайпать дальше, пока не найдем нужный элемент
    public void swipeUpQuick()
    {
        //Используем прошлый метод для свайпа страницы
        swipeUp(200);
    }

    //Теперь пишем метод который будет свайпат страницу, проверять что после свайпа на странице нет нужного элемента и свайпать дальше, пока не найдем нужный элемент
    public void swipeUpToFindeElement (String locator, String error_message, int max_swipe)
    {
        By by = this.getLocatorByString(locator);
  /*    Этот код не нужен, так как мы его записали ниже в цикле while
        //Пишем функцию которая ищет все элементы на странице
        driver.findElements(by);
        //Пишем функцию, которая проверяет сколько элементов нашли на странице c помощь findeElement
        driver.findElements(by).size();
        */
        int already_swiped = 0; //в эту переменную будет записывать реальное колличество свайпов
         while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipe) {
                waitForElementPresent(locator, "Cannnot find element by swipe" + "\n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped; //++  - плюсуем каждый свайп в переменную
        }
    }

    //Метод для свайпа влево по определенному элемунту
    public void swipeElementToLeft (String locator, String error_message)
    {
        WebElement element =  waitForElementPresent(
                locator,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        //Находим правую границу нашего элемента: к левой плюсуем ширину правой границы
        int right_x = left_x + element.getSize().getWidth();
        //ищем верхнуюю координату
        int up_y = element.getLocation().getY();
        int down_y = up_y + element.getSize().getHeight();
        int middle_y = (up_y + down_y) / 2;

        //Теперь инициируем драйвер
        TouchAction action = new TouchAction(driver); //тут надо выбирать TouchAction из Appium, тк может быть еще из Selenium
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))  //Если свайп очень быстро проходит, то можно увеличить вреимя
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }
    //Метод для подсчета колличества элементов
    public int getAmountElements (String locator) {

        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        //List - создает некий список и записывает его в список elements
        //driver.findElements(by) - функция findElements ищет элементы
        return elements.size();
        //Возвращаем колличество найденных элементов записанные в elements
    }

    //Метод для проверки что нинашлось ни одной статьи
    public void assertElementNotPresent(String locator, String error_message){
        //Сначала получим колличество элементов
        int amount_of_elements = getAmountElements(locator);
        //Далее провреяем количество найденных элементов и если их больше, то эксепшн
        if (amount_of_elements > 0) {
            //Напишем эррор для эксепшена
            //тут by.toString() - toString() -этот метод преобразит элемент by в строковое значение
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    //
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    //метод для определения типа локаторов
    private By getLocatorByString(String locator_with_type)
    {

        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);


        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get typ of locator. Locator: " + locator_with_type);
        }

    }


}
