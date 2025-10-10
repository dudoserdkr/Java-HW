package edu.hw4;

public abstract class Stone {
    protected final String name;
    protected final double weightCarat;
    protected final double transparency;
    protected final StoneCategory category;

    public Stone(String name, double weightCarat, double transparency, StoneCategory category) {
        if (transparency < 0.0 || transparency > 1.0) {
            throw new IllegalArgumentException("transparency must be between 0.0 and 1.0");
        }
        this.name = name;
        this.weightCarat = weightCarat;
        this.transparency = transparency;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getWeightCarat() {
        return weightCarat;
    }

    public double getTransparency() {
        return transparency;
    }

    public StoneCategory getCategory() {
        return category;
    }

    public abstract double getCost();

    @Override
    public String toString() {
        return String.format("%s (category=%s, weight=%.3f ct, transparency=%.2f, cost=%.2f)",
                name, category, weightCarat, transparency, getCost());
    }
}
