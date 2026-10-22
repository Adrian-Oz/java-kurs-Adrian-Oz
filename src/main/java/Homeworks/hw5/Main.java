package Homeworks.hw5;

public class Main {
    public static void main(String[] args) {

        String[] cards = {
                "LC-123456",
                "LC-ASD123",
                "LC-111111",
                "123123123"

        };
        int[] centsPerCard = {
                10000,
                10,
                1230912,
                931023,
        };

        for (int i = 0; i < cards.length; i++) {
            if (!LoyaltyCard.hasValidFormat(cards[i])) {
                System.out.println("format odrzucony");

                continue;
            }

            String currentCardSpend = LoyaltyCard.formatMoney(centsPerCard[i]);
            String currentCard = cards[i];
            int currentCardPoints = LoyaltyCard.pointsFor(centsPerCard[i]);
            String currentCardBar = LoyaltyCard.drawBar(currentCardPoints);

            System.out.println("Card digits sum: " + LoyaltyCard.digitSum(cards[i]));
            System.out.printf("Card %s has spend : %s%n", currentCard, currentCardSpend);
            System.out.printf("Points: %d   %s%n", currentCardPoints, currentCardBar);

        }

        String testCard = "LC-123456";
        int recursiveSum = LoyaltyCard.digitSumRecursive(testCard, 3);
        int normalSum = LoyaltyCard.digitSum(testCard);

        System.out.printf("Test card : %s | Recursive : %d | Normal : %d", testCard, recursiveSum, normalSum);


    }
}
