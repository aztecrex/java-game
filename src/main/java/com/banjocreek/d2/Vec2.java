package com.banjocreek.d2;

public interface Vec2 {
    static ImmutableVec2 immutable(final double x, final double y) {
        return new ImmutableVec2(x, y);
    }

    static ImmutableVec2 immutable(final Vec2 v) {
        return new ImmutableVec2(v.x(), v.y());
    }

    static MutableVec2 mutable(final double x, final double y) {
        return new MutableVec2(x, y);
    }

    static MutableVec2 mutable(final Vec2 v) {
        return new MutableVec2(v.x(), v.y());
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
