package ru.qa.irakulikova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenidePractice {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldFindJUnit5test() {

        String jUnit5ExpectationTest = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                         
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }""";

        open("https://github.com/selenide/selenide"); //Открываю страницу Selenide в Github;
        $("#wiki-tab").click(); // Перехожу в раздел Wiki проекта;
        $(".js-wiki-more-pages-link").click(); // Раскрываю список Pages;
        $(byLinkText("SoftAssertions")).click(); // Открываю страницу SoftAssertions;
        // Проверяю, что внутри есть пример кода для JUnit5:
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
        String realityJUnit5Test = ($("#user-content-3-using-junit5-extend-test-class").parent().sibling(0).getText());
        Assertions.assertEquals(jUnit5ExpectationTest, realityJUnit5Test);
    }
}