package com.spbpu;

import static com.spbpu.VectorOperations.*;

public class NonlinearConjugateMethod {
    public static int Optimize(double[] x, Function function, Gradient gradient,
                               double eps, double eps1, double[][] S) {
        int k = 0;
        double[] x_new = new double[x.length];

        while(norm2(gradient.gradf(x)) > eps) {
            if (k >= 3)
                return -1;
            double step = GetNewStepDichotomy(x, S[k], function, eps1);
            System.arraycopy(VectorSum(x, NumberVectorMult(step, S[k])), 0, x_new, 0, x_new.length);
            if (function.f(x_new) > function.f(x))
                System.out.println(k + "!");
            System.arraycopy(x_new, 0, x, 0, x_new.length);
            k++;
        }

        return k;
    }

    private static double GetNewStepDichotomy(double[] x, double[] dir,
                                              Function function, double eps/*, int[] fCount*/) {
        double left = 0, right = 1; // начальный интервал неопределённости шага
        double step1, step2;
        double delta = eps / 20;
        while (right - left > eps) {
            step1 = (left + right) / 2 - delta;
            step2 = (left + right) / 2 + delta;
            //fCount[0] += 2;
            if (function.f(VectorSum(x, NumberVectorMult(-step1, dir))) >
                    function.f(VectorSum(x, NumberVectorMult(-step2, dir))))
                left = step1;
            else
                right = step2;
            assert (left < right);
        }
        return (left + right) / 2;
    }
}
