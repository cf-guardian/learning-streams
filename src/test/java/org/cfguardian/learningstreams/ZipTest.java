package org.cfguardian.learningstreams;

import static org.cfguardian.learningstreams.LearningStreams.*;
import static org.junit.Assert.*;

import org.junit.Test;
import reactor.rx.Stream;

public final class ZipTest {

    @Test
    public void compareStreams() {

        Stream<String> as = Stream.just("x", "y");
        Stream<String> bs = Stream.just("x");

        assertFalse(Stream.zip(optional(as), optional(bs), (a, b) -> a.equals(b))
                .reduce(true, (accum, match) -> accum && match)
                .get());
    }

    @Test
    public void simpleZip() {

        Stream<String> as = Stream.just("x");
        Stream<String> bs = Stream.just("y");

        assertTrue(Stream.zip(as, bs, (a, b) -> "x".equals(a) && "y".equals(b))
                .next().get());
    }
}
