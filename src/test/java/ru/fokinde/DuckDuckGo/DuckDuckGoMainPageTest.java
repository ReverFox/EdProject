package ru.fokinde.DuckDuckGo;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.fokinde.DuckDuckGo.DesktopMainPage.*;

@Epic("Main page")
@Story("Side menu")
public class DuckDuckGoMainPageTest {

    @BeforeClass
    public void init() {
        Configuration.headless = false;
        Configuration.browser = "chrome";
        //Configuration.remote = "http://192.168.56.1:4444/";

        open("https://duckduckgo.com");
    }

    @Test(description = "Проверка наличия поисковой строки")
    @TmsLink("TMS-001")
    public void searchBoxExistTest() {
        SEARCH_STRING_INPUT.shouldBe(exist);
        SEARCH_STRING_BUTTON.shouldBe(exist);
    }

    @Test(description = "Проверка наличия сcылки \"About\"")
    @TmsLink("TMS-002")
    public void aboutRefVisibleTest() {
        ABOUT_REF.shouldBe(exist);
        assertThat(ABOUT_REF.getAttribute("href"))
                .as("Ссылка: https://duckduckgo.com/about")
                .isEqualTo("https://duckduckgo.com/about");
    }

    @Test(description = "Проверка наличия ссылки AI \"About\"")
    @TmsLink("TMS-003")
    public void aiButtonVisibleTest() {
        AI_CHAT_REF.shouldBe(exist);
        assertThat(AI_CHAT_REF.getAttribute("href"))
                .as("Ссылка: https://duck.ai/")
                .isEqualTo("https://duck.ai/");
    }

    @Test(description = "Боковое меню. Открытие меню")
    @TmsLink("TMS-004")
    public void openSideMenuTest() {
        DesktopMainPage mainPage = new DesktopMainPage();
        mainPage.openSideMenu();
        SIDE_MENU.shouldBe(exist);
    }

    @Test(description = "Боковое меню. Закрытие меню", dependsOnMethods = {"openSideMenuTest"})
    @TmsLink("TMS-005")
    public void closeSideMenuTest() {
        DesktopMainPage mainPage = new DesktopMainPage();
        mainPage.closeSideMenu();
        SIDE_MENU.shouldNotBe(exist);
    }
}
