package utilities;

import java.util.Random;

public class RandomUtils {
    private static final String LATIN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String CYRILLIC = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String ALL_CHARACTERS = LATIN + LATIN.toLowerCase() + DIGITS + CYRILLIC + CYRILLIC.toLowerCase();

    public static int getRandomValue(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while (sb.length() < length) {
            sb.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generatePassword(String mail, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        sb.append(mail.charAt(0));
        sb.append(LATIN.charAt(random.nextInt(LATIN.length())));
        sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        sb.append(CYRILLIC.charAt(random.nextInt(CYRILLIC.length())));
        while (sb.length() < length) {
            sb.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return sb.toString();
    }
}
