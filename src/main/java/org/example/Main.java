package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Started Calculator Application");
        calculator();
        logger.info("Exited Calculator Application");
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
                    logger.info("Operation: SQUARE ROOT");
                    System.out.print("Enter a number: ");
                    double squareRootNumber = scanner.nextDouble();
                    logger.info("Number entered: "+squareRootNumber);
                    double squareRootResult = getSqrt(squareRootNumber);
                    System.out.println("The square root of " + squareRootNumber + " is " + squareRootResult);
                    logger.info("Got result: "+squareRootResult);
                    break;
                case 2:
                    logger.info("Operation: FACTORIAL");
                    System.out.print("Enter a number: ");
                    int factorialNumber = scanner.nextInt();
                    logger.info("Number entered: "+factorialNumber);
                    int factorialResult = getFactorial(factorialNumber);
                    System.out.println(factorialNumber + "! = " + factorialResult);
                    logger.info("Got result: "+factorialResult);
                    break;
                case 3:
                    logger.info("Operation: NATURAL LOG");
                    System.out.print("Enter a number: ");
                    double naturalLogNumber = scanner.nextDouble();
                    logger.info("Number entered: "+naturalLogNumber);
                    double naturalLogResult = getLog(naturalLogNumber);
                    System.out.println("The natural logarithm of " + naturalLogNumber + " is " + naturalLogResult);
                    logger.info("Got result: "+naturalLogResult);
                    break;
                case 4:
                    logger.info("Operation: POWER FUNCTION");
                    System.out.print("Enter the base: ");
                    double powerBase = scanner.nextDouble();
                    logger.info("Base entered: "+powerBase);
                    System.out.print("Enter the exponent: ");
                    double powerExponent = scanner.nextDouble();
                    logger.info("Power entered: "+powerExponent);
                    double powerResult = getPow(powerBase, powerExponent);
                    System.out.println(powerBase + "^" + powerExponent + " = " + powerResult);
                    logger.info("Got result: "+powerResult);
                    break;
                case 0:
                    logger.info("Exit");
                    System.out.println("Exiting...");
                    break;
                default:
                    logger.info("Invalid choice");
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
