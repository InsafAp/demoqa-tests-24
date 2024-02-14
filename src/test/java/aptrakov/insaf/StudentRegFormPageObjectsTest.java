package aptrakov.insaf;


import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.testdata.TestData;


public class StudentRegFormPageObjectsTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();


    @Test
    void studentRegFormTest() {
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

    }

    @Test
    void studentRegFormReqParamsTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGenderInput(testData.gender)
                .setNumberInput(testData.phone)
                .setDateOfBirthInput(testData.birthDay, testData.birthMonth, testData.birthYear)
                .submit();


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


    }


    @Test
    void studentRegFormFailTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .submit();


        registrationPage.registrationFailureCheck();


    }
}

