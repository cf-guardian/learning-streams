package org.cfguardian.learningstreams;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public final class LearningStreamsTest {

	private static List<String> getSimpleList() {
		return Collections.singletonList("singleton element 1");
	}

	private static Flux<String> stringRangeFlux(String prefix, int n) {
		return Flux.range(0, n)
				.map(i -> prefix + i);
	}

	@Test
	public final void streamFromIterable() {
		assertEquals("singleton element 1", Flux.fromIterable(getSimpleList()).next().block());
	}

	@Test
	public final void publisherToStream() {
		Publisher<String> p = Flux.fromIterable(getSimpleList());

		assertEquals("singleton element 1", Flux.from(p).next().block());
	}

	@Test
	public final void tryPrinting() {

		stringRangeFlux("element ", 10)
				.map(x -> x)
				.subscribe(System.out::println);

	}
}
