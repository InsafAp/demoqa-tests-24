package aptrakov.insaf.Annotations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class WebTestValue extends TestBase {





//Тест1
    @Test
    @ParameterizedTest(name = "Тест2.searchForMusic {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @ValueSource(strings = {
            "TEST",
            "MY LOVE"
    })

    void searchForMusicAtZaycevServiceValueSource(String searchMusic) {
        $(".magnifying-glass").click();
        $(".shz-menu-action-passthrough").setValue(searchMusic).pressEnter();

        $(".title-wrapper").shouldHave(text(searchMusic));
    }








}