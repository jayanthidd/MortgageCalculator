package com.company;

import javax.print.attribute.standard.NumberUp;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;

public class Main {
    final static byte NUMBEROFMONTHS = 12;
    final static byte PERCENTAGE = 100;

    public static void main(String[] args) {
        double principal = readValue("PRINCIPAL : ", 1000, 1000000);
        float intRate = (float)readValue("Annual Interest Rate : ", 1, 10);
        int period = (int)readValue("Number Of Years : ", 10, 30);
        int paymentsmade = 1;

        printMortgage(principal, intRate, period);

        printPaymentSchedule(principal, intRate, period, paymentsmade);
    }

    private static void printMortgage(double principal, float intRate, int period) {
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(calcMortgage(principal, intRate, period)));
    }

    private static void printPaymentSchedule(double principal, float intRate, int period, int paymentsmade) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = paymentsmade; month <= period * NUMBEROFMONTHS; month++){
            double balance = calcBalance(principal, intRate, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readValue(String prompt, int min, int max){
        Scanner scanner = new Scanner(System.in);
        int value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
    public static double calcBalance(double principal, double intRate, int period, int paymentsmade){
        double balance;
        int numberOfPayments = period * NUMBEROFMONTHS;
        double monthlyInterest = intRate/NUMBEROFMONTHS/PERCENTAGE;
        balance = principal * (Math.pow(1+monthlyInterest,numberOfPayments ) - Math.pow(1 + monthlyInterest, paymentsmade)) / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    return balance;
    }
    public static double calcMortgage(double principal, double intRate, int period){
        int numberOfPayments = period * NUMBEROFMONTHS;
        double monthlyInterest = intRate/NUMBEROFMONTHS/PERCENTAGE;
        double intAmt = principal * monthlyInterest * Math.pow(monthlyInterest + 1, numberOfPayments) / (Math.pow(monthlyInterest + 1, numberOfPayments) - 1);
        return intAmt;
    }
}
