package edu.austral.ingsis.math;

import java.io.IOException;

public interface Function {
    String print();

    boolean isComposite();

    void acceptVisitor(VisitorFunction visitor) throws IOException;
}
