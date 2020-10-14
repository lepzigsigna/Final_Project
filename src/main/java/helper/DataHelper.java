package helper;

import com.github.javafaker.Faker;

public class DataHelper {
    private static Faker faker = new Faker();

    public static String generateName() {
        return "A_" + faker.name().firstName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateCategoryName () { return "A_" + faker.country().name(); }

    public static String generateArticleTitle() {
        return faker.country().capital() + faker.name().firstName();
    }

    public static String generateArticleContent() {
        return faker.lorem().sentence(100);
    }


}
