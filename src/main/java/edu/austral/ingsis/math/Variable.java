package edu.austral.ingsis.math;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Variable implements Function {
    String name;
    Double value;
    Operand operand;

    public Variable(String name, Double value, Operand operand) throws IOException {
        if (operand != Operand.SQRT && operand != Operand.ABS)
            throw new IOException("The operand is not valid the variable");
        this.name = name;
        this.value = value;
        this.operand = operand;
    }

    public Variable(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(Double value) {
        this.value = value;
    }

    @Override
    public Double solve() {

        if (operand == null) return value;

        switch (operand) {
            case SQRT:
                return Math.sqrt(value);
            case ABS:
                return Math.abs(value);
        }
        return value;
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        if (operand != null && operand == Operand.ABS) {
            if (name != null) return builder.append("|").append(name).append("|").toString();
            return builder.append("|").append(value.intValue()).append("|").toString();
        } else if (operand != null && operand == Operand.SQRT) {
            if (name != null) return builder.append("sqrt(").append(name).append(")").toString();
            return builder.append("sqrt(").append(value.intValue()).append(")").toString();
        } else {
            if (name != null) return builder.append(name).toString();
            return builder.append(value.intValue()).toString();
        }

    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public List<String> getNames() {
        List<String> result = new ArrayList<>();
        if (name != null) result.add(name);
        return result;
    }

    @Override
    public void addNames(List<String> result) {
        if (name != null) result.add(name);
    }
}
