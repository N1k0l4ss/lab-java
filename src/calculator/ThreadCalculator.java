package calculator;


import java.util.function.DoubleUnaryOperator;

public class ThreadCalculator implements Runnable{
    private IntegralCalc calculator;
    private Main main;

    public ThreadCalculator(double a, double b, int n, DoubleUnaryOperator f, Main main) {
        calculator = new IntegralCalc(a, b, n, f);
        this.main = main;
    }

    @Override
    public void run() {
        double res = calculator.calcIntegral();
        main.sumResult(res);
    }
}
