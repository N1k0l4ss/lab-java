import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// Студент гр. 2151 Белоножко Никита, 2 вариант
// Номера для тестирования: 0, 180, 240
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;
    double x1 = 0.8;
    double x2 = 2;
    double deltaX = 0.005;
    double [] y = { 5, 2, 3, 10, 4, 1 };

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
    void testMinY()     // Y: Number of max = 3, of min = 5, Averange = 4.166.... Sum = 25
    {
        double expected = 5;
        double res = main.minY(y);
        assertEquals(expected, res, 0.00001, "Wrong minY");
    }

    @Test
    void testMaxY()
    {
        double expected = 3;
        double res = main.maxY(y);
        assertEquals(expected, res, 0.00001, "Wrong maxY");
    }

    @Test
    void testSumY()
    {
        double expected = 25;
        double res = main.sumY(y);
        assertEquals(expected, res, 0.00001, "Wrong sumY");
    }

    @Test
    void testAverangeY()
    {
        double expected = 4.166666;
        double res = main.averangeY(y);
        assertEquals(expected, res, 0.00001, "Wrong averangeY");
    }
}