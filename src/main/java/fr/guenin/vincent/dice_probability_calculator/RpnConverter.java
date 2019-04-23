package fr.guenin.vincent.dice_probability_calculator;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

class RpnConverter {
    private Queue<String> output;
    private Stack<Operator> operators;
    private List<Operator> library;

    RpnConverter(List<Operator> library) {
        this.library = library;
    }

    private void init() {
        if(output == null) {
            output = new LinkedList<>();
        }else {
            output.clear();
        }

        if(operators == null) {
            operators = new Stack<>();
        }else{
            output.clear();
        }
    }

    String arithmeticExpressionToRpn(String arithmeticExpression) {
        this.init();

        String[] tokens = arithmeticExpression.split(" ");
        shuntingYard(tokens);

        return String.join(" ", Arrays.asList(output.toArray(new String[0])));
    }

    private void shuntingYard(String[] tokens) {
        for(String token : tokens) {
            if(NumberUtils.isCreatable(token)) { //token is a number
                arithmeticExpressionToRpnIsNumber(token);
            }

            for(Operator operator : library) { //token is an operator
                if(operator.symbol.equals(token)) {
                    arithmeticExpressionToRpnIsOperator(operator);
                    break;
                }
            }

            if(token.equals("(")) { //token is a left parenthesis
                arithmeticExpressionToRpnIsLeftParenthesis();
            }

            if(token.equals(")")) { //token is a right parenthesis
                arithmeticExpressionToRpnIsRightParenthesis();
            }
        }

        while(!operators.empty()) {
            output.add(operators.pop().symbol);
        }
    }

    private void arithmeticExpressionToRpnIsNumber(String token) {
        this.output.add(token);
    }

    private void arithmeticExpressionToRpnIsOperator(Operator operator) {
        while(true) {
            if(operators.empty()) {
                operators.push(operator);
                break;
            }

            if( !operators.peek().symbol.equals("(") && ((operators.peek().precedence > operator.precedence) ||
                    (operators.peek().precedence.equals(operator.precedence) && operators.peek().leftAssociative))) {
                output.add(operators.pop().symbol);
            }else{
                operators.add(operator);
                break;
            }
        }
    }

    private void arithmeticExpressionToRpnIsLeftParenthesis() {
        operators.push(new LeftParenthesis());
    }

    private void arithmeticExpressionToRpnIsRightParenthesis() {
        while(!operators.empty() && !operators.peek().symbol.equals("(")) {
            output.add(operators.pop().symbol);
        }
        operators.pop();
    }
}
