package edu.hw2;

import java.util.Scanner;

public class t17c {
    public static double f(double x, double epsilon) {
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException("|x| must be < 1");
        }
        if (epsilon <= 0.0) {
            throw new IllegalArgumentException("epsilon must be > 0");
        }

        double a = 1;
        double S = a;

        int k = 1;
        while (Math.abs((k + 1) * a) >= epsilon) {
            a *= -x;
            S += (k + 1) * a;
            k++;
        }
        return S;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Type both x and epsilon spaced -> ");
        double x = in.nextDouble();
        double epsilon = in.nextDouble();
        System.out.println(f(x, epsilon));
    }
}
