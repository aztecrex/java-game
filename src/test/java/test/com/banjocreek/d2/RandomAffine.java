package test.com.banjocreek.d2;

final class RandomAffine extends RandomMat3 {

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