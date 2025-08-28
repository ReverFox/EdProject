package ru.fokinde.DuckDuckGo;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.fokinde.DuckDuckGo.MainPage.ABOUT_REF;
import static ru.fokinde.DuckDuckGo.MainPage.SEARCH_BOX;

public class DuckDuckGoTest {

    @BeforeClass
    public void init() {
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.remote = "http://192.168.56.1:4444/";
    }

    @Test(description = "Поисковая строка отображается на главной странице")
    @TmsLink("TMS-001")
    public void mainPageSearchBoxVisibleTest() {
        open("https://duckduckgo.com");
        $(By.cssSelector(SEARCH_BOX)).should(exist);
        //$(By.cssSelector(ABOUT_REF)).should(exist);
        //$(By.cssSelector(SEARCH_BUTTON)).should(exist);
        //$(By.cssSelector(AI_CHAT_BUTTON)).should(exist);
        //$(By.cssSelector(SIDE_BAR_BUTTON)).should(exist);
    }

    @Test(description = "Проверка отображения поиска на главной странице")
    @TmsLink("TMS-001")
    public void mainPageAboutRefVisibleTest() {
        open("https://duckduckgo.com");
        $(By.cssSelector(ABOUT_REF)).should(exist);
    }
}
