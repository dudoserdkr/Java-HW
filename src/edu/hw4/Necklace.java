package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Necklace {
    private final List<Stone> stones = new ArrayList<>();

    public void addStone(Stone stone) {
        stones.add(stone);
    }

    public List<Stone> getStones() {
        return new ArrayList<>(stones);
    }

    public double totalWeightCarat() {
        return stones.stream().mapToDouble(Stone::getWeightCarat).sum();
    }

    public double totalCost() {
        return stones.stream().mapToDouble(Stone::getCost).sum();
    }

    public List<Stone> sortedByValueDescending() {
        return stones.stream()
                .sorted(Comparator.comparingDouble(Stone::getCost).reversed())
                .collect(Collectors.toList());
    }

    public List<Stone> findByTransparencyRange(double minTransparency, double maxTransparency) {
        return stones.stream()
                .filter(s -> s.getTransparency() >= minTransparency && s.getTransparency() <= maxTransparency)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Necklace:\n");
        for (Stone s : stones) {
            sb.append("  ").append(s).append("\n");
        }
        sb.append(String.format("Total weight: %.3f ct, Total cost: %.2f", totalWeightCarat(), totalCost()));
        return sb.toString();
    }
}
