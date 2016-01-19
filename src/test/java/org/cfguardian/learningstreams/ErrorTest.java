package org.cfguardian.learningstreams;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import reactor.rx.Stream;

public final class ErrorTest {

	@Test
	public void mapErrorHandling() throws InterruptedException {

		final AtomicBoolean mapped = new AtomicBoolean(false);
		final AtomicBoolean error = new AtomicBoolean(false);

		Stream.<String, IllegalArgumentException>fail(new IllegalArgumentException("bad"))
				.map(s -> {
					mapped.set(true);
					return s;
				})
				.doOnError(t -> error.set(true))
				.onErrorReturn("dummy")
				.next()
				.get();

		assertFalse(mapped.get());
		assertTrue(error.get());
	}

	@Test
	public void flatMapErrorHandling() throws InterruptedException {

		final AtomicBoolean mapped = new AtomicBoolean(false);
		final AtomicBoolean error = new AtomicBoolean(false);

		Stream.<String, IllegalArgumentException>fail(new IllegalArgumentException("bad"))
				.flatMap(s -> {
					mapped.set(true);
					return Stream.just(s);
				})
				.doOnError(t -> error.set(true))
				.onErrorReturn("dummy")
				.next()
				.get();

		assertFalse(mapped.get());
		assertTrue(error.get());
	}
}
