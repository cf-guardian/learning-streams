package org.cfguardian.learningstreams;

import org.junit.Test;
import reactor.rx.Stream;
import reactor.rx.Streams;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.cfguardian.learningstreams.LearningStreams.optional;
import static org.junit.Assert.*;

public final class ZipTest {

    @Test
    public void compareStreams() {

        Stream<String> as = Streams.just("x", "y");
        Stream<String> bs = Streams.just("x");

        assertFalse(Streams.zip(optional(as), optional(bs), (a, b) -> a.equals(b))
                .reduce(true, (accum, match) -> accum && match)
                .next().get());
    }

    @Test
    public void zipOfNull() {

        Stream<String> as = Streams.just("x");
        Stream<String> bs = Streams.<String>just(null);
        final AtomicBoolean ran = new AtomicBoolean(false);

        // This should fail rather than deliver a null value.
        assertNull(Streams.zip(as, bs, (a, b) -> {
            ran.set(true);
            return "y";
        }).next().get());

        assertFalse(ran.get());
    }

    @Test
    public void simpleZip() {

        Stream<String> as = Streams.just("x");
        Stream<String> bs = Streams.just("y");

        assertTrue(Streams.zip(as, bs, (a, b) -> "x".equals(a) && "y".equals(b))
                .next().get());
    }
}
