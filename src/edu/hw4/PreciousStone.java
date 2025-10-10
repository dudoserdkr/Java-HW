package edu.hw4;

public class PreciousStone extends Stone {
    private final PreciousType type;

    public PreciousStone(PreciousType type, double weightCarat, double transparency) {
        super(type.name(), weightCarat, transparency, StoneCategory.PRECIOUS);
        this.type = type;
    }

    public PreciousType getType() {
        return type;
    }

    @Override
    public double getCost() {
        double base = type.getBasePricePerCarat();
        double rarity = type.getRarityScore();
        double transparencyFactor = 0.6 + transparency;
        double typeMultiplier = 1.5;
        return base * weightCarat * rarity * transparencyFactor * typeMultiplier;
    }
}
