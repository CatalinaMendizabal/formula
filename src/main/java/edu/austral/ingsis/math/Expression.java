package edu.austral.ingsis.math;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Expression implements Function {
    Function leftFunction;
    Function rightFunction;
    Operand firstOperand;
    Operand secondOperand;

    public Expression(Function leftFunction, Operand operand, Function rightFunction) {
        this.leftFunction = leftFunction;
        this.rightFunction = rightFunction;
        this.firstOperand = operand;
    }

    public Expression(Function leftFunction, Function rightFunction, Operand firstOperand, Operand secondOperand) {
        this.leftFunction = leftFunction;
        this.rightFunction = rightFunction;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public Function getLeftFunction() { return leftFunction; }

    public Function getRightFunction() { return rightFunction; }

    public Operand getFirstOperand() { return firstOperand; }

    public Operand getSecondOperand() { return secondOperand; }

    @Override
    public String print() {

        StringBuilder builder = new StringBuilder();

        if (secondOperand != null) {
            switch (secondOperand) {
                case ABS:
                    builder.append("|");
                    break;
                case SQRT:
                    builder.append("sqrt(");
                    break;
            }
        }

        if (leftFunction.isComposite()) builder.append("(");
        builder.append(leftFunction.print());
        if (leftFunction.isComposite()) builder.append(")");

        switch (firstOperand) {
            case ADD: {
                builder.append(" ").append("+").append(" ");
                break;
            }
            case SUBTRACT: {
                builder.append(" ").append("-").append(" ");
                break;
            }
            case MULTIPLY: {
                builder.append(" ").append("*").append(" ");
                break;
            }
            case DIVIDE: {
                builder.append(" ").append("/").append(" ");
                break;
            }
            case POW: {
                builder.append(" ").append("^").append(" ");
                break;
            }
            case SQRT: {
                builder.append(" ").append(")").append(" ");
                break;
            }
            case ABS: {
                builder.append(" ").append("|").append(" ");
                break;
            }
        }

        if (rightFunction.isComposite()) builder.append('(');
        builder.append(rightFunction.print());
        if (rightFunction.isComposite()) builder.append(')');

        return builder.toString();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void accept(VisitorFunction visitor) throws IOException {
        visitor.visitExpression(this);
    }
}
