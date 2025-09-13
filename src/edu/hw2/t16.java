package edu.hw2;

import java.util.Scanner;

public class t16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(" ");
        String s1 = line[0];
        String s2 = line[1];

        int x = Integer.parseInt(s1, 2);
        int y = Integer.parseInt(s2, 2);
        int mask = x ^ y;

        int counter = 0;
        while (mask > 0) {
            counter++;
            mask &= (mask - 1);
        }
    }
}
