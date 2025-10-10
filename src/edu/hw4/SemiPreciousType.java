package edu.hw4;

public enum SemiPreciousType {
    AMETHYST(50.0, 1.1),
    TOPAZ(80.0, 1.2),
    GARNET(40.0, 1.0),
    AQUAMARINE(150.0, 1.4);

    private final double basePricePerCarat;
    private final double rarityScore;

    SemiPreciousType(double basePricePerCarat, double rarityScore) {
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
