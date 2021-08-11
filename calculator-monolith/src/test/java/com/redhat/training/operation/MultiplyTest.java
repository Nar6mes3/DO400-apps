package com.redhat.training.operation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@Tag("unit")
public class MultiplyTest {
    @Inject
    Multiply multiply;

    @Test
    public void simple_multiplaction() {
        assertEquals(multiply.apply("4*5"),20);
    }

    @Test
    public void unparseable_operation() {
        assertNull(multiply.apply("4-5"));
    }

}
