package aptrakov.insaf;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class SelenideSearch {
    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://dgithub.com";
        pageLoadStrategy = "eager";
        holdBrowserOpen = false;
        timeout = 5000; // default 4000


    }

    @Test
    void softAssertionsJUnit5(){
        //открываем selenide
        open("https://github.com/selenide/selenide");
        //клик по wiki
        $("#wiki-tab").click();
        //проверка что есть SoftAssertions
        $("#wiki-body").shouldHave(text("Soft assertions"));
        //перейти в SoftAssertions
        $("#wiki-body").$(byText("Soft assertions")).click();
        //проверка что есть пример кода
        $("#wiki-content").shouldHave(text("Using JUnit5 extend test class:"));
        $("#wiki-content").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "@Test\n" +
                "void test() {\n" +
                "Configuration.assertionMode = SOFT;\n" +
                "open(\"page.html\");\n" +
                "$(\"#first\").should(visible).click();\n" +
                "$(\"#second\").should(visible).click();\n" +
                "}\n" +
                "}"));



    }
}
