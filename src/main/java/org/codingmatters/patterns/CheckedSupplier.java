package org.codingmatters.patterns;

import java.util.Optional;

@FunctionalInterface
public interface CheckedSupplier<R, E extends Exception> {
    Optional<R> get() throws E;
}
