package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SmartechTest extends BaseTest {

    @Test
    @DisplayName("Открытие главной страницы")
    void mainPageTest() {
        open("https://smartru.com/");
        $("body").shouldHave(text("Сервисы и услуги"));

    }

    @Test
    @DisplayName("Сервисы и услуги")
    void servicesMenuTest() {
        open("https://smartru.com/");
        $(".card.border-0[href='/services/software-development/']").click();
        //$$(byText("Разработка программного обеспечения")).find(visible).parent().click();
        $("body").shouldHave(text("Высоконагруженные приложения"));

    }

    @Test
    @DisplayName("Работа главного меню")
    void mainMenuTest() {
        open("https://smartru.com/");
        $(byText("О нас")).hover();
        $(".dropdown-menu.show").shouldHave(text("История компании"));
        $(".dropdown-menu.show").$(byText("Отзывы")).click();
        $("body").shouldHave(text("Смартек сделали"));

    }

    @Test
    @DisplayName("Проверка заполнения формы")
    void projectEvaluationFormTest() {
        String  userName = "Di",
                phoneNumber = "+77 777 777 77 77",
                userEmail = "111111@gmail.com",
                userMassage = "Привет!",
                picture = "_53876-66152.jpg";

        open("https://smartru.com/");
        $(byText("Обсудить проект")).click();

        $("input[placeholder='Ваше имя*']").val(userName);
        $("input[placeholder='Телефон*']").val(phoneNumber);
        $("input[placeholder='Email']").val(userEmail);
        $("textarea[placeholder='Сообщение']").val(userMassage);
        $(".inputfile").uploadFile(new File("src/test/resources/" + picture));
        $(".btn-block").click();
        $(".form-group.has-error.field-agreement").shouldBe(visible);

    }

    @Test
    @DisplayName("Проверка поисковой строки")
    void searchTest() {
        String searchQuery = "о нас";

        open("https://smartru.com/");
        $("#btn-search").click();
        $("input[placeholder='Что вы ищете?']").val(searchQuery).pressEnter();
        $(".main-content").shouldHave(text("использовались Oracle и PostgreSQL"));

    }

}