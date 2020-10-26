// Студент гр. 2151 Белоножко Никита, 2 вариант
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
        mn.run(mn,x1, x2, deltaX);
    }

    private void run(Main mn, double x1, double x2, double deltaX)
    {
        mn.fillX(x1, x2, deltaX);
        mn.fillY(x1, x2, deltaX);
        System.out.println("Max y = " + mn.maxY(y));
        System.out.println("Min y = " + mn.minY(y));
        System.out.println("Averange y = " + mn.averangeY(y));
    }

    public double calcY(double x)
    {
        if (abs (x-1.7) < eps)
            return  a * x * x * x  + 7 * sqrt(x);
        else if (x < 1.7 - eps)
            return p * x * x - 7 / (x*x);
        return log10(x+7*sqrt(x));
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

    public int maxY(double [] y)
    {
        int max = 0;
        for (int i = 0; i < y.length; i++)
            if (y[i]>y[max])
                max = i;
        return max;
    }

    public int minY(double [] y)
    {
        int min = 0;
        for (int i = 0; i < y.length; i++)
            if (y[i]<y[min])
                min = i;
        return min;
    }

    public double sumY(double [] y)
    {
        double sum = 0;
        for (int i = 0; i < y.length; i++)
            sum += y[i];
        return sum;
    }

    public double averangeY(double [] y)
    {
        return sumY(y)/y.length;
    }
}


