package utils;

import java.util.Random;

public class RandomUtil {
    private static int numLength = 3;
    private static int distanceFromBound = 10;
    private static int firstDigitIndex = 0;
    private static int secondDigitIndex = 1;

    public static int getRandomValue(int origin, int bound) {
        Random rand = new Random();
        return rand.nextInt(origin, bound);
    }

    public static int generateIdTwoSameNum(int origin, int bound) {
        String val;
        int randomNumber = RandomUtil.getRandomValue(origin, bound);

        if (bound - randomNumber < distanceFromBound) {
            randomNumber = randomNumber - distanceFromBound;
        }
        String randomNumString = String.valueOf(randomNumber);
        if (randomNumString.length() >= numLength) {
            val = String.valueOf(randomNumString.charAt(firstDigitIndex)) + randomNumString.charAt(secondDigitIndex)
                    + randomNumString.charAt(secondDigitIndex);
        } else {
            val = String.valueOf(randomNumString.charAt(firstDigitIndex)) + randomNumString.charAt(firstDigitIndex);
        }
        return Integer.parseInt(val);
    }
}
