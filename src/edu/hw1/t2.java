package edu.hw1;

//╭─~/IdeaProjects/HW ‹main●›
//╰─$ javac -d out src/edu/hw1/t2.java
//╭─~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t2 Hello, World!
//!dlroW ,olleH%

public class t2 {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = new StringBuilder(args[i]).reverse().toString();

        }
        for (int j = args.length - 1; j > -1; j--) {
            if (j == 0) {
                System.out.print(args[j]);
            } else {
                System.out.print(args[j] + " ");
            }
        }
    }
}
