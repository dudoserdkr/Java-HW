package edu.hw1;

// ╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ javac -d out src/edu/hw1/t3.java
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t3 10 0.1
//1.0
//╭─ ~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t3 10 0.1 H
//Some of the arguments are not real numbers

public class t3 {
    public static void main(String[] args) {
        double prod = 1;
        for (String s: args) {
            try {
                double tmp = Double.parseDouble(s);
                prod *= tmp;
            } catch (NumberFormatException e) {
                System.out.println("Some of the arguments are not real numbers");
                return;
            }
        }
        System.out.println(prod);
    }
}
