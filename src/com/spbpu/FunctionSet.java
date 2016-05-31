package com.spbpu;

import static com.spbpu.VectorOperations.MatrixVectorMult;
import static com.spbpu.VectorOperations.NumberVectorMult;
import static com.spbpu.VectorOperations.VectorVectorMult;

/**
 * Set of functions and their gradients
 */
class FunctionSet {
    private static double[][] H = new double[][]{
            {9,2,1},
            {2,7,3},
            {1,3,4}};
    static double[][] S = new double[][]{
            {0,1,0},
            {-2,1,-1},
            {21,45,-119}};
    static double f(double[] x) {
        return VectorVectorMult(x, MatrixVectorMult(H, x));
    }
    static double[] gradf(double[] x) {
        return NumberVectorMult(2, MatrixVectorMult(H, x));
    }
}
