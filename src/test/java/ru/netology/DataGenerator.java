package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static User generateUser() {
            Faker faker = new Faker(new Locale("ru"));
            return new User(faker.address().city(), faker.name().fullName(), faker.phoneNumber().phoneNumber());
        }

        public static String getDate(int date) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate newDate=today.plusDays(date);
            return formatter.format(newDate);
        }
    }
}