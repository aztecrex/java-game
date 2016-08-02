package test.com.banjocreek.d2;

import com.banjocreek.d2.NormalizedTransform;

final class RandomAffine extends RandomMat3 implements NormalizedTransform {

    @Override
    public double m02() {
        return 0;
    }

    @Override
    public double m12() {
        return 0;
    }

    @Override
    public double m22() {
        return 1;
    }

}