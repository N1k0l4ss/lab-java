import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {

    }

    public double calcY(int p, int i, double a, double h)
    {
        double x = a + i * h;
        double res = 0;
        if (x < 1.7)
            res = (p*x*p*x) - 7 / (x*x);
        else if (x >= 1.7 && x < 1.8)
            res = pow(a*x, 3) + 7*sqrt(x);
        else if (x>1.7)
            res = log10(x+7*sqrt(x));
        return res;
    }

}
