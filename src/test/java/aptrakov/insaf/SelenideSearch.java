package aptrakov.insaf;



import com.codeborne.selenide.SelenideElement;
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
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        //перейти в SoftAssertions
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        //проверка что есть пример кода
        $("#wiki-content").shouldHave(text("Using JUnit5 extend test class:"));
       /* $("#wiki-content").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "@Test\n" +
                "void test() {\n" +
                "Configuration.assertionMode = SOFT;\n" +
                "open(\"page.html\");\n" +
                "$(\"#first\").should(visible).click();\n" +
                "$(\"#second\").should(visible).click();\n" +
                "}\n" +
                "}"));*/


        SelenideElement selenideElement = $("#wiki-content").shouldHave(text(String.join("\n",
                "@ExtendWith({SoftAssertsExtension.class}))",
                "class Tests {", "@Test", "void test() {",
                "Configuration.assertionMode = SOFT;",
                "open(\"page.html\");",
                "$(\"#first\").should(visible).click();",
                "$(\"#second\").should(visible).click();",
                "}",
                "}")));


    }
}
