# learning-streams
A play area for learning about reactive streams.

## Content

The idea is that this repository will have some test programs, written in
Java(8), which exercise the streams implemented in `org.reactivestreams`.

The dependency looks like this in a Maven pom:
```xml
<dependency>
    <groupId>org.reactivestreams</groupId>
    <artifactId>reactive-streams</artifactId>
    <version>1.0.0</version>
</dependency>
```

and so this repo is built as a Maven repository, and has IntelliJ project
configuration files for consistency.

## Goals

We aim to understand streams from a users point of view, and eventually to be
able to explain it to others. Our inclination is to be precise and formal,
though it remains to be seen how far we can get that way.

## Execution notes

Note that to run this correctly from the command line (e.g. `mvn clean test`)
you need to have Java 8 installed and currently pointed to by `JAVA_HOME`.
