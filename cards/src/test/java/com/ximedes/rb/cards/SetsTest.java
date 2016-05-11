package com.ximedes.rb.cards;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Created by mawi on 07/04/2016.
 */
public class SetsTest {
    @Test
    public void quavaSets() {
        final ImmutableSet<Integer> a = ImmutableSet.of(1, 3, 6, 7);
        final ImmutableSet<Integer> b = ImmutableSet.of(4, 5, 6, 7);
        final ImmutableSet<Integer> c = ImmutableSet.of(2, 3, 5, 6);

        final Joiner joiner = Joiner.on(", ");

        System.out.println("i1 : " + joiner.join(Sets.difference(Sets.difference(a, c), b)));
        System.out.println("i2 : " + joiner.join(Sets.difference(Sets.difference(c, a), b)));
        System.out.println("i3 : " + joiner.join(Sets.difference(Sets.intersection(a, c), b)));
        System.out.println("i4 : " + joiner.join(Sets.difference(b, Sets.difference(a, c))));
        System.out.println("i5 : " + joiner.join(Sets.difference(b, Sets.difference(c, a))));
        System.out.println("i6 : " + joiner.join(Sets.difference(b, Sets.intersection(a,c))));
        System.out.println("i7 : " + joiner.join(Sets.intersection(Sets.difference(a, c), b)));
        System.out.println("i8 : " + joiner.join(Sets.intersection(Sets.difference(c,a), b)));
        System.out.println("i9 : " + joiner.join(Sets.intersection(Sets.intersection(a, c), b)));
    }
}
