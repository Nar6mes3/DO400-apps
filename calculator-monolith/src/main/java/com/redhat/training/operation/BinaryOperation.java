package com.redhat.training.operation;

import com.redhat.training.service.SolverService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BinaryOperation implements Operation {

    private final String regex;
    private final BinaryOperator<Float> operator;

    public BinaryOperation(final String regexParam, final BinaryOperator<Float> operatorParam) {
        super();
        this.regex = regexParam;
        this.operator = operatorParam;
    }

    @Override
    public Float apply(final String equation) {
        return solveGroups(equation).stream().reduce(this.operator).orElse(null);
    }

    private List<Float> solveGroups(final String equation) {
        Matcher matcher = Pattern.compile(this.regex).matcher(equation);
        if (matcher.matches()) {
            List<Float> result = new ArrayList<>(matcher.groupCount());
            for (int groupNum = 1; groupNum <= matcher.groupCount(); groupNum++) {
                result.add(solve(matcher.group(groupNum)));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    @Inject
    SolverService solverService;

    private Float solve(final String equation) {
        return solverService.solve(equation);
    }

}
