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

    /**
     * Inverse matrix using Gauss method
     * @param M_in input matrix
     * @return inversed matrix
     */
    public static double[][] Inverse(double[][] M_in) {
        assert(M_in.length == M_in[0].length);
        int m = M_in.length;

        double[][] M = new double[m][m];
        System.arraycopy(M_in, 0, M, 0, m);
        for (int i = 0; i < m; ++i)
            System.arraycopy(M_in[i], 0, M[i], 0, m);

        double[][] res = new double[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                res[i][j] = i == j ? 1 : 0;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int k = i; k < m; ++k) {
                if (Math.abs(M[k][i]) > 1e-10) {
                    if (i != k) {
                        SwapRows(M, i, k);
                        SwapRows(res, i, k);
                    }
                    break;
                }
                if (k == m - 1) {
                    return null;
                }
            }
            double d = M[i][i];
            for (int j = 0; j < m; ++j) {
                M[i][j] /= d;
                res[i][j] /= d;
            }
            for (int k = i + 1; k < m; ++k) {
                d = M[k][i];
                for (int j = 0; j < m; ++j) {
                    M[k][j] -= M[i][j] * d;
                    res[k][j] -= res[i][j] * d;
                }
            }
        }

        for (int i = m - 2; i >= 0; --i) {
            for (int j = m - 1; j > i; --j) {
                for (int k = 0; k < m; ++k) {
                    res[i][k] -= res[j][k] * M[i][j];
                }
            }
        }
        return res;
    }

    private static void SwapRows(double[][] M, int i, int j) {
        for (int k = 0; k < M[i].length; ++k) {
            double tmp = M[i][k];
            M[i][k] = M[j][k];
            M[j][k] = tmp;
        }

    }
}
