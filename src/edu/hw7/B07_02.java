package edu.hw7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class B07_02 {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("src/edu/hw7");
        Files.createDirectories(dir);
        Path input = dir.resolve("B07_02-input.bin");
        Path output = dir.resolve("B07_02-output.bin");
        generateToyFile(input);
        int targetAge = 6;
        List<Toy> toys = readToys(input);
        List<Toy> filtered = new ArrayList<>();
        for (Toy t : toys) if (t.isForAge(targetAge)) filtered.add(t);
        writeToys(output, filtered);
        System.out.println("Input:  " + input.toAbsolutePath());
        System.out.println("Output: " + output.toAbsolutePath());
        System.out.println("Age:    " + targetAge);
        System.out.println("All toys:");
        for (Toy t : toys) System.out.println(t);
        System.out.println("Filtered:");
        for (Toy t : filtered) System.out.println(t);
    }

    public static void generateToyFile(Path file) throws IOException {
        List<Toy> seed = List.of(
                new Toy("М'яч", 299, 3, 10),
                new Toy("Лялька", 549, 4, 9),
                new Toy("Конструктор", 1299, 6, 14),
                new Toy("Пазл 500", 399, 8, 99),
                new Toy("Плюшевий ведмедик", 459, 1, 6),
                new Toy("Настільна гра", 899, 7, 16)
        );
        writeToys(file, seed);
    }

    public static List<Toy> readToys(Path file) throws IOException {
        List<Toy> toys = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(file))) {
            while (true) {
                try {
                    String name = dis.readUTF();
                    int priceUAH = dis.readInt();
                    int minAge = dis.readInt();
                    int maxAge = dis.readInt();
                    toys.add(new Toy(name, priceUAH, minAge, maxAge));
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return toys;
    }

    public static void writeToys(Path file, List<Toy> toys) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(file))) {
            for (Toy t : toys) {
                dos.writeUTF(t.name);
                dos.writeInt(t.priceUAH);
                dos.writeInt(t.minAge);
                dos.writeInt(t.maxAge);
            }
        }
    }

    public static class Toy {
        public final String name;
        public final int priceUAH;
        public final int minAge;
        public final int maxAge;

        public Toy(String name, int priceUAH, int minAge, int maxAge) {
            this.name = name;
            this.priceUAH = priceUAH;
            this.minAge = minAge;
            this.maxAge = maxAge;
        }

        public boolean isForAge(int age) {
            return age >= minAge && age <= maxAge;
        }

        @Override
        public String toString() {
            return name + " | " + priceUAH + " грн | " + minAge + "-" + maxAge + " р.";
        }
    }
}
