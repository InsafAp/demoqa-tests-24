package aptrakov.insaf.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue в репозитории")
@Story("Поиск  Issue")
@Owner("aptrakov")

public class LambdaAllure {



    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final Integer ISSUE_NUMBER = 87;


    @DisplayName("Test Issue, selenide listener")
    @Test
    public void testNativeListenerStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();

        $(withText("#" + ISSUE_NUMBER)).shouldBe(Condition.visible);
    }
    @Test
    @DisplayName("Test Issue, lambda steps")
   public  void lambdaAllureTest(){


        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

       step("Ищем репозиторий по имени " + REPOSITORY, () -> {
           $("[data-target='qbsearch-input.inputButtonText']").click();
           $("#query-builder-test").setValue(REPOSITORY).pressEnter();
       });

       step("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
           $(linkText("eroshenkoam/allure-example")).click();
       });

       step("Открываем таб Issues", () -> {
           $("#issues-tab").click();
       });
       step("Проверяем что существует Issue c номером " + ISSUE_NUMBER, () -> {
           $(withText("#" + ISSUE_NUMBER)).shouldBe(Condition.visible);
       });

    }

    @DisplayName("Test Issue с Selenide annotations")
    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);

    }


}
