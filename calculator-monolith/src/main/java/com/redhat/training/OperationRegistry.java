package com.redhat.training;

import com.redhat.training.operation.Add;
import com.redhat.training.operation.Substract;
import com.redhat.training.operation.Identity;
import com.redhat.training.operation.Multiply;
import com.redhat.training.operation.Operation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class OperationRegistry {
    @Inject
    Add add;

    @Inject
    Substract substract;

    @Inject
    Identity identity;

    @Inject
    Multiply multiply;

    private List<Operation> operations;

    @PostConstruct
    public void buildOperationList() {
        operations = List.of(substract, add, identity, multiply);
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

}
