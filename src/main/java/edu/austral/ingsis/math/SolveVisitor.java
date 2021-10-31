package edu.austral.ingsis.math;

import java.io.IOException;

public class SolveVisitor implements VisitorFunction {
    Double result;

    @Override
    public void visitVariable(Variable variable) {
        double value = variable.getValue();
        Operand operand = variable.getOperand();

        if (operand == null) {
            result = value;
            return;
        }
        switch (operand) {
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
        double result = 0;

        Function leftFunction = expression.getLeftFunction();
        Function rightFunction = expression.getRightFunction();
        Operand firstOperand = expression.getFirstOperand();
        Operand secondOperand = expression.getSecondOperand();

        switch (firstOperand) {
            case ADD:
                result = calculateResult(leftFunction) + calculateResult(rightFunction);
                break;
            case SUBTRACT:
                result = calculateResult(leftFunction) - calculateResult(rightFunction);
                break;
            case MULTIPLY:
                result = calculateResult(leftFunction) * calculateResult(rightFunction);
                break;
            case DIVIDE:
                result = calculateResult(leftFunction) / calculateResult(rightFunction);
                break;
            case POW:
                result = Math.pow(calculateResult(leftFunction), calculateResult(rightFunction));
                break;

        }

        if (secondOperand != null) {
            switch (secondOperand) {
                case SQRT:
                    result = Math.sqrt(result);
                    break;
                case ABS:
                    result = Math.abs(result);
                    break;
            }
        }

        this.result = result;
    }

    private double calculateResult(Function function) throws IOException {
        function.acceptVisitor(this);
        return result;
    }
}
