import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private int m, c;
    private double t, b;

    public static void main(String[] args)
    {
        Main mn = new Main();
        mn.enterVars();
        double f = mn.calcF();
        double z = mn.calcZ();
        mn.datePrintOld();
        mn.datePrintNew();
        mn.printVar(f, z);
    }

    private void datePrintNew()
    {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Дата и время[new]: %d %s, %d:%d:", now.getDayOfMonth(), now.getMonth(), now.getHour(), now.getMinute());
        System.out.println(now.format(DateTimeFormatter.ofPattern("ss.SSS")));
    }

    private void enterVars()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter m: ");
        m = s.nextInt();
        System.out.print("Enter c: ");
        c = s.nextInt();
        System.out.print("Enter t: ");
        t = s.nextDouble();
        System.out.print("Enter b: ");
        b = s.nextDouble();
    }
    private double calcF()
    {
        double sinT = Math.sin(t);
        return Math.cbrt(m * Math.tan(t) + Math.abs(c * sinT));
    }
    private double calcZ()
    {
        return m * Math.cos(b * t * Math.sin(t)) + c;
    }

    private void printVar(double f, double z)
    {
        System.out.printf("m = %d, c = %d, t = %f, b = %f\n", m, c, t, b);
        System.out.printf("F = %f, Z = %f\n", f, z);
    }
    private void datePrintOld()
    {
        Date date = new Date();
        System.out.printf("Дата и время[old]: %1$te %1$tB, %1$tH:%1$tM:%1$tS:%1$tL\n", date);
    }
}