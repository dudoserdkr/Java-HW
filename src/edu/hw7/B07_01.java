package edu.hw7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class B07_01 {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("src/edu/hw7");
        Files.createDirectories(dir);
        Path input = dir.resolve("B07_01-input.bin");
        Path output = dir.resolve("B07_01-output.bin");
        generateInputFile(input, new double[]{1.0, 5.5, 6.2, -3.14, 10.0, 5.5000001, 0.0});
        double a = 5.5;
        double[] data = readAllDoubles(input);
        double[] filtered = Arrays.stream(data).filter(x -> x > a).toArray();
        writeDoubles(output, filtered);
        System.out.println("Input:  " + input.toAbsolutePath());
        System.out.println("Output: " + output.toAbsolutePath());
        System.out.println("a = " + a);
        System.out.println("Read:   " + Arrays.toString(data));
        System.out.println("Kept:   " + Arrays.toString(filtered));
    }

    public static void generateInputFile(Path file, double[] values) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(file))) {
            for (double v : values) dos.writeDouble(v);
        }
    }

    public static double[] readAllDoubles(Path file) throws IOException {
        ArrayList<Double> list = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(file))) {
            while (true) {
                try {
                    list.add(dis.readDouble());
                } catch (EOFException e) {
                    break;
                }
            }
        }
        double[] arr = new double[list.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
        return arr;
    }

    public static void writeDoubles(Path file, double[] values) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(file))) {
            for (double v : values) dos.writeDouble(v);
        }
    }
}
