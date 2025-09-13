package edu.hw2;

import java.util.Scanner;

public class t5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input the array size -> ");

        int n = in.nextInt();
        System.out.print("Type " + n + " values -> ");

        double[] arr = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i ++) {
            arr[i] = in.nextDouble();
            sum += arr[i];
        }
        sum /= n;
        System.out.println("Mean of the array -> " + sum);
    }
}
