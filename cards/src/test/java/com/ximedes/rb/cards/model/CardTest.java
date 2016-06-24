package com.ximedes.rb.cards.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areEffectivelyImmutable;

/**
 * Created by mawi on 24/06/2016.
 */
public class CardTest {

    @Test
    public void checkMyClassIsImmutable() {
        assertInstancesOf(Card.class, areEffectivelyImmutable());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Card.class).verify();
    }
}