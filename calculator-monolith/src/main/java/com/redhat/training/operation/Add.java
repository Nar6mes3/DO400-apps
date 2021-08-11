package com.redhat.training.operation;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.BinaryOperator;

@ApplicationScoped
public final class Add extends BinaryOperation {

    private static final String REGEX = "(.+)\\+(.+)";
    private static final BinaryOperator<Float> OPERATOR = (lhs, rhs) -> lhs + rhs;

    public Add() {
        super(REGEX, OPERATOR);
    }
}
