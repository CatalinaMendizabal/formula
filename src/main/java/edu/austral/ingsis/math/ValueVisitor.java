package edu.austral.ingsis.math;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValueVisitor implements VisitorFunction {

    List<String> result = new ArrayList<>();

    @Override
    public void visitVariable(Variable variable) {
        String name = variable.getName();
        if (name != null) result.add(name);
    }

    @Override
    public void visitExpression(Expression expression) throws IOException {
        expression.getLeftFunction().acceptVisitor(this);
        expression.getRightFunction().acceptVisitor(this);
    }
}
