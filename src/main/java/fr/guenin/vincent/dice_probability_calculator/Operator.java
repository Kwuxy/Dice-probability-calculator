package fr.guenin.vincent.dice_probability_calculator;

public class Operator implements Cloneable {
    public String symbol;
    public Integer precedence;
    public Boolean leftAssociative;

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    protected Operator clone() {
        return new Operator();
    }
}
