package edu.austral.ingsis.math;

import java.io.IOException;

public interface Function {
    String print();

    boolean isComposite();

    void accept(VisitorFunction visitor) throws IOException;
}
