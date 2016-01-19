package org.cfguardian.learningstreams;

import java.util.Optional;

import reactor.rx.Stream;

public class LearningStreams {

	public void simpleMethod(String[] args) {
		System.out.println("Hello");
	}

	public static <T> Stream<Optional<T>> optional(Stream<T> as) {
		return as.map(a-> Optional.of(a)).concatWith(Stream.just(Optional.empty()));
	}
}
