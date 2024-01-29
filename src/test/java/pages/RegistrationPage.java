package pages;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput =$("#userEmail"),
            userGenderInput =$("#genterWrapper"),
            userNumberInput =$("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput =$("#subjectsInput"),
            hobbiesInput=$("#hobbiesWrapper"),
            pictureInput= $("#uploadPicture"),
            userAddressInput=$("#currentAddress"),
            stateInput=$("#state"),
             stateCityInput=$("#stateCity-wrapper"),
            cityInput=$("#city"),
            modalContent = $(".modal-content"),
             submitInput=$("#submit");



    CalendarComponent calendarComponent = new CalendarComponent();
    ResultComponent resultComponent = new ResultComponent();


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;

    }
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;

    }
    public RegistrationPage setUserEmail(String value){
        userEmailInput.setValue(value);
        return this;

    }

    public RegistrationPage setGenderInput(String value){
        userGenderInput.$(byText(value)).click();
        return this;

    }

    public RegistrationPage setNumberInput(String value){
        userNumberInput.setValue(value);
        return this;

    }
    public RegistrationPage setDateOfBirthInput(String day, String month,String year){
        calendarInput.click();
        calendarComponent.setDate(day, month, year);



        return  this;
    }

    public RegistrationPage setSubjectsInput(String value){
        subjectsInput.setValue(value).pressEnter();
        return this;

    }

    public RegistrationPage setHobbiesInput(String value){
        hobbiesInput.$(byText(value)).click();
        return this;

    }

    public RegistrationPage setPicturesInput(String value){
        pictureInput.uploadFromClasspath(value);
        return this;

    }

    public RegistrationPage setAddressInput(String value){
        userAddressInput.setValue(value);
        return this;

    }

    public RegistrationPage setStateInput(String value){
        stateInput.click();
        stateCityInput.$(byText(value)).click();
        return this;

    }

    public RegistrationPage setCityInput(String value){
        cityInput.click();
        stateCityInput.$(byText(value)).click();
        return this;

    }
    public RegistrationPage submit(){
        submitInput.click();
        return this;


    }

//проверка резалтов

    public RegistrationPage checkResult(String field, String value){
        resultComponent.checkResult(field, value);


        return  this;

    }

    public RegistrationPage registrationFailureCheck() {
        modalContent.shouldBe(hidden);

        return this;
    }




}
