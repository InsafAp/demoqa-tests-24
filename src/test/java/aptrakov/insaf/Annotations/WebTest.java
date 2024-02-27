package aptrakov.insaf.Annotations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class WebTest extends TestBase {


    @ValueSource(strings = {
            "TEST",
            "MY LOVE"
    })
    @ParameterizedTest(name = "Поиск музыки {0} в сервисе зайцев.нет")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void searchForVideoGameAtSteamService(String searchMusic) {
        $(".magnifying-glass").click();
        $(".shz-menu-action-passthrough").setValue(searchMusic).pressEnter();

        $(".title-wrapper").shouldHave(text(searchMusic));
    }


}