package logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class Lab2Test {
    Lab2 l2;
    double x1 = 0.8;
    double x2 = 2;
    double deltaX = 0.005;
    double [] y = { 5, 2, 3, 10, 4, 1 };

    @BeforeEach
    void setUp() {
        l2 = new Lab2();
    }

    @Test
    void testFindSize()
    {
        double expected = 241;
        double res = l2.findSize(x1, x2, deltaX);
        assertEquals(expected, res, "Wrong findSizeX");
    }

    @Test
    void testFillX()
    {
        double expected = 2;
        l2.fillX(x1, x2, deltaX);
        double res = l2.x[240];
        assertEquals(expected, res, 0.0001, "Wrong fillX");
    }

    @Test
    void testCalcY()
    {
        double expected = 16.4964;
        l2.fillX(x1, x2, deltaX);
        double res = l2.calcY(l2.x[180]);
        assertEquals(expected, res, 0.0001, "Wrong calcY");
    }

    @Test
    void testFillY()
    {
        l2.fillX(x1, x2, deltaX);
        l2.fillY(x1, x2, deltaX);
        double expected = -8.3775;
        double res = l2.y[0];
        assertEquals(expected, res, 0.0001, "Wrong fillY");
    }

    @Test
    void testMinY()     // Y: Number of max = 3, of min = 5, Averange = 4.166.... Sum = 25
    {
        double expected = 5;
        double res = l2.minY(y);
        assertEquals(expected, res, 0.0001, "Wrong minY");
    }

    @Test
    void testMaxY()
    {
        double expected = 3;
        double res = l2.maxY(y);
        assertEquals(expected, res, 0.0001, "Wrong maxY");
    }

    @Test
    void testSumY()
    {
        double expected = 25;
        double res = l2.sumOf(y);
        assertEquals(expected, res, 0.0001, "Wrong sumY");
    }

    @Test
    void testAverangeY()
    {
        double expected = 4.166666;
        double res = l2.averageY(y);
        assertEquals(expected, res, 0.0001, "Wrong averangeY");
    }
}