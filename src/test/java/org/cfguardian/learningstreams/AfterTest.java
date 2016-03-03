package org.cfguardian.learningstreams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class AfterTest {

    @Test
    public void afterTest() {

        assertEquals("after-test-string",
            Mono
                .just("test-string")
                .doOnSuccess(str -> System.out.println("Success: stream.after-1 " + str))
                .doOnError(x -> System.out.println("Error: stream.after-1 " + x))
                .after(
                    () -> Mono
                        .just("after-test-string")
                        .doOnSuccess(str -> System.out.println("Success: stream.after-2 " + str))
                        .doOnError(x -> System.out.println("Error: stream.after-2 " + x))
                )
                .doOnSuccess(str -> System.out.println("Success: stream.after-3 " + str))
                .doOnError(x -> System.out.println("Error: stream.after-3 " + x))
                .get());
    }

    @Test
    public void afterErrorTest() {

        try {
            List<String> list =
                Mono
                    .error(new IllegalArgumentException("test-exception"))
                    .doOnSuccess(str -> System.out.println("Success: stream.after-1 " + str))
                    .doOnError(x -> System.out.println("Error: stream.after-1 " + x))
                    .after(
                        () -> Mono
                            .just("after-test-string")
                            .doOnSuccess(str -> System.out.println("Success: stream.after-2 " + str))
                            .doOnError(x -> System.out.println("Error: stream.after-2 " + x))
                    )
                    .doOnSuccess(str -> System.out.println("Success: stream.after-3 " + str))
                    .doOnError(x -> System.out.println("Error: stream.after-3 " + x))
                    .as(Flux::from)
                    .toList().get();
            System.out.println(list);
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException iae) {
            // expected: ignore this
        }
        catch (Exception e) {
            fail("Should throw IllegalArgumentException instead of " + e);
        }

    }

    @Test
    public void afterTestEmpty() {

        assertEquals("after-test-string",
            Mono
                .empty()
                .doOnSuccess(str -> System.out.println("Success: stream.after-1 " + str))
                .doOnError(x -> System.out.println("Error: stream.after-1 " + x))
                .after(
                    () -> Mono
                        .just("after-test-string")
                        .doOnSuccess(str -> System.out.println("Success: stream.after-2 " + str))
                        .doOnError(x -> System.out.println("Error: stream.after-2 " + x))
                )
                .doOnSuccess(str -> System.out.println("Success: stream.after-3 " + str))
                .doOnError(x -> System.out.println("Error: stream.after-3 " + x))
                .get());
    }

}
