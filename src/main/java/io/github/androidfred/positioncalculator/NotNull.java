package io.github.androidfred.positioncalculator;

public class NotNull<T> {

    private T verified;

    private NotNull() {
    }

    public NotNull(final T verify) {
        if (verify == null) {
            throw new IllegalArgumentException("must not be null");
        }
        this.verified = verify;
    }

    public final T provide() {
        return this.verified;
    }
}
