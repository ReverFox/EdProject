package ru.fokinde.DuckDuckGo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Main page")
@Story("Side menu")
public class DuckDuckGoMainPageTest {

    @BeforeClass
    public void init() {
        Configuration.headless = false;
        Configuration.browser = "chrome";

        if ((System.getenv("is_cicd") != null) && (System.getenv("is_cicd").equals("true"))) {
            Configuration.remote = "http://192.168.56.1:4444/";
        }
    }

    @Test(description = "Проверка наличия сcылки \"About\"")
    @TmsLink("TMS-001")
    public void aboutRefVisibleTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.ABOUT_REF.shouldBe(exist);
        assertThat(mainPage.ABOUT_REF.getAttribute("href"))
                .as("Ссылка: https://duckduckgo.com/about")
                .isEqualTo("https://duckduckgo.com/about");
    }

    @Test(description = "Проверка наличия ссылки AI \"About\"")
    @TmsLink("TMS-002")
    public void aiButtonVisibleTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.AI_CHAT_REF.shouldBe(exist);
        assertThat(mainPage.AI_CHAT_REF.getAttribute("href"))
                .as("Ссылка: https://duck.ai/")
                .isEqualTo("https://duck.ai/");
    }

    @Test(description = "Боковое меню. Открытие меню")
    @TmsLink("TMS-003")
    public void openSideMenuTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.openSideMenu();
        mainPage.SIDE_MENU.shouldBe(exist);
    }

    @Test(description = "Боковое меню. Закрытие меню", dependsOnMethods = {"openSideMenuTest"})
    @TmsLink("TMS-004")
    public void closeSideMenuTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.openSideMenu();
        mainPage.SIDE_MENU.shouldBe(exist);
        mainPage.closeSideMenu();
        mainPage.SIDE_MENU.shouldNotBe(exist);
    }

    @Test(description = "Проверка наличия поисковой строки")
    @TmsLink("TMS-005")
    public void searchBoxExistTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.SEARCH_STRING_INPUT.shouldBe(exist);
        mainPage.SEARCH_STRING_BUTTON.shouldBe(exist);
    }

    @Test(description = "Проверка работы поисковой строки", dependsOnMethods = {"searchBoxExistTest"})
    @TmsLink("TMS-006")
    public void searchBoxSearchTest() {
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        mainPage.search("Hello, World!");
        sleep(2000);
        assertThat(WebDriverRunner.url())
                .as("Search result: https://duckduckgo.com/?t=h_&q=Hello%2C+World!&ia=web")
                .isEqualTo("https://duckduckgo.com/?t=h_&q=Hello%2C+World!&ia=web");
    }

    @Test(description = "Проверка колличества результатов поиска")
    @TmsLink("TMS-007")
    public void searchResultQuantityTest() {
        final int RESULT_QUANTITY = 10;
        DesktopMainPage mainPage = open("https://duckduckgo.com", DesktopMainPage.class);
        DesktopSearchResultPage searchResultPage = mainPage.search("Hello, World!");
        sleep(2000);
        assertThat(searchResultPage.SEARCH_RESULTS.size())
                .as("Количество найденых результатов: %d".formatted(RESULT_QUANTITY))
                .isEqualTo(RESULT_QUANTITY);

    }
}
