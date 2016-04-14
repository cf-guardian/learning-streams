package org.cfguardian.learningstreams;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class ErrorTest {

	@Test
	public void mapErrorHandling() throws InterruptedException {

		final AtomicBoolean mapped = new AtomicBoolean(false);
		final AtomicBoolean error = new AtomicBoolean(false);

		Flux.<String>error(new IllegalArgumentException("bad"))
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

		Flux.<String>error(new IllegalArgumentException("bad"))
				.flatMap(s -> {
					mapped.set(true);
					return Flux.just(s);
				})
				.doOnError(t -> error.set(true))
				.onErrorReturn("dummy")
				.next()
				.get();

		assertFalse(mapped.get());
		assertTrue(error.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void afterBehaviourWithUpstreamError() {

		Mono.error(new IllegalArgumentException("bad"))
				.after(() -> Mono.empty())
				.get();
	}
}
