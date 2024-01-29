package aptrakov.insaf;


import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;




    public class StudentRegFormPageObjectsTest extends TestBase {
        RegistrationPage registrationPage = new RegistrationPage();


        @Test
            ///тест все параметры
        void studentRegFormTest() {
            registrationPage.openPage()
                    .setFirstName("Insaf")
                    .setLastName("Aptrakov")
                    .setUserEmail("insaf@aptrakov.com")
                    .setGenderInput("Male")
                    .setNumberInput("9111111111")
                    .setDateOfBirthInput("16", "December", "1999")
                    .setSubjectsInput("Arts")
                    .setHobbiesInput("Sports")
                    .setPicturesInput("test.jpeg")
                    .setAddressInput("test")
                    .setStateInput("NCR")
                    .setCityInput("Delhi")
                    .submit();


//проверка


            registrationPage.checkResult("Student Name", "Insaf Aptrakov")
                    .checkResult("Student Email", "insaf@aptrakov.com")
                    .checkResult("Gender", "Male")
                    .checkResult("Mobile", "9111111111")
                    .checkResult("Date of Birth", "16 December,1999")
                    .checkResult("Subjects", "Arts")
                    .checkResult("Hobbies", "Sports")
                    .checkResult("Picture", "test.jpeg")
                    .checkResult("Address", "test")
                    .checkResult("State and City", "NCR Delhi");

        }

        @Test
            //тест обязательные параметры
        void studentRegFormReqParamsTest() {
            registrationPage.openPage()
                    .setFirstName("Insaf")
                    .setLastName("Aptrakov")
                    .setGenderInput("Male")
                    .setNumberInput("9111111111")
                    .setDateOfBirthInput("16", "December", "1999")
                    .submit();


            //проверка

            registrationPage.checkResult("Student Name", "Insaf Aptrakov")
                    .checkResult("Student Email", "\t")
                    .checkResult("Gender", "Male")
                    .checkResult("Mobile", "9111111111")
                    .checkResult("Date of Birth", "16 December,1999")
                    .checkResult("Subjects", "\t")
                    .checkResult("Hobbies", "\t")
                    .checkResult("Picture", "\t")
                    .checkResult("Address", "\t")
                    .checkResult("State and City", "\t");


        }


        @Test
            //негативная проверка
        void studentRegFormFailTest() {
            registrationPage.openPage()
                    .setFirstName("Insaf")
                    .submit();

            //проверка

            registrationPage.registrationFailureCheck();


        }
    }

