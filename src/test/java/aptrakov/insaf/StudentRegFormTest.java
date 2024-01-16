package aptrakov.insaf;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 5000; // default 4000


    }

    @Test

    void StudentRegFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Insaf");
        $("#lastName").setValue("Aptrakov");
        $("#userEmail").setValue("insaf@aptrakov.com");
        $(".custom-control-label").click();
        $("#userNumber").setValue("9111111111");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--016").click();

        $("#subjectsInput").setValue("Arts").sendKeys(Keys.ENTER);
        $(byText("Sports")).ancestor(".custom-checkbox").click();
        $("#uploadPicture").uploadFromClasspath("test.jpeg");
        $("#currentAddress").setValue("test");

        $("#state").click();
        $(byText("NCR")).click();

        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();
//проверка
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Insaf"));
        $(".table-responsive").shouldHave(text("Aptrakov"));
        $(".table-responsive").shouldHave(text("insaf@aptrakov.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("9111111111"));
        $(".table-responsive").shouldHave(text("16 December,1999"));
        $(".table-responsive").shouldHave(text("Arts"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("test.jpeg"));
        $(".table-responsive").shouldHave(text("test"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

    }
}
