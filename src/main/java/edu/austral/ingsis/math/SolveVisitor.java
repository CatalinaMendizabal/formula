package edu.austral.ingsis.math;

import java.io.IOException;

public class SolveVisitor implements VisitorFunction {
    Double result;

    @Override
    public void visitVariable(Variable variable) {
        double value = variable.getValue();
        Operand altOperand = variable.getOperand();

        if (altOperand == null) {
            result = value;
            return;
        }

        switch (altOperand) {
            case ABS:
                result = Math.abs(value);
                return;
            case SQRT:
                result = Math.sqrt(value);
                return;
        }

        result = value;
    }

    @Override
    public void visitExpression(Expression expression) throws IOException {
        Operand operand = expression.getFirstOperand();
        Operand altOperand = expression.getSecondOperand();
        Function left = expression.getLeftFunction();
        Function right = expression.getRightFunction();

        double result = 0;

        switch (operand) {
            case ADD:
                result = getResult(left) + getResult(right);
                break;
            case SUBTRACT:
                result = getResult(left) - getResult(right);
                break;
            case MULTIPLY:
                result = getResult(left) * getResult(right);
                break;
            case DIVIDE:
                result = getResult(left) / getResult(right);
                break;
            case POW:
                result = Math.pow(getResult(left), getResult(right));
                break;

        }

        if(altOperand == null) {
            this.result = result;
            return;
        }

        switch (altOperand) {
            case SQRT:
                result = Math.sqrt(result);
                break;
            case ABS:
                result = Math.abs(result);
                break;
        }

        this.result = result;
    }

    private double getResult(Function function) throws IOException {
        function.accept(this);
        return result;
    }
}
