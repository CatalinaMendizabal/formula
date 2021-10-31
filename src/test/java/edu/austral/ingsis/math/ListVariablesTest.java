package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListVariablesTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldListVariablesFunction1() throws IOException {
        Function function = new Expression(new Variable(1d), Operand.ADD, new Variable(6d));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, empty());
    }


    /**
     * Case 12 / div
     */
    @Test
    public void shouldListVariablesFunction2() throws IOException {
        Function function = new Expression(new Variable(12d), Operand.DIVIDE, new Variable("div", 4d));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, containsInAnyOrder("div"));
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    public void shouldListVariablesFunction3() throws IOException {
        Function function = new Expression(new Expression(new Variable(9d), Operand.DIVIDE, new Variable("x", 3d)), Operand.MULTIPLY, new Variable("y", 4d));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;

        assertThat(result, containsInAnyOrder("x", "y"));
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    public void shouldListVariablesFunction4() throws IOException {
        Function function = new Expression(new Expression(new Variable(27d), Operand.DIVIDE, new Variable("a", 9d)), Operand.POW, new Variable("b", 3d));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;

        assertThat(result, containsInAnyOrder("a", "b"));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldListVariablesFunction5() throws IOException {
        Function function = new Expression(new Variable("z", 36d), Operand.POW,new Expression(new Variable(1d), Operand.DIVIDE, new Variable( 2d)));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, containsInAnyOrder("z"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction6() throws IOException {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABS), Operand.SUBTRACT, new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction7() throws IOException {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABS), Operand.SUBTRACT, new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldListVariablesFunction8() throws IOException {
        Function function = new Expression(new Expression(new Variable(5d), Operand.SUBTRACT, new Variable("i", 2d)), Operand.MULTIPLY, new Variable(8d));
        VariableVisitor varVisitor = new VariableVisitor();
        function.accept(varVisitor);

        final List<String> result = varVisitor.result;
        assertThat(result, containsInAnyOrder("i"));
    }
}
