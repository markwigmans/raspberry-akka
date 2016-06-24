package com.ximedes.rb.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

/**
 * Created by mawi on 24/06/2016.
 */
public class ValueCommandTest {

    @Test
    public void checkMyClassIsImmutable() {
        assertInstancesOf(ValueCommand.class, areImmutable());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ValueCommand.class)
                .withRedefinedSuperclass()
                .verify();
    }
}