package Homeworks.hw4;

import java.util.Arrays;
import java.util.Scanner;


//Didn't focus on giving user more tries as assigment is focused on arrays.
//Didn't focus on input validation.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of readings");
        String input = sc.nextLine().trim();

        int readingsAmount = Integer.parseInt(input);
        int[] readings = new int[readingsAmount];

        for (int i = 0; i < readings.length; i++) {
            System.out.printf("Please enter the %d reading : %n", i + 1);
            input = sc.nextLine().trim();
            readings[i] = Integer.parseInt(input);
        }
//      Treating entries as whole numbers
//      Assume that reading.lenght is at least 1.
        int sum = readings[0];
        int max = readings[0];
        int min = readings[0];

//      Can be changed to for-each
        for (int i = 1; i < readings.length; i++) {
            if (readings[i] < min) {
                min = readings[i];
            } else if (readings[i] > max) {
                max = readings[i];
            }
            sum += readings[i];
        }

        double avg = (double) sum / readings.length;
        int aboveAvgCounter = 0;

        for (int item : readings) {
            if (item > avg) {
                aboveAvgCounter++;
            }
        }

        System.out.println("Average: " + avg);
        System.out.println("Readings above average: " + aboveAvgCounter);

        int[] copy = Arrays.copyOf(readings, readings.length);
        Arrays.sort(copy);
        double median;
        if (copy.length % 2 == 0) {
            median = ((double) copy[copy.length / 2] + (double) copy[copy.length / 2 - 1]) / 2;
        } else {
            median = copy[copy.length / 2];
        }

        System.out.println("Median: " + median);
        System.out.println("Original: " + Arrays.toString(readings));
        System.out.println("Copy: " + Arrays.toString(copy));


        int[][] readingsArray = {
                {10, 20, 30, 40},
                {20, -10, 203, 20},
                {10, 2, 0, 230, 20},
        };


        double buildingAvg = 0;
        int buildingSum = 0;
        double[] maxBuilding = {Double.NEGATIVE_INFINITY, 0};
        for (int i = 0; i < readingsArray.length; i++) {
            buildingSum = 0;
            for (int j = 0; j < readingsArray[i].length; j++) {
                buildingSum += readingsArray[i][j];

            }
            buildingAvg = (double) buildingSum / readingsArray[i].length;
            if (maxBuilding[0] < buildingAvg) {
                maxBuilding[0] = buildingAvg;
                maxBuilding[1] = i + 1;
            }


        }
        System.out.println("Building with highest average : ");
        System.out.println(Arrays.toString(maxBuilding));

        sc.close();
    }

}
