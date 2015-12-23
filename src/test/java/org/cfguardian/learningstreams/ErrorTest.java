package org.cfguardian.learningstreams;

import org.junit.Test;
import reactor.rx.Stream;
import reactor.rx.Streams;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class ErrorTest {

    @Test
    public void mapErrorHandling() throws InterruptedException {

        Stream<String> f = Streams.fail(new IllegalArgumentException("bad"));

        final AtomicBoolean mapped = new AtomicBoolean(false);
        final AtomicBoolean error = new AtomicBoolean(false);

        f.map(s -> {
            mapped.set(true);
            return s;
        }).when(IllegalArgumentException.class, e -> error.set(true)).next().isError();

        assertFalse(mapped.get());
        assertTrue(error.get());
    } 
    
    @Test
    public void flatMapErrorHandling() throws InterruptedException {

        Stream<String> f = Streams.fail(new IllegalArgumentException("bad"));

        final AtomicBoolean mapped = new AtomicBoolean(false);
        final AtomicBoolean error = new AtomicBoolean(false);

        f.flatMap(s -> {
            mapped.set(true);
            return Streams.just(s);
        }).when(IllegalArgumentException.class, e -> error.set(true)).next().isError();

        assertFalse(mapped.get());
        assertTrue(error.get());
    }
}
