package Homeworks.hw3;


import java.util.Scanner;

//THIS CODE IS SO UNREADABLE :D due to constrains.

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Helper class to make code more readable
        Package currentPackage = new Package();


        String input = "";
        // This flags, accumulators could be in some class as static fields??
        int approved = 0;
        int denied = 0;
        int totalWeight = 0;
        double avgWeight = 0;
        int totalFee = 0;

        int maxWeight = 0;
        String maxWeightCode = "";

        String previousLocal = "";
        int currentStreak = 0;

        String longestLocal = "";
        int longestStreak = 0;

        String[] locales = {"PL", "EU", "WORLD"};// could do enums here

        // While loop controls
        boolean endFlag = false;
        boolean skipIteration = true;
        boolean weightFlag;
        boolean localeFlag;

        System.out.println("Insert the package codes. If you wish to end, enter \"KONIEC\"");
//         While(true) creates endless loop, needs break to exit.
        while (true) {
            System.out.println("Enter package code: ");
//          Code check max 3 times
            for (int i = 0; i < 3; i++) {
                input = sc.nextLine();
                if (input.equals("KONIEC")) {
                    endFlag = true;
                    skipIteration = true;
                    break;
                }
//              Reset flag before check
                skipIteration = true;
                if (input.contains("-") && input.length() >= 5) {
                    skipIteration = false;
                    currentPackage.setCode(input);
//                    for loop break
                    break;

                } else {
                    System.out.println("Package number must contain \"-\" and has min. 5 length");

                }
            }
            if (skipIteration) {
//              Check endFlag to not increase denied counter on KONIEC
                if (endFlag) {
//                    While loop
                    break;
                }
                System.out.println("Package denied, maximum attempts reached (3)");
                denied++;
//                Skip current iteration
                continue;
            }
            approved++;

//          Unlimited tries, do while reduces 1 check ¯\_(ツ)_/¯
            do {
                System.out.println("Enter weight in grams: ");
                input = sc.nextLine();
                weightFlag = validateWeight(input);
            }
            while (!weightFlag);
            currentPackage.setWeight(Integer.parseInt(input));

//          totalWeight and avgWeight logic
            totalWeight += Integer.parseInt(input);
            avgWeight = (double) totalWeight / approved;

            if (maxWeight < currentPackage.getWeight()) {
                maxWeight = currentPackage.getWeight();
                maxWeightCode = currentPackage.getCode();
            }
//          Unlimited tries, do while reduces 1 check ¯\_(ツ)_/¯
            do {

                System.out.println("Enter localization. (PL/EU/WORLD): ");
                input = sc.nextLine().toUpperCase().trim();
//              reset localeFlag
                localeFlag = true;
                for (String item : locales) {
                    if (item.equals(input)) {
                        localeFlag = false;
                        break;
                    }

                }
                if (localeFlag) System.out.println("Error : Please choose from available options");

            }
            while (localeFlag);
            currentPackage.setLocal(input);


            //Streak logic
            if (currentPackage.getLocal().equals(previousLocal)) {
                currentStreak++;
            } else {
                if (longestStreak < currentStreak) {
                    longestStreak = currentStreak;
                    longestLocal = previousLocal;
                }
                currentStreak = 1;
            }
            previousLocal = currentPackage.getLocal();

            //Update total fee
            totalFee += currentPackage.getFee();
//            System.out.println(currentPackage.toString());
            System.out.println("Package approved");



        }

        // Final streak check for no locale change.
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
            longestLocal = previousLocal;
        }

//      PRINT INFO HERE
        System.out.printf("%-40s%10d%n", "Approved:", approved);
        System.out.printf("%-40s%10d%n", "Denied:", denied);
        System.out.printf("%-40s%10d%n", "Total weight:[g]", totalWeight);
        System.out.printf("%-40s%10.2f%n", "Average weight:[g]", avgWeight);
        System.out.printf("%-40s%10s%n", "Total fee:", centsToZl(totalFee));
        System.out.printf("%-30s%10s%10d%n", "Heaviest code and weight[g]:", maxWeightCode, maxWeight);
        System.out.printf("%-30s%10s%10d%n", "Longest zone streak:", longestLocal, longestStreak);


    }

    //    HELPER METHODS
    static boolean validateWeight(String weight) {
        // check if only numbers are in the string first, so i can chain if else if.
        for (int i = 0; i < weight.length(); i++) {
            if (weight.charAt(i) < 48 || weight.charAt(i) > 57) {
                System.out.println("Weight must be a whole number !");
                return false;
            }
        }
        if (weight.length() > 5 || weight.isEmpty()) {
            System.out.println("Weight must be between 1-5 characters");
            return false;
        } else if (Integer.parseInt(weight) < 1 || Integer.parseInt(weight) > 30000) {
            System.out.println("Weight must be a number between 1-30000");
            return false;
        }


//    :C
//    else if (!weight.matches("[0-9]+") || weight.contains(" ")) {
//            System.out.println("Weight must be a whole number in grams !");
//        }

        return true;
    }

    static String centsToZl(int cents) {
        int zl = cents / 100;
        int gr = cents % 100;
        return String.format("%d,%02d zł", zl, gr);
    }

}
