package edu.hw4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Necklace necklace = new Necklace();

        necklace.addStone(new PreciousStone(PreciousType.DIAMOND, 1.20, 0.95));
        necklace.addStone(new PreciousStone(PreciousType.RUBY, 0.90, 0.85));
        necklace.addStone(new PreciousStone(PreciousType.EMERALD, 1.10, 0.70));
        necklace.addStone(new SemiPreciousStone(SemiPreciousType.AMETHYST, 5.00, 0.60));
        necklace.addStone(new SemiPreciousStone(SemiPreciousType.AQUAMARINE, 3.00, 0.80));
        necklace.addStone(new SemiPreciousStone(SemiPreciousType.TOPAZ, 2.50, 0.40));

        System.out.println(necklace);
        System.out.println();

        System.out.println("Sorted by value (descending):");
        List<Stone> sorted = necklace.sortedByValueDescending();
        sorted.forEach(s -> System.out.println("  " + s));
        System.out.println();

        double minT = 0.6, maxT = 0.9;
        System.out.printf("Stones with transparency in [%.2f, %.2f]:%n", minT, maxT);
        List<Stone> filtered = necklace.findByTransparencyRange(minT, maxT);
        filtered.forEach(s -> System.out.println("  " + s));
        System.out.println();

        System.out.printf("Total weight (ct): %.3f%n", necklace.totalWeightCarat());
        System.out.printf("Total cost: %.2f%n", necklace.totalCost());
    }
}
