package helper;

import com.github.javafaker.Faker;

public class DataHelper {
    private static Faker faker = new Faker();

    public static String generateName() {
        return faker.name().firstName() + "_" + faker.letterify("???") + ">VN";
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateCategoryName() {
        return faker.country().name() + "_" + faker.number().numberBetween(0, 100);
    }

    public static String generateArticleTitle() {
        return faker.country().capital() + "_" + faker.letterify("???") + ">VN";
    }

    public static String generateArticleContent() {
        return faker.lorem().sentence(100);
    }

    public static String generateURL() {
        return faker.internet().url();
    }

}
