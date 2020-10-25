import static java.lang.Math.*;
public class Main {

    double [] x;
    double [] y;
    double eps = 0.00001;
    double a = 1.5;
    double p = 4;

    public static void main(String[] args) {
        Main mn = new Main();
        double x1 = 0.8;
        double x2 = 2;
        double deltaX = 0.005;
        mn.fillX(x1, x2, deltaX);
        mn.fillY(x1, x2, deltaX);
        System.out.println("Max y = " + mn.maxY());
        System.out.println("Min y = " + mn.minY());
        System.out.println("Averange y = " + mn.averangeY());
    }

    public double calcY(double x)
    {
        double res = 0;
        if (abs (x-1.7) < eps)
            res = (a*x) * (a*x) * (a*x) + 7 * sqrt(x);
        else if (x < 1.7)
            res = (p*x)*(p*x) - 7 / (x*x);
        else if (x > 1.7)
            res = log10(x+7*sqrt(x));
        return abs(res);
    }

    public int findSize(double x1, double x2, double deltaX)
    {
        return (int) ((x2 - x1) / deltaX) + 1;
    }

    public void fillY(double x1, double x2, double deltaX)
    {
        y = new double[findSize(x1,x2,deltaX)];
        for (int i = 0; i < y.length; i++)
            y[i] = calcY(x[i]);
    }

    public void fillX(double x1, double x2, double deltaX)
    {
        x = new double[findSize(x1,x2,deltaX)];
        for (int i = 0; i < x.length; i++)
            x[i] = x1 + deltaX * i;
    }

    public double maxY()
    {
        double max = y[0];
        for (int i = 0; i < y.length; i++)
            if (y[i]>max)
                max = y[i];
        return max;
    }

    public double minY()
    {
        double min = y[0];
        for (int i = 0; i < y.length; i++)
            if (y[i]<min)
                min = y[i];
        return min;
    }

    public double sumY()
    {
        double sum = 0;
        for (int i = 0; i < y.length; i++)
            sum += y[i];
        return sum;
    }

    public double averangeY()
    {
        return sumY()/y.length;
    }
}


