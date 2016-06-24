package com.ximedes.rb.cards.api.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.mutabilitydetector.unittesting.AllowedReason;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areEffectivelyImmutable;

/**
 * Created by mawi on 24/06/2016.
 */
public class ApiCardTest {

    @Test
    public void checkMyClassIsImmutable() {
        assertInstancesOf(ApiCard.class, areEffectivelyImmutable(),
                AllowedReason.assumingFields("image").areNotModifiedAndDoNotEscape());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ApiCard.class).verify();
    }
}