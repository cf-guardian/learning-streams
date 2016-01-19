package org.cfguardian.learningstreams;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import reactor.Mono;
import reactor.rx.Promise;

public class PromiseTest {

	@Test
	public void promiseOnError() {
		final AtomicBoolean outOfMono = new AtomicBoolean(false);
		final AtomicBoolean outOfPromise = new AtomicBoolean(false);

		Mono.error(new IllegalStateException("fail"))
				.doOnError(t -> outOfMono.set(true))
				.as(Promise::from)
				.doOnError(t -> outOfPromise.set(true))
				.otherwiseJust("dummy")
				.get();

		assertTrue(outOfMono.get());
		assertTrue(outOfPromise.get());
		;
	}

}
