package com.spbpu;

import static com.spbpu.VectorOperations.*;

/**
 * Set of functions and their gradients
 */
class FunctionSet {
    final static double[][] H = new double[][]{
            {9,2,1},
            {2,7,3},
            {1,3,4}};
    final static double[] b = new double[]{1, 2, 3};
    final static double[][] S = new double[][]{
            {0, 1, 0},
            {-3, 0, 2},
            {1, -17.0/7, 5}};

    static double f(double[] x) {
        return VectorVectorMult(x, MatrixVectorMult(H, x)) / 2 + VectorVectorMult(b, x);
    }
    static double[] gradf(double[] x) {
        return VectorSum(MatrixVectorMult(H, x), b);
    }

    static double f_new(double[] x) {
        return f(x) + x[2]*x[2] * Math.exp(x[0] + x[1]) / 5;
    }
    static double[] gradf_new(double[] x) {
        double[] grad = gradf(x);
        grad[0] += x[2]*x[2] * Math.exp(x[0] + x[1]) / 5;
        grad[1] += x[2]*x[2] * Math.exp(x[0] + x[1]) / 5;
        grad[2] += 2 * x[2] * Math.exp(x[0] + x[1]) / 5;
        return grad;
    }

    static double[][] H(double[] x) {
        int m = H.length;
        double[][] H_ = new double[m][m];
        System.arraycopy(H, 0, H_, 0, m);
        for (int i = 0; i < m; ++i)
            System.arraycopy(H[i], 0, H_[i], 0, m);
        return H_;
    }
    static double[][] H_new(double[] x) {
        double[][] H_add = new double[][]{
                {x[2]*x[2] * Math.exp(x[0] + x[1]) / 5, x[2]*x[2] * Math.exp(x[0] + x[1]) / 5, 2*x[2] * Math.exp(x[0] + x[1]) / 5},
                {x[2]*x[2] * Math.exp(x[0] + x[1]) / 5, x[2]*x[2] * Math.exp(x[0] + x[1]) / 5, 2*x[2] * Math.exp(x[0] + x[1]) / 5},
                {2*x[2] * Math.exp(x[0] + x[1]) / 5, 2*x[2] * Math.exp(x[0] + x[1]) / 5, 2 * Math.exp(x[0] + x[1]) / 5}};
        return MatrixMatrixSum(H, H_add);
    }
}
