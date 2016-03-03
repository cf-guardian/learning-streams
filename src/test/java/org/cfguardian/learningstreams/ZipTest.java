package org.cfguardian.learningstreams;

import static org.cfguardian.learningstreams.LearningStreams.optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import reactor.core.publisher.Flux;

public final class ZipTest {

    @Test
    public void compareFluxs() {

        Flux<String> as = Flux.just("x", "y");
        Flux<String> bs = Flux.just("x");

        assertFalse(Flux
            .zip(optional(as), optional(bs), (a, b) -> a.equals(b))
            .reduce(true, (accum, match) -> accum && match)
            .get());
    }

    @Test
    public void simpleZip() {

        Flux<String> as = Flux.just("x");
        Flux<String> bs = Flux.just("y", "z");

        assertTrue(Flux
            .zip(as, bs, (a, b) -> "x".equals(a) && "y".equals(b))
            .next()
            .get());
    }
}
