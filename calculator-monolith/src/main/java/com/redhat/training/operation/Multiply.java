package com.redhat.training.operation;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.BinaryOperator;

@ApplicationScoped
public class Multiply extends BinaryOperation {

    private static final String REGEX = "(.+)\\*(.+)";
    private static final BinaryOperator<Float> OPERATOR = (lhs, rhs) -> lhs * rhs;

    public Multiply() {
        super(REGEX, OPERATOR);
    }

}
