package com.spbpu;

import static com.spbpu.VectorOperations.MatrixVectorMult;
import static com.spbpu.VectorOperations.NumberVectorMult;
import static com.spbpu.VectorOperations.VectorVectorMult;

/**
 * Set of functions and their gradients
 */
class FunctionSet {
    static double[][] H = new double[][]{
            {9,2,1},
            {2,7,3},
            {1,3,4}};
    static double[][] S = new double[][]{
            {0,1,0},
            {-3,0,2},
            {1,-17/7,35/7}};

    static double f(double[] x) {
        return VectorVectorMult(x, MatrixVectorMult(H, x));
    }
    static double[] gradf(double[] x) {
        return NumberVectorMult(2, MatrixVectorMult(H, x));
    }

    static double f_new(double[] x) {
        return VectorVectorMult(x, MatrixVectorMult(H, x)) + x[2]*x[2] * Math.exp(x[0] + x[1]) / 5;
    }
    static double[] gradf_new(double[] x) {
        double[] grad = NumberVectorMult(2, MatrixVectorMult(H, x));
        grad[0] += x[2]*x[2] * Math.exp(x[0] + x[1]) / 5;
        grad[2] += 2 * x[2] * Math.exp(x[0] + x[1]) / 5;
        return grad;
    }
}
