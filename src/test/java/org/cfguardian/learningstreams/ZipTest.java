package org.cfguardian.learningstreams;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import reactor.rx.Stream;
import reactor.rx.Streams;

public final class ZipTest {

	@Test
	public void compareStreams() {

		Stream<Optional<String>> as = Streams.just("x", "y")
				.map(a-> Optional.of(a)).concatWith(Streams.just(Optional.empty()));
		Stream<Optional<String>> bs = Streams.just("x")
				.map(a-> Optional.of(a)).concatWith(Streams.just(Optional.empty()));

		assertFalse(Streams.zip(as, bs, (a, b) -> a.equals(b))
				.reduce(true, (accum, match) -> accum && match)
				.next().get());
	}
}
