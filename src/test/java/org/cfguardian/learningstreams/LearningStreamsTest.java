package org.cfguardian.learningstreams;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import reactor.rx.Streams;

import static org.junit.Assert.assertEquals;

/**
 * Created by spowell on 11/12/2015.
 */
public final class LearningStreamsTest {
	
	@Test
	public final void streamFromIterable() {
		assertEquals("singleton element 1", Streams.from(getSimpleList()).next().get());
	}
	
	List<String> getSimpleList() {
		return Collections.singletonList("singleton element 1");
	}
}
