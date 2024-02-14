package pages.testdata;
import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String gender = getGender();
    public String phone = faker.phoneNumber().subscriberNumber(10);
    public String birthDay = String.valueOf(faker.number().numberBetween(1, 28));
    public String birthMonth = getMonth();
    public String birthYear = String.valueOf(faker.number().numberBetween(1900, 2019));
    public String subject = getSubject();
    public String hobby = getHobbies();
    public String picture = "test.jpeg";
    public String address = faker.address().fullAddress();

    public String state = getState();
    public String city = getStateByCity(state);




    public String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }
    public String getMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }
    public String getSubject() {
        return faker.options().option("Maths", "Arts", "English", "Biology", "Hindi", "Commerce");
    }

    public String getHobbies() {

        return faker.options().option("Sports", "Reading", "Music");
    }
    public String getState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }
    public String getStateByCity(String value){

        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> state;
        };
    }


}
