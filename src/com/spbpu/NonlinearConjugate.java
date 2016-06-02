package com.spbpu;

import static com.spbpu.VectorOperations.*;

public class NonlinearConjugate {

    public static int Optimize(double[] x, Function function, Gradient gradient,
                               double eps, double eps1, double[][] S) {
        int k = 0;
        double[] x_new = new double[x.length];

        System.out.println(function.f(x) + ", градиент: " +
                gradient.gradf(x)[0] + " " + gradient.gradf(x)[1] + " " + gradient.gradf(x)[2]);

        while(norm2(gradient.gradf(x)) > eps*eps && k < 3) {
            double step = GetNewStepDichotomy(x, S[k], function, eps1);
            //System.out.println("step: " + step);
            System.arraycopy(VectorSum(x, NumberVectorMult(step, S[k])), 0, x_new, 0, x_new.length);
            if (function.f(x_new) >= function.f(x)) {
                step = GetNewStepDichotomy(x, NumberVectorMult(-1, S[k]), function, eps1);
                System.arraycopy(VectorSum(x, NumberVectorMult(-step, S[k])), 0, x_new, 0, x_new.length);
                //System.out.println("step: " + step);
            }
            System.arraycopy(x_new, 0, x, 0, x_new.length);
            System.out.println(function.f(x) + ", градиент: " +
                    gradient.gradf(x)[0] + " " + gradient.gradf(x)[1] + " " + gradient.gradf(x)[2]);
            k++;
        }
        return k;
    }

    public static int OptimizeFletcherReeves(double[] x, Function function, Gradient gradient, Hessian hessian,
                                             double eps, double eps1) {
        int k = 0;
        double[] x_new = new double[x.length];
        double[] S = new double[x.length];
        double[] S_new = new double[x.length];
        double[] grad = new double[x.length];

        System.arraycopy(gradient.gradf(x), 0, grad, 0, grad.length);
        System.arraycopy(NumberVectorMult(-1, grad), 0, S, 0, S.length);
        System.out.println("x0: " + x[0] + " " + x[1] + " " + x[2]);
        System.out.println(function.f(x) + ", градиент: " + grad[0] + " " + grad[1] + " " + grad[2]);

        while(norm2(grad) > eps*eps) {
            double step = GetNewStepDichotomy(x, S, function, eps1);
            System.out.print(step + ", ");
            System.arraycopy(VectorSum(x, NumberVectorMult(step, S)), 0, x_new, 0, x_new.length);
            if (function.f(x_new) >= function.f(x)) {
                System.out.println("Mistake in " + k + " step");
                return -1;
            }
            System.arraycopy(gradient.gradf(x_new), 0, grad, 0, grad.length);
            double beta = VectorVectorMult(gradient.gradf(x_new), MatrixVectorMult(hessian.H(x_new), S)) /
                    VectorVectorMult(S, MatrixVectorMult(hessian.H(x_new), S));
            System.arraycopy(VectorSum(NumberVectorMult(-1, grad), NumberVectorMult(beta, S)),
                    0, S_new, 0, S_new.length);
            System.arraycopy(S_new, 0, S, 0, S.length);
            System.arraycopy(x_new, 0, x, 0, x.length);
            System.out.println(function.f(x) + ", градиент: " + grad[0] + " " + grad[1] + " " + grad[2]);
            k++;
        }
        return k;
    }

    public static int OptimizeNewton(double[] x, Function function, Gradient gradient,
                                     Hessian hessian, double eps) {
        int k = 0;
        double[] x_new = new double[x.length];
        double[] grad = new double[x.length];

        System.arraycopy(gradient.gradf(x), 0, grad, 0, grad.length);
        System.out.println(function.f(x) + ", градиент: " + grad[0] + " " + grad[1] + " " + grad[2]);

        while(norm2(grad) > eps*eps) {
            System.arraycopy(VectorSum(x, NumberVectorMult(-1, MatrixVectorMult(Inverse(hessian.H(x)), grad))),
                    0, x_new, 0, x_new.length);
            if (function.f(x_new) >= function.f(x)) {
                System.out.println(k + "!");
                return -1;
            }
            System.arraycopy(gradient.gradf(x_new), 0, grad, 0, grad.length);
            System.arraycopy(x_new, 0, x, 0, x.length);
            System.out.println(function.f(x) + ", градиент: " + grad[0] + " " + grad[1] + " " + grad[2]);
            k++;
        }

        return k;
    }

    private static double GetNewStepDichotomy(double[] x, double[] dir, Function function, double eps) {
        double left = 0, right = 5; // начальный интервал неопределённости шага
        double step1, step2;
        double delta = eps / 20;
        while (right - left > eps) {
            step1 = (left + right) / 2 - delta;
            step2 = (left + right) / 2 + delta;
            //fCount[0] += 2;
            if (function.f(VectorSum(x, NumberVectorMult(step1, dir))) >
                    function.f(VectorSum(x, NumberVectorMult(step2, dir))))
                left = step1;
            else
                right = step2;
            assert (left < right);
        }
        return (left + right) / 2;
    }
}
