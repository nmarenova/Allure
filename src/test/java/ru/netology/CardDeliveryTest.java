package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:9999/");

    }


    @Test
    void shouldCardDeliveryNewDate() {
        User user = DataGenerator.Registration.generateUser();
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(DataGenerator.Registration.getDate(3));
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(" [data-test-id='success-notification']").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(text("Успешно! Встреча успешно запланирована на " + DataGenerator.Registration.getDate(3)));
        $(".calendar-input input").sendKeys(Keys.CONTROL + "a");
        $(".calendar-input input").sendKeys(Keys.DELETE);
        $(".calendar-input input").sendKeys(DataGenerator.Registration.getDate(3));
        $$("button").find(exactText("Запланировать")).click();
        $(" [data-test-id='replan-notification']")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$("button").find(exactText("Перепланировать")).click();
        $(" [data-test-id='success-notification']")
                .shouldHave(text("Успешно! Встреча успешно запланирована на " + DataGenerator.Registration.getDate(3)));
    }
}


