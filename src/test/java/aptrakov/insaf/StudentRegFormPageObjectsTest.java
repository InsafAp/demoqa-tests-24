package aptrakov.insaf;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.testdata.TestData;

import java.util.Map;

import static io.qameta.allure.Allure.step;


public class StudentRegFormPageObjectsTest  {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }



    @Test
    @Tag("demoqa")
    void studentRegFormTest() {
        step("Open and Fill form", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setUserEmail(testData.userEmail)
                    .setGenderInput(testData.gender)
                    .setNumberInput(testData.phone)
                    .setDateOfBirthInput(testData.birthDay, testData.birthMonth, testData.birthYear)
                    .setSubjectsInput(testData.subject)
                    .setHobbiesInput(testData.hobby)
                    .setPicturesInput(testData.picture)
                    .setAddressInput(testData.address)
                    .setStateInput(testData.state)
                    .setCityInput(testData.city)
                    .submit();
        });

        step("Verify results", () -> {
            registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.userEmail)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.phone)
                    .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                    .checkResult("Subjects", testData.subject)
                    .checkResult("Hobbies", testData.hobby)
                    .checkResult("Picture", testData.picture)
                    .checkResult("Address", testData.address)
                    .checkResult("State and City", testData.state + " " + testData.city);

        });
    }

    @Test
    @Tag("demoqa")
    void studentRegFormReqParamsTest() {
        step("Open and Fill form", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGenderInput(testData.gender)
                    .setNumberInput(testData.phone)
                    .setDateOfBirthInput(testData.birthDay, testData.birthMonth, testData.birthYear)
                    .submit();
        });

        step("Verify results", () -> {
            registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", "\t")
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.phone)
                    .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                    .checkResult("Subjects", "\t")
                    .checkResult("Hobbies", "\t")
                    .checkResult("Picture", "\t")
                    .checkResult("Address", "\t")
                    .checkResult("State and City", "\t");


        });
    }


    @Test
    @Tag("demoqa")
    void studentRegFormFailTest() {
        step("Open and Fill form", () -> {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .submit();
        });

        step("Verify results", () -> {
        registrationPage.registrationFailureCheck();
        });


    }
}

