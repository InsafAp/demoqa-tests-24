package pages.components;
import static com.codeborne.selenide.Selenide.*;
public class CalendarComponent {
    public  void setDate(String day, String month,String year){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--016").click();
    }
}
