package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class SmartechTest extends TestBase {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;

    }

    @BeforeEach
    public void init() {
        open("https://smartru.com/");
    }

    @Test
    @DisplayName("Проверка главной страницы")
    void mainPageTest() {
        $("body").shouldHave(text("Сервисы и услуги"));

    }

    @Test
    @DisplayName("Сервисы и услуги")
    void servicesMenuTest() {
        $(".card.border-0[href='/services/software-development/']").click();
        $("body").shouldHave(text("Высоконагруженные приложения"));

    }

    @Test
    @DisplayName("Работа главного меню")
    void mainMenuTest() {
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
                picture = "000.png";

        $(byText("Обсудить проект")).click();

        $("input[placeholder='Ваше имя*']").val(userName);
        $$("input[placeholder='Телефон*']").find(visible).val(phoneNumber);
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

        $("#btn-search").click();
        $("input[placeholder='Что вы ищете?']").val(searchQuery).pressEnter();
        $(".main-content").shouldHave(text("использовались Oracle и PostgreSQL"));

    }

    /*@Test
    @DisplayName("Проверка карусели со спонсорами")
    public void carouselUsingTest() {
        $$("[data-slick-index]").get(8).shouldNotBe(disappear);
        $(".slick-next.slick-arrow").scrollTo().click();
        sleep(2000);
        $$("[data-slick-index]").get(8).shouldBe(visible);

    }*/

    @Test
    @DisplayName("Проверка карусели со спонсорами")
    public void carouselUsingTest() {
        $(".carousel-wrapper [data-slick-index='8']").shouldNotBe(visible);
        $(".slick-next.slick-arrow").click();
        $(".carousel-wrapper [data-slick-index='8']").shouldBe(visible);

    }

    @Test
    @DisplayName("Вращение иконки при наведении")
    void iconTransformTest() {

        $("[src='/upload/uf/b79/b79387b68ef1ac28994d558ea399ab2d.svg']").hover();
        $("[src='/upload/uf/b79/b79387b68ef1ac28994d558ea399ab2d.svg']").shouldNotHave(cssClass("transform: rotate3d(0, 0, 0, 0deg"));

    }

}
