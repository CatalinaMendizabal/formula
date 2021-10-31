package edu.austral.ingsis.math;

import java.util.ArrayList;
import java.util.List;

public class Expression implements Function {
    Function leftFunction;
    Function rightFunction;
    List<Operand> operands;

    public Expression(Function leftFunction, List<Operand> operands, Function rightFunction) {
        this.leftFunction = leftFunction;
        this.rightFunction = rightFunction;
        this.operands = operands;
    }

    @Override
    public Double solve() {
        double result = 0;

        for (Operand operand : operands) {
            switch (operand) {
                case ADD: {
                    result = leftFunction.solve() + rightFunction.solve();
                    break;
                }
                case SUBTRACT: {
                    result = leftFunction.solve() - rightFunction.solve();
                    break;
                }
                case MULTIPLY: {
                    result = leftFunction.solve() * rightFunction.solve();
                    break;
                }
                case DIVIDE: {
                    result = leftFunction.solve() / rightFunction.solve();
                    break;
                }
                case POW: {
                    result = Math.pow(leftFunction.solve(), rightFunction.solve());
                    break;
                }
                case SQRT: {
                    result = Math.sqrt(result);
                    break;
                }
                case ABS: {
                    result = Math.abs(result);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String print() {

        StringBuilder builder = new StringBuilder();

        if (leftFunction.isComposite()) builder.append("(");
        builder.append(leftFunction.print());
        if (leftFunction.isComposite()) builder.append(")");

        for (Operand operand : operands) {
            switch (operand) {
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
        }

        return builder.toString();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public List<String> getNames() {
        List<String> result = new ArrayList<>();
        leftFunction.addNames(result);
        rightFunction.addNames(result);
        return result;
    }

    @Override
    public void addNames(List<String> result) {
        leftFunction.addNames(result);
        rightFunction.addNames(result);
    }
}
