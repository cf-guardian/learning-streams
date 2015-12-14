package org.cfguardian.learningstreams;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.rx.Streams;
import reactor.rx.stream.DecoratingStream;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by spowell on 11/12/2015.
 */
public final class LearningStreamsTest {

    @Test
    public final void streamFromIterable() {
        assertEquals("singleton element 1", Streams.from(getSimpleList()).next().get());
    }

    @Test
    public final void publisherToStream() {
        Publisher<String> p = Streams.from(getSimpleList());

        assertEquals("singleton element 1", new DecoratingStream<>(p).next().get());
    }

    @Test
    public final void wrapPublisher() {
        Publisher<String> p = Streams.from(getSimpleList());

        assertEquals("singleton element 1", Streams.wrap(p).next().get());
    }

    List<String> getSimpleList() {
        return Collections.singletonList("singleton element 1");
    }
}
