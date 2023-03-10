package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        calculator();
    }

    public static void calculator() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Scientific Calculator");
            System.out.println("---------------------");
            System.out.println("1. Square root function - √x");
            System.out.println("2. Factorial function - x!");
            System.out.println("3. Natural logarithm (base е) - ln(x)");
            System.out.println("4. Power function - x^y");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter a number: ");
                    double squareRootNumber = scanner.nextDouble();
                    double squareRootResult = getSqrt(squareRootNumber);
                    System.out.println("The square root of " + squareRootNumber + " is " + squareRootResult);
                    break;
                case 2:
                    System.out.print("Enter a number: ");
                    int factorialNumber = scanner.nextInt();
                    int factorialResult = getFactorial(factorialNumber);
                    System.out.println(factorialNumber + "! = " + factorialResult);
                    break;
                case 3:
                    System.out.print("Enter a number: ");
                    double naturalLogNumber = scanner.nextDouble();
                    double naturalLogResult = getLog(naturalLogNumber);
                    System.out.println("The natural logarithm of " + naturalLogNumber + " is " + naturalLogResult);
                    break;
                case 4:
                    System.out.print("Enter the base: ");
                    double powerBase = scanner.nextDouble();
                    System.out.print("Enter the exponent: ");
                    double powerExponent = scanner.nextDouble();
                    double powerResult = getPow(powerBase, powerExponent);
                    System.out.println(powerBase + "^" + powerExponent + " = " + powerResult);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(choice != 0);

        scanner.close();
    }

    public static double getPow(double powerBase, double powerExponent) {
        return Math.pow(powerBase, powerExponent);
    }

    public static double getLog(double naturalLogNumber) {
        return Math.log(naturalLogNumber);
    }

    public static int getFactorial(int factorialNumber) {
        int factorialResult = 1;
        for(int i = 1; i <= factorialNumber; i++) {
            factorialResult *= i;
        }
        return factorialResult;
    }

    public static double getSqrt(double squareRootNumber) {
        return Math.sqrt(squareRootNumber);
    }
}