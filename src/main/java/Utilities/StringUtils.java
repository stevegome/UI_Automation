package Utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.security.SecureRandom;
import java.util.Random;

public class StringUtils {

    /**
     * Change character value to string
     *
     * @param value
     * @return
     */
    public static String changeCharToString(char value) {
        return String.valueOf(value);
    }

    /**
     * Generate random string for given n length
     *
     * @param n
     * @return
     */
    public static String generateRandomStringWithLengthOf(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder stringBuilder = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            stringBuilder.append(AlphaNumericString.charAt(index));
        }

        return stringBuilder.toString();
    }

    /**
     * Will generate random string including Prefix
     *
     * @param appendVal
     * @param n
     * @return
     */
    public static String generateRandomStringIncludingPrefix(String appendVal, int n) {

        String randomString = "";
        if (appendVal.length() < n) {
            int temp = n - appendVal.length();
            randomString = appendVal + StringUtils.generateRandomStringWithLengthOf(temp);

        } else {
            Assert.fail("Length of prefix should not greater than prefix value");
        }
        return randomString;
    }

    /**
     * Will generate random string appending Prefix
     *
     * @param appendVal
     * @param n
     * @return
     */
    public static String generateRandomStringWithAppendingPrefix(String appendVal, int n) {
        return appendVal + generateRandomStringWithLengthOf(n);
    }

    /**
     * Generate random email
     *
     * @param prefix
     * @param domain
     * @return
     */
    public static String getRandomEmail(String prefix, String domain) {
        Random random = new SecureRandom();
        return prefix + random.nextInt(999999) + "@" + domain;
    }

    /**
     * Generate random name
     *
     * @param n
     * @return
     */
    public static String getRandomName(int n) {
        String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaString.length() * Math.random());

            // add Character one by one in end of sb
            stringBuilder.append(AlphaString.charAt(index));
        }
        return stringBuilder.toString();
    }

    /**
     * Generate random name
     *
     * @return
     */
    public static String getRandomNumber(int n) {
        return RandomStringUtils.randomNumeric(n);
    }

}


