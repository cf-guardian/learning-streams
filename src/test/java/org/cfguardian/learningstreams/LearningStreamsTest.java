package org.cfguardian.learningstreams;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public final class LearningStreamsTest {

    @Test
    public final void streamFromIterable() {
        assertEquals("singleton element 1", Flux.fromIterable(getSimpleList()).next().get());
    }

    @Test
    public final void publisherToStream() {
        Publisher<String> p = Flux.fromIterable(getSimpleList());

        assertEquals("singleton element 1", Flux.from(p).next().get());
    }

    List<String> getSimpleList() {
        return Collections.singletonList("singleton element 1");
    }
}
