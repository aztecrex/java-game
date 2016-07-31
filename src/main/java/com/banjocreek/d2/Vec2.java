package com.banjocreek.d2;

public interface Vec2 {

    static final ImmutableVec2 east = new ImmutableVec2(1d, 0d);
    static final ImmutableVec2 north = new ImmutableVec2(0d, 1d);
    static final ImmutableVec2 south = new ImmutableVec2(0d, -1d);
    static final ImmutableVec2 west = new ImmutableVec2(-1d, 0d);

    static ImmutableVec2 immutable() {
        return new ImmutableVec2(0d, 0d);
    }

    static ImmutableVec2 immutable(final double x, final double y) {
        return new ImmutableVec2(x, y);
    }

    static MutableVec2 mutable() {
        return new MutableVec2(0d, 0d);
    }

    static MutableVec2 mutable(final double x, final double y) {
        return new MutableVec2(x, y);
    }

    default double distance(final Vec2 other) {
        return Math.sqrt((other.x() - x()) * (other.x() - x()) + (other.y() - y()) * (other.y() - y()));
    }

    default double distanceSquared(final Vec2 other) {
        return (other.x() - x()) * (other.x() - x()) + (other.y() - y()) * (other.y() - y());
    }

    default double heading() {
        return Math.atan2(y(), x());
    }

    default double magnitude() {
        return Math.sqrt(x() * x() + y() * y());
    }

    default double magnitudeSquared() {
        return x() * x() + y() * y();
    }

    double x();

    double y();
}
