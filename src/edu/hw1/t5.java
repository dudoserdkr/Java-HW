package edu.hw1;

import java.util.Scanner;

//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t5 1 2
//2
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t5 1
//Exception in thread "main" java.lang.IllegalArgumentException: Two values must be typed
//	at edu.hw1.t5.main(t5.java:16)
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t5
//1 2
//1
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t5 1 5
//2
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t5 5 5
//0
//5
//1
//3
//1

public class t5 {
    public static void main(String[] args) {
        int N, M;
        if (args.length == 0) {
            Scanner in = new Scanner(System.in);
            N = in.nextInt();
            M = in.nextInt();
        } else if (args.length == 2){
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Two values must be typed");
        }

        M++; // if we don't M++ rnd vals will be in [0, M)
        for (int i = 0; i < N; i++) {
            int rnd = (int) (Math.random() * M);
            System.out.println(rnd);
        }
    }
}