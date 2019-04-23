package fr.guenin.vincent.dice_probability_calculator;

public class Plus extends Operator {
    public Plus() {
        this.symbol = "+";
        this.precedence = 2;
        this.leftAssociative = true;
    }
}
