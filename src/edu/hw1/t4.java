package edu.hw1;

import java.util.Scanner;

//╭─~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t4
//3 4 5
//3.8298

public class t4 {
    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int x = in.nextInt();
         int y = in.nextInt();
         int z = in.nextInt();
         double garmonic_mean = 3 / (Math.pow(x, -1) + Math.pow(y, -1) + Math.pow(z, -1));
         System.out.printf("%.4f%n", garmonic_mean);
    }
}
