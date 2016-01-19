package org.cfguardian.learningstreams;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.rx.Stream;

public final class LearningStreamsTest {

	@Test
	public final void streamFromIterable() {
		assertEquals("singleton element 1", Stream.fromIterable(getSimpleList()).next().get());
	}

	@Test
	public final void publisherToStream() {
		Publisher<String> p = Stream.fromIterable(getSimpleList());

		assertEquals("singleton element 1", Stream.from(p).next().get());
	}

	List<String> getSimpleList() {
		return Collections.singletonList("singleton element 1");
	}
}
