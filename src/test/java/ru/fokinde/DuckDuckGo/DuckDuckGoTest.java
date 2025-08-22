package ru.fokinde.DuckDuckGo;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static ru.fokinde.DuckDuckGo.MainPage.*;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DuckDuckGoTest {

    @Test(description = "Проверка отображения поиска на главной странице")
    @TmsLink("TMS-001")
    public void mainPageTest() {
        Configuration.headless = true;
        open("https://duckduckgo.com");
        $(By.cssSelector(SEARCH_BOX)).should(exist);
        $(By.cssSelector(ABOUT_REF)).should(exist);
        $(By.cssSelector(SEARCH_BUTTON)).should(exist);
        $(By.cssSelector(AI_CHAT_BUTTON)).should(exist);
        $(By.cssSelector(SIDE_BAR_BUTTON)).should(exist);
    }
}
