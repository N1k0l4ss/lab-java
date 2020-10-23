import static java.lang.Math.*;
public class Main {

    double [] x;
    double [] y;
    double eps = 0.00001;
    double a = 1.5;
    double p = 4;

    public double calcY(double x)
    {
        double res = 0;
        if (abs (x-1.7) < eps)
            res = (a*x) * (a*x) * (a*x) + 7 * sqrt(x);
        else if (x < 1.7)
            res = (p*x)*(p*x) - 7 / (x*x);
        else if (x > 1.7)
            res = log10(x+7*sqrt(x));
        return res;
    }

    public int findSize(double x1, double x2, double deltaX)
    {
        return (int) ((x2 - x1) / deltaX) + 1;
    }

    public void fillY(double x1, double x2, double deltaX)
    {
//        fillX(x1, x2, deltaX);
        y = new double[findSize(x1,x2,deltaX)];
        for (int i = 0; i < y.length; i++) {
            y[i] = calcY(x[i]);
        }
    }

    public void fillX(double x1, double x2, double deltaX)
    {
        x = new double[findSize(x1,x2,deltaX)];
        for (int i = 0; i < x.length; i++) {
            x[i] = x1 + deltaX * i;
        }
    }
}


