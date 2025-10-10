package edu.hw4;

public enum PreciousType {
    DIAMOND(10000.0, 5.0),
    SAPPHIRE(2500.0, 3.5),
    RUBY(3000.0, 3.8),
    EMERALD(3500.0, 3.9);

    private final double basePricePerCarat;
    private final double rarityScore;

    PreciousType(double basePricePerCarat, double rarityScore) {
        this.basePricePerCarat = basePricePerCarat;
        this.rarityScore = rarityScore;
    }

    public double getBasePricePerCarat() {
        return basePricePerCarat;
    }

    public double getRarityScore() {
        return rarityScore;
    }
}
