package aptrakov.insaf.Annotations;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WebTestCsv extends TestBase {

    @CsvSource(value = {
            "TEST | TEST",
            "MY LOVE | my love"
    }, delimiter = '|')
    @ParameterizedTest(name = "Test2.searchForMusic {0} ")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void searchForMusicAtZaycevServiceCsvFileTest(String searchMusic, String name) {
        $(".magnifying-glass").click();
        $(".shz-menu-action-passthrough").setValue(searchMusic).pressEnter();

        $(".title-wrapper").shouldHave(text(name));
    }
}
