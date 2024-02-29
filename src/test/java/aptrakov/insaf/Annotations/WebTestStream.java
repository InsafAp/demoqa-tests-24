package aptrakov.insaf.Annotations;
import aptrakov.insaf.Annotations.TestBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
public class WebTestStream extends TestBase {
    static Stream<Arguments> dataForMusicAtZaycevServiceStreamTest() {
        return Stream.of(
                Arguments.of("TEST", "TEST"),
                Arguments.of("MY LOVE", "MY LOVE")
        );
    }
    @MethodSource ("dataForMusicAtZaycevServiceStreamTest")
    @ParameterizedTest(name = "Test3.searchForMusic {0} ")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void searchForMusicAtZaycevServiceStreamTest(String searchMusic, String name) {
        $(".magnifying-glass").click();
        $(".shz-menu-action-passthrough").setValue(searchMusic).pressEnter();

        $(".title-wrapper").shouldHave(text(name));
    }
}
