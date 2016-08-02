package com.banjocreek.d2;

/**
 * Normalized affine transform. Implied m02, m12 are zero and m22 is 1.
 */
public interface NormalizedTransform {

    double m00();

    double m01();

    double m10();

    double m11();

    double m20();

    double m21();

}
