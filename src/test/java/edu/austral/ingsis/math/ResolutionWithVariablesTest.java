package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionWithVariablesTest {

    /**
     * Case 1 + x where x = 3
     */
    @Test
    public void shouldResolveFunction1() throws IOException {
        Function function = new Expression(new Variable(1d), Operand.ADD, new Variable("x", 3d));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(4d));
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    public void shouldResolveFunction2() throws IOException {
        Function function = new Expression(new Variable(12d), Operand.DIVIDE, new Variable("div", 4d));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(3d));
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    public void shouldResolveFunction3() throws IOException {
        Function function = new Expression(new Expression(new Variable(9d), Operand.DIVIDE, new Variable("x", 3d)), Operand.MULTIPLY, new Variable("y", 4d));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(12d));
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    public void shouldResolveFunction4() throws IOException {
        Function function = new Expression(new Expression(new Variable(27d), Operand.DIVIDE, new Variable("a", 9d)), Operand.POW, new Variable("b", 3d));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(27d));
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    public void shouldResolveFunction5() throws IOException {
        Function function = new Expression(new Variable("z", 36d), Operand.POW,new Expression(new Variable(1d), Operand.DIVIDE, new Variable( 2d)));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(6d));
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    public void shouldResolveFunction6() throws IOException {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABS), Operand.SUBTRACT, new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(0d));
    }

    /**
     * Case |value| - 8 where value = -8
     */
    @Test
    public void shouldResolveFunction7() throws IOException {
        Function function = null;
        try {
            function = new Expression(new Variable("value", -8d, Operand.ABS), Operand.SUBTRACT, new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(0d));
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    public void shouldResolveFunction8() throws IOException {
        Function function = new Expression(new Expression(new Variable(5d), Operand.SUBTRACT, new Variable("i", 2d)), Operand.MULTIPLY, new Variable(8d));
        SolveVisitor solver = new SolveVisitor();
        function.accept(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(24d));
    }
}
