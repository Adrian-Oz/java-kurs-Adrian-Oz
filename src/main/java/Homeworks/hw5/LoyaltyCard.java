package Homeworks.hw5;

public class LoyaltyCard {


    public static boolean hasValidFormat(String card) {

        return card != null && card.startsWith("LC-") && card.length() == 9;
    }

    public static int digitSum(String card) {
        int sum = 0;
        for (int i = 3; i < card.length(); i++) {
            int number = card.charAt(i) - '0';
            if (number >= 0 && number < 10) {
                sum += number;
            }
        }

        return sum;
    }

    public static int digitSumRecursive(String card, int index) {
        if (index == card.length()) {
            return 0;
        }

        return (card.charAt(index) - '0') + digitSumRecursive(card, index + 1);
    }

    public static int digitSumRecursive(int number) {
        if (number < 10) {
            return number;
        }

        return number % 10 + digitSumRecursive(number / 10);
    }

    public static int pointsFor(int cents) {
//      int cleans fractal part.
        return cents / 10000;

    }

    public static String drawBar(int points) {
        return "#".repeat(points);
    }


    public static String formatMoney(int cents, String currency) {
        double formattedValue = cents / 100d;
        return String.format("%.2f %s", formattedValue, currency);

    }

    public static String formatMoney(int cents) {
        return formatMoney(cents, "zł");
    }


}
