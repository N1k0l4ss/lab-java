package calculator;

import java.util.function.DoubleUnaryOperator;

public class Main {
    private double summaryResult;
    private int finishedThreads;
    private final int threads = 10;

    private final double start = 1;
    private final double end = 4;
    private final int n = 100_000_000;

    public static void main(String[] args) {
        new Main()
//                .runMonoThread();
                    .runMultiThread();
    }

    private void runMultiThread() {
        double delta = (end - start) / threads;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            new Thread(new ThreadCalculator(start+i*delta, start+(i+1)*delta, n/threads, IntegralCalc::func, this)).start();
        }
        synchronized (this) {
            while (finishedThreads < threads) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }

        long finishTime = System.currentTimeMillis();
        double res = summaryResult;
        System.out.println("Res = " + res);
        System.out.println("Time = " + (finishTime-startTime));
    }

    public synchronized void sumResult(double res) {
        summaryResult += res;
        finishedThreads++;
        notify();
    }

    private void runMonoThread() {
        IntegralCalc integralCalc = new IntegralCalc(start, end, n, IntegralCalc::func);
        long startTime = System.currentTimeMillis();
        double res = integralCalc.calcIntegral();
        long finishTime = System.currentTimeMillis();
        System.out.println("Res = " + res);
        System.out.println("Time = " + (finishTime-startTime));
    }
}
