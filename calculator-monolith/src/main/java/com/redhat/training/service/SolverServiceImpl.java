package com.redhat.training.service;

import com.redhat.training.OperationRegistry;
import com.redhat.training.operation.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class SolverServiceImpl implements SolverService {

    private static final Logger LOG = LoggerFactory.getLogger(SolverService.class);

    @Inject
    OperationRegistry operationRegistry;

    @Override
    public Float solve(@PathParam("equation") final String equation) {
        LOG.info("Solving '{}'", equation);

        for (Operation operation : this.operationRegistry.getOperations()) {
            Float result = operation.apply(equation);
            if (result != null) {
                LOG.info("Solved '{} = {}'", equation, result);
                return result;
            }
        }

        throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST).entity("Unable to parse: " + equation).build());
    }
}
