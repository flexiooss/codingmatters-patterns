package org.codingmatters.patterns.firstof;

import java.util.LinkedList;
import java.util.Optional;

public class FirstOf<R, E extends Exception> {

    private LinkedList<CheckedSupplier<R,E>> suppliers = new LinkedList<>();

    public FirstOf(CheckedSupplier<R,E> firstSupplier) {
        this.suppliers.add(firstSupplier);
    }

    public FirstOf<R,E> orElse(CheckedSupplier<R,E> supplier) {
        this.suppliers.add(supplier);
        return this;
    }

    public Optional<R> get() throws E {
        for (CheckedSupplier<R, E> supplier : this.suppliers) {
            Optional<R> r = supplier.get();
            if(r.isPresent()) {
                return r;
            }
        }
        return Optional.empty();
    }
}
