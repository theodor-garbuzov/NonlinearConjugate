package com.spbpu;


public class VectorOperations {

    /**
     * Vector sum
     *
     * @param v1 - vector 1
     * @param v2 - vector 2
     * @return result vector
     */
    public static double[] VectorSum(double[] v1, double[] v2) {
        assert (v1.length == v2.length);
        double[] result = new double[v1.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = v1[i] + v2[i];
        return result;
    }

    /**
     * Multiply vector by number
     *
     * @param num  - number
     * @param v - vector
     * @return result vector
     */
    public static double[] NumberVectorMult(double num, double[] v) {
        double[] result = new double[v.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = num * v[i];
        return result;
    }

    /**
     * Multiply vectors
     *
     * @param v1 - vector 1
     * @param v2 - vector 2
     * @return result of multiplication
     */
    public static double VectorVectorMult(double[] v1, double[] v2) {
        double result = 0;
        for (int i = 0; i < v1.length; ++i)
            result += v1[i] * v2[i];
        return result;
    }

    /**
     * Multiply vector by matrix (left: Matrix * vector)
     *
     * @param M - matrix
     * @param v - vector
     * @return result of multiplication
     */
    public static double[] MatrixVectorMult(double[][] M, double[] v) {
        double[] result = new double[M.length];
        for (int i = 0; i < M.length; ++i) {
            result[i] = 0;
            for (int j = 0; j < v.length; ++j)
                result[i] += M[i][j] * v[j];
        }
        return result;
    }

    /**
     * Norm of vector squared
     *
     * @param v - vector
     * @return norm of vector squared
     */
    public static double norm2(double[] v)
    {
        double res = 0;
        for (double vI : v) res += vI * vI;
        return res;
    }

    public static double[][] MatrixMatrixSum(double[][] M1, double[][] M2) {
        assert(M1.length == M2.length && M1[0].length == M2.length);
        double[][] res = new double[M1.length][M1[0].length];
        for (int i = 0; i < M1.length; ++i)
            for (int j = 0; j < M1[i].length; ++j)
                res[i][j] = M1[i][j] + M2[i][j];
        return res;
    }
}
