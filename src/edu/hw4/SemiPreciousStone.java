package edu.hw4;

public class SemiPreciousStone extends Stone {
    private final SemiPreciousType type;

    public SemiPreciousStone(SemiPreciousType type, double weightCarat, double transparency) {
        super(type.name(), weightCarat, transparency, StoneCategory.SEMI_PRECIOUS);
        this.type = type;
    }

    public SemiPreciousType getType() {
        return type;
    }

    @Override
    public double getCost() {
        double base = type.getBasePricePerCarat();
        double rarity = type.getRarityScore();
        double transparencyFactor = 0.5 + 0.9 * transparency;
        double typeMultiplier = 1.0;
        return base * weightCarat * rarity * transparencyFactor * typeMultiplier;
    }
}
