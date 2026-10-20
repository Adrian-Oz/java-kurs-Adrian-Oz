package Homeworks.hw2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter flight number :");
        String flightNumber = formatText(sc.nextLine());
        if (flightNumber.isBlank()) {
            System.out.println("REJECTED:");
            System.out.println("Flightnumber  cannot be empty ! ");
            return;
        } else if (flightNumber.length() < 4 || flightNumber.length() > 6) {
            System.out.println("REJECTED:");
            System.out.println("Flightnumber must be between 4 to 6 characters");
            return;
        }

        System.out.println("Enter your surname: ");
        String passengerSurname = formatText(sc.nextLine());
        if (passengerSurname.isBlank()) {
            System.out.println("REJECTED:");
            System.out.println("Surname cannot be empty ! ");
            return;
        }
        System.out.println("Enter flight class (ECONOMY / PREMIUM / BUSINESS / FIRST)");
        String flightClass = formatText(sc.nextLine());

        System.out.println("Enter luggage weight:  (eg. kk,gg) ");
        String baggageWeight = sc.nextLine();
        if (!baggageWeight.contains(",") || baggageWeight.substring(baggageWeight.indexOf(",")).length() != 3) {
            System.out.println("REJECTED:");
            System.out.println("Baggage weight must contain \",\" and have 2 digits after it.");
            return;
        }
        String[] baggageWeights = baggageWeight.split(",");
        int baggageWeightInGrams = Integer.parseInt(baggageWeights[0]) * 1000 + Integer.parseInt(baggageWeights[1]) * 10;
        if (baggageWeightInGrams < 0 || baggageWeightInGrams > 60000) {
            System.out.println("REJECTED:");
            System.out.println(baggageWeightInGrams);
            System.out.println("Baggage weight has to be between 0-60kg");
            return;
        }
        System.out.println("Enter your age");
        int passengerAge = Integer.parseInt(sc.nextLine());

//
//
//        Different approach , less intuitive and checks after all inputs
//
//
//        if (flightNumber.isBlank() || passengerSurname.isBlank()) {
//            System.out.println("REJECTED:");
//            System.out.println("Flightnumber and Surname cannot be empty ! ");
//            return;
//        } else if (flightNumber.length() < 4 || flightNumber.length() > 6) {
//            System.out.println("REJECTED:");
//            System.out.println("Flightnumber must be between 4 to 6 characters");
//            return;
//        } else if (baggageWeight.contains(",") && baggageWeight.substring(baggageWeight.indexOf(",")).length() == 2) {
//            System.out.println("REJECTED:");
//            System.out.println("Baggage weight has to have 2 digits after the \",\"");
//            return;
//        } else if (baggageWeightInGrams > 0 && baggageWeightInGrams < 60000) {
//            System.out.println("REJECTED:");
//            System.out.println("Baggage weight has to be between 0-60kg");
//            return;
//        }

        int baggageLimit = switch (flightClass) {
            case "ECONOMY" -> 23000;
            case "PREMIUM" -> 28000;
            case "BUSINESS" -> 32000;
            case "FIRST" -> 40000;
            default -> {
                System.out.println("UWAGA: nieznana klasa flightClass, przyjmuję limit economy");
                yield 23000;

            }


        };

        String boarding = (flightClass.equals("BUSINESS") || flightClass.equals("FIRST")) ? "PRIORITY" : "normalne";
        String guardianNote = (passengerAge < 16) ? "PASAŻER MAŁOLETNI — WYMAGANA OPIEKA" : "";

        System.out.printf("%-20s %s%n", "LOT:", flightNumber);
        System.out.printf("%-20s %s%n", "PASAŻER:", passengerSurname);
        System.out.printf("%-20s %s%n", "KLASA:", flightClass);
        System.out.printf("%-20s %d,%02d kg%n", "WAGA:",
                baggageWeightInGrams / 1000,
                (baggageWeightInGrams % 1000) / 10);
        System.out.printf("%-20s %d,%02d kg%n", "LIMIT:",
                baggageLimit / 1000,
                0);
        int overweight = baggageWeightInGrams - baggageLimit;
        if (overweight > 0) {
            System.out.printf("%-20s %d,%02d kg%n", "NADWAGA:",
                    overweight / 1000,
                    (overweight % 1000) / 10);
        } else {
            System.out.printf("%-20s %d,%02d kg%n", "NADWAGA:",
                    0,
                    0);
        }

        int baggageFee = calculateFee(baggageWeightInGrams, baggageLimit);
        System.out.printf("%-20s %d zł%n", "OPŁATA:", baggageFee);

        if (baggageFee == 300) {
            System.out.println("BAGAŻ DO NADANIA JAKO CARGO");
        }
        System.out.printf("%-20s %s%n", "BOARDING:", boarding);

        if (!guardianNote.isEmpty()) {
            System.out.println(guardianNote);
        }


    }


    public static int calculateFee(int weight, int baggageLimit) {
        int overweight = weight - baggageLimit;
        if (overweight < 0) {
            return 0;
        } else if (overweight < 5000) {
            return 50;
        } else if (overweight < 15000) {
            return 150;
        } else {
            return 300;
        }


    }

    public static String formatText(String str) {
        return str.trim().toUpperCase().replaceAll("\\s+", "");
    }


}
