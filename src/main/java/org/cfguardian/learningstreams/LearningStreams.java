package org.cfguardian.learningstreams;

import reactor.rx.Stream;
import reactor.rx.Streams;

import java.util.Optional;

/**
 * Created by spowell on 11/12/2015.
 */
public class LearningStreams {

	public void simpleMethod(String[] args) {
		System.out.println("Hello");
	}

	public static <T> Stream<Optional<T>> optional(Stream<T> as) {
		return as.map(a-> Optional.of(a)).concatWith(Streams.just(Optional.empty()));
	}
}
