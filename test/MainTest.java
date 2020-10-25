import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;
    double x1 = 0.8;
    double x2 = 2;
    double deltaX = 0.005;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testFindSize()
    {
        double expected = 241;
        double res = main.findSize(x1, x2, deltaX);
        assertEquals(expected, res, "Wrong findSizeX");
    }

    @Test
    void testFillX()
    {
        double expected = 2;
        main.fillX(x1, x2, deltaX);
        double res = main.x[240];
        assertEquals(expected, res, 0.00001, "Wrong fillX");
    }

    @Test
    void testCalcY()
    {
        double expected = 0.6975;
        main.fillX(x1, x2, deltaX);
        double res = main.calcY(main.x[0]);
        assertEquals(expected, res, 0.00001, "Wrong calcY");
    }

    @Test
    void testFillY()
    {
        main.fillX(x1, x2, deltaX);
        main.fillY(x1, x2, deltaX);
        double expected = 0.6975;
        double res = main.y[0];
        assertEquals(expected, res, 0.00001, "Wrong fillY");
    }

    @Test
    void testMinY()
    {
        main.fillX(x1, x2, deltaX);
        main.fillY(x1, x2, deltaX);
        double expected = 0.089;
        double res = main.minY();
        assertEquals(expected, res, 0.00001, "Wrong minY");
    }

    @Test
    void testMaxY()
    {
        main.fillX(x1, x2, deltaX);
        main.fillY(x1, x2, deltaX);
        double expected = 43.53194;
        double res = main.maxY();
        assertEquals(expected, res, 0.00001, "Wrong maxY");
    }

    @Test
    void testSumY()
    {
        main.fillX(x1, x2, deltaX);
        main.fillY(x1, x2, deltaX);
        double expected = 3837.33993;
        double res = main.sumY();
        assertEquals(expected, res, 0.00001, "Wrong sumY");
    }

    @Test
    void testAverangeY()
    {
        main.fillX(x1, x2, deltaX);
        main.fillY(x1, x2, deltaX);
        double expected = 15.92257;
        double res = main.averangeY();
        assertEquals(expected, res, 0.00001, "Wrong averangeY");
    }
}