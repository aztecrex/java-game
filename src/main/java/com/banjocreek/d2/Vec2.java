package com.banjocreek.d2;

public interface Vec2 {
    double x();
    double y();

    
    default double distance(final Vec2 other) {
        return Math.sqrt((other.x() - this.x()) * (other.x() - this.x()) + (other.y() - this.y()) * (other.y() - this.y()));
    }

    default double distanceSquared(final Vec2 other) {
        return (other.x() - this.x()) * (other.x() - this.x()) + (other.y() - this.y()) * (other.y() - this.y());
    }

    default double heading() {
        return Math.atan2(this.x(), this.y());
    }

    default double magnitude() {
        return Math.sqrt(this.x() * this.x() + this.y() * this.y());
    }

    default double magnitudeSquared() {
        return this.x() * this.x() + this.y() * this.y();
    }
 
    
    static ImmutableVec2 imv(double x, double y) {
        return new ImmutableVec2(x,y);
    }
    
    static ImmutableVec2 imv(Vec2 v) {
        return new ImmutableVec2(v.x(), v.y());
    }
}
