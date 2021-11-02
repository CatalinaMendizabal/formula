package edu.austral.ingsis.math;

import java.io.IOException;

public interface VisitorFunction {

    void visitVariable(Variable variable) throws IOException;

    void visitExpression(Expression expression) throws IOException;
}
