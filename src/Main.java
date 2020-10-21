import static java.lang.Math.*;
public class Main {

    double [] x;
    double [] y;
    double eps = 0.0001;
    double a = 1.5;
    double p = 4;

    public double calcY(double x)
    {
        double res = 0;
        if (x < 1.7)
            res = (p*x*p*x) - 7 / (x*x);
        else if (x == 1.7)
            res = pow(a*x, 3) + 7*sqrt(x);
        else if (x > 1.7)
            res = log10(x+7*sqrt(x));
        return res;
    }

    public int findSizeX(double x1, double x2, double deltaX)
    {
        return (int) ((x2 - x1) / deltaX) + 1;
    }

    public void fillX()
    {
        double deltaX = 0.005;
        double x1 = 0.8;
        int x2 = 2;
        x = new double[findSizeX(x1,x2,deltaX)];
        for (int i = 0; i < x.length; i++) {
            x[i] = x1 + deltaX * i;
    }

}
}


