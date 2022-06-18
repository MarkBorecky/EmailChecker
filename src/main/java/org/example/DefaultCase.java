package org.example;

import java.util.function.Supplier;

public class DefaultCase<T> extends Case<T> {
    public DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
        super(booleanSupplier, resultSupplier);
    }
}
