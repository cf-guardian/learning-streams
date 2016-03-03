package org.cfguardian.learningstreams;

import java.util.Optional;

import reactor.core.publisher.Flux;

public class LearningStreams {

	public void simpleMethod(String[] args) {
		System.out.println("Hello");
	}

	public static <T> Flux<Optional<T>> optional(Flux<T> as) {
		return as.map(a-> Optional.of(a)).concatWith(Flux.just(Optional.empty()));
	}
}
