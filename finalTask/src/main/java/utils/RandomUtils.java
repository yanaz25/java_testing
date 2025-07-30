package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomUtils {

    public static int getRandomValue(int origin, int bound) {
        Random rand = new Random();
        return rand.nextInt(origin, bound);
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
