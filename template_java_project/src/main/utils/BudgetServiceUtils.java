package main.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

public class BudgetServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\]");
    // package private for testing
    static final int BUDGET_ID_LENGTH = 5;

    // do not instantiate
    private BudgetServiceUtils() {}

    /**
     * Checks that the provided String contains only valid characters.
     *
     * @param stringToValidate the Budget name to be validated
     * @return true if the String is valid (contains only valid characters),
     *         false otherwise
     */
    public static boolean isValidString(final String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        }

        return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }

    public static String generateBudgetId() {
        return RandomStringUtils.randomAlphanumeric(BUDGET_ID_LENGTH);
    }

    public static String generateSpendingCategoryId() { return RandomStringUtils.randomAlphanumeric(BUDGET_ID_LENGTH); }
}
