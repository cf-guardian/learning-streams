package org.cfguardian.learningstreams;

import static org.cfguardian.learningstreams.LearningStreams.optional;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import reactor.rx.Stream;
import reactor.rx.Streams;

public final class ZipTest {

	@Test
	public void compareStreams() {

		Stream<String> as = Streams.just("x", "y");
		Stream<String> bs = Streams.just("x");

		assertFalse(Streams.zip(optional(as), optional(bs), (a, b) -> a.equals(b))
				.reduce(true, (accum, match) -> accum && match)
				.next().get());
	}
}
