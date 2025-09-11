package ru.fokinde.DuckDuckGo;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DesktopMainPage {
    public static final String DESKTOP_WRAPPER_CSS = "[class*=\"header_headerWrapDesktop\"]";

    public final SelenideElement ABOUT_REF = $(DESKTOP_WRAPPER_CSS + " [class*=\"header_logoText\"]");
    public final SelenideElement AI_CHAT_REF = $(DESKTOP_WRAPPER_CSS + " [data-testid=\"aichat-button\"]");
    public final SelenideElement SEARCH_STRING_INPUT = $("[class*=\"searchbox_searchbox\"] input");
    public final SelenideElement SEARCH_STRING_BUTTON = $("[class*=\"searchbox_searchButton\"]");
    public final SelenideElement SIDE_MENU_OPEN_BUTTON = $(DESKTOP_WRAPPER_CSS + " button[data-testid=\"sidemenu-button\"");
    public final SelenideElement SIDE_MENU_CLOSE_BUTTON = $(DESKTOP_WRAPPER_CSS + " button[class*=\"sideMenu_closeButton\"]");
    public final SelenideElement SIDE_MENU = $(DESKTOP_WRAPPER_CSS + " [data-testid=\"sidemenu\"]");
    
    
    public void openSideMenu() {
        SIDE_MENU_OPEN_BUTTON
                .shouldBe(visible)
                .click();
    }
    
    public void closeSideMenu() {
        SIDE_MENU_CLOSE_BUTTON
                .shouldBe(visible)
                .click();
    }

    public DesktopSearchResultPage search(String search_query) {
        SEARCH_STRING_INPUT.type(search_query);
        SEARCH_STRING_BUTTON.click();
        return page(DesktopSearchResultPage.class);
    }
}
