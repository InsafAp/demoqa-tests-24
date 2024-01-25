package aptrakov.insaf;

import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;


public class HoverAndDragAndDrop {
    @BeforeAll
    static void beforeAll(){
        browserSize = "1920x1080";
        baseUrl = "https://dgithub.com";
        pageLoadStrategy = "eager";
        holdBrowserOpen = false;
        timeout = 5000; // default 4000
    }

    @Test
     void actionHover(){
        open("https://github.com/");
        $$(".HeaderMenu-link.border-0").filterBy(text("Solutions")).first().hover();
        $$(".HeaderMenu-dropdown-link").filterBy(text("Enterprise")).first().click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\n"+"developer platform."));



    }

    @Test

    void dragAndDrop(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        //actions().dragAndDrop($("#column-a"),$("#column-b")).perform();
        $("#column-a").dragAndDrop(DragAndDropOptions.to($("#column-b")));
        $("#column-b").shouldHave(text("A"));

    }
}
