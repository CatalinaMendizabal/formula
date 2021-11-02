package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldResolveSimpleFunction1() throws IOException {
        Function function = new Expression(new Variable(1d), Operand.ADD, new Variable(6d));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(7d));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldResolveSimpleFunction2() throws IOException {
        Function function = new Expression(new Variable(12d), Operand.DIVIDE, new Variable(2d));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(6d));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldResolveSimpleFunction3() throws IOException {
        Function function = new Expression(new Expression(new Variable(9d), Operand.DIVIDE, new Variable(2d)), Operand.MULTIPLY, new Variable(3d));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(13.5d));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldResolveSimpleFunction4() throws IOException {
        Function function = new Expression(new Expression(new Variable(27d), Operand.DIVIDE, new Variable(6d)), Operand.POW, new Variable(2d));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(20.25d));
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    public void shouldResolveSimpleFunction5() throws IOException {
        Function function = new Expression(new Variable(36d), Operand.POW, new Expression(new Variable(1d), Operand.DIVIDE, new Variable(2d)));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(6d));
    }

    /**
     * Case |136|
     */
    @Test
    public void shouldResolveSimpleFunction6() throws IOException {
        Function function = null;
        try {
            function = new Variable("a", 136d, Operand.ABS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(136d));
    }

    /**
     * Case |-136|
     */
    @Test
    public void shouldResolveSimpleFunction7() throws IOException {
        Function function = null;
        try {
            function = new Variable("a", -136d, Operand.ABS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;
        assertThat(result, equalTo(136d));
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    public void shouldResolveSimpleFunction8() throws IOException {
        Function function = new Expression(new Expression(new Variable(5d), Operand.SUBTRACT, new Variable(5d)), Operand.MULTIPLY, new Variable(8d));
        SolveVisitor solver = new SolveVisitor();
        function.acceptVisitor(solver);

        final Double result = solver.result;

        assertThat(result, equalTo(0d));
    }
}
