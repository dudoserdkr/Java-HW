package edu.hw1;

//╭─~/IdeaProjects/HW ‹main●›
//╰─$ javac -d out src/edu/hw1/t1.java
//╭─~/IdeaProjects/HW ‹main●›
//╰─$ java -cp out edu.hw1.t1
//Type your name: Ivan
//Hello, Ivan


import java.util.Scanner;

public class t1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, "utf-8");
        System.out.print("Type your name: ");
        String name = in.nextLine();
        System.out.println("Hello, " + name);

    }
}
