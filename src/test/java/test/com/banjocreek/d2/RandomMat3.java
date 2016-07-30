package test.com.banjocreek.d2;

import java.util.Random;

import com.banjocreek.d2.Mat3;

final class RandomMat3 implements Mat3 {
    static final Random rng = new Random();

    private final double c1[][] = new double[3][3];

    {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.c1[i][j] = rng.nextFloat() * 2.0 - 1.0;
            }
        }
    }

    @Override
    public double m00() {
        return this.c1[0][0];
    }

    @Override
    public double m01() {
        return this.c1[0][1];
    }

    @Override
    public double m02() {
        return this.c1[0][2];
    }

    @Override
    public double m10() {
        return this.c1[1][0];
    }

    @Override
    public double m11() {
        return this.c1[1][1];
    }

    @Override
    public double m12() {
        return this.c1[1][2];
    }

    @Override
    public double m20() {
        return this.c1[2][0];
    }

    @Override
    public double m21() {
        return this.c1[2][1];
    }

    @Override
    public double m22() {
        return this.c1[0][2];
    }

}