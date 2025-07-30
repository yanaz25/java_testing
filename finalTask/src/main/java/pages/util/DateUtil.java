package pages.util;

public class DateUtil {
    private static String startPrefix = "2";
    private static int indexOffset = 2;
    private static String delimiter = ":";
    private static String dotSymbol = ".";

    public static String parseDate(String dateTimeText) {
        int dateStartIndex = dateTimeText.indexOf(delimiter) + indexOffset;
        int dotIndex = dateTimeText.indexOf(dotSymbol);
        if (dateTimeText != null && !dateTimeText.isEmpty()) {
            if (dateTimeText.startsWith(startPrefix)) {
                dateStartIndex = 0;
            }
            return dateTimeText.substring(dateStartIndex, dotIndex);
        }
        return "";
    }
}
