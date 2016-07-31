package com.banjocreek.d2;

public interface Mat3 {
    static ImmutableMat3 immutable() {
        return new ImmutableMat3(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    static ImmutableMat3 immutable(final double m00, final double m01, final double m02, final double m10,
            final double m11, final double m12, final double m20, final double m21, final double m22) {
        return new ImmutableMat3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }

    static MutableMat3 mutable() {
        return new MutableMat3(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    static MutableMat3 mutable(final double m00, final double m01, final double m02, final double m10, final double m11,
            final double m12, final double m20, final double m21, final double m22) {
        return new MutableMat3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }

    double m00();

    double m01();

    double m02();

    double m10();

    double m11();

    double m12();

    double m20();

    double m21();

    double m22();

}
