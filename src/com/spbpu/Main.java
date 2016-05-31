package com.spbpu;

public class Main {

    public static void main(String[] args) {
        double[] x = new double[]{1, 2, 3};
        double eps = 0.01;
        double eps1 = 0.01; // one-dimensional optimization

        int iter = NonlinearConjugateMethod.Optimize(x, FunctionSet::f, FunctionSet::gradf,
                eps, eps1, FunctionSet.S);
        System.out.println("\nКоличество шагов: " + iter);
        System.out.println("Точка минимума: " + x[0] + " " + x[1] + " " + x[2]);
    }
}
