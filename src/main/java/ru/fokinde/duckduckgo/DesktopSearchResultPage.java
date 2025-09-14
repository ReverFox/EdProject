package ru.fokinde.duckduckgo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DesktopSearchResultPage {
    public final SelenideElement SEARCH_STRING_INPUT = $("[id=\"search_form_input\"]");
    public final SelenideElement SEARCH_STRING_RESET_BUTTON = $("button[type=\"reset\"]");
    public final SelenideElement SEARCH_STRING_SEARCH_BUTTON = $("button[type=\"search\"]");
    public final ElementsCollection SEARCH_RESULTS = $$(".react-results--main li[data-layout=\"organic\"]");
}
