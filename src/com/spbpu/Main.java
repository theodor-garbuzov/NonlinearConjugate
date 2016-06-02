package com.spbpu;

public class Main {

    public static void main(String[] args) {
        double[] x = new double[]{-1, -1, -1};
        double eps = 0.01;
        double eps1 = 0.001; // one-dimensional optimization

        int iter = NonlinearConjugate.Optimize(x, FunctionSet::f, FunctionSet::gradf,
                eps, eps1, FunctionSet.S);
        System.out.println("Количество шагов: " + iter);
        System.out.println("Точка минимума: " + x[0] + " " + x[1] + " " + x[2]);

        System.arraycopy(new double[]{2, 2, 3}, 0, x, 0, x.length);
        System.out.println("\nФлетчер-Ривс: ");
        iter = NonlinearConjugate.OptimizeFletcherReeves(x, FunctionSet::f_new, FunctionSet::gradf_new,
                FunctionSet::H_new, eps, eps1);
        System.out.println("Количество шагов: " + iter);
        System.out.println("Точка минимума: " + x[0] + " " + x[1] + " " + x[2]);

        System.arraycopy(new double[]{2, 1, 3}, 0, x, 0, x.length);
        System.out.println("\nНьютон: ");
        iter = NonlinearConjugate.OptimizeNewton(x, FunctionSet::f_new, FunctionSet::gradf_new,
                FunctionSet::H_new, eps);
        System.out.println("Количество шагов: " + (iter/3));
        System.out.println("Точка минимума: " + x[0] + " " + x[1] + " " + x[2]);
    }
}
