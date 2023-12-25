package aptrakov.insaf;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000


    }

    @Test

    void StudentRegForm() {

        open("/automation-practice-form");
        $("#firstName").setValue("Insaf");
        $("#lastName").setValue("Aptrakov");
        $("#userEmail").setValue("insaf@aptrakov.com");
        $("[for=gender-radio-1").click();
        $("#userNumber").setValue("9111111111");
        $("#dateOfBirthInput").setValue("").sendKeys(Keys.ENTER);
        $("#subjectsInput").setValue("Arts").sendKeys(Keys.ENTER);
        $("#hobbies-checkbox-1").ancestor(".custom-checkbox").click();
        $("#uploadPicture").uploadFromClasspath("test.jpeg");
        $("#currentAddress").setValue("test");
        $("#react-select-3-input").setValue("NCR").sendKeys(Keys.ENTER);
        $("#react-select-4-input").setValue("Delhi").sendKeys(Keys.ENTER);

        sleep(2000);

        $("#submit").click();
//проверка
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}
