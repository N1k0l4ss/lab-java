package calculator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

public class IntegralCalc {
    private double a;
    private double b;
    private int n;
    private DoubleUnaryOperator f;
    private double h;

    public IntegralCalc(double a, double b, int n, DoubleUnaryOperator f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    static public double func(double x){
        return x*x;
    }

    public double calcIntegral(){
        h = (b-a)/n;
        return (f.applyAsDouble(a) + f.applyAsDouble(b))/2*h +
                IntStream.range(1, n).mapToDouble(i->a+i*h).map(f).map(v->v*h).sum();
    }
}
