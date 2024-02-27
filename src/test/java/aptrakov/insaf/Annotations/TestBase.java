package aptrakov.insaf.Annotations;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;//
        Configuration.timeout = 5000; // default 4000
    }

    @BeforeEach
    void beforeEach() {
        open("https://www.shazam.com/ru/charts/top-200/russia/");
    }

    @AfterEach()
    void afterEach() {
        Selenide.closeWebDriver();
    }
}