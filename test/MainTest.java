import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testCalcY()
    {
        double expected = 0.6975;
        main.fillX(0.8, 2, 0.005);
        double res = main.calcY(main.x[0]);
        res = Math.abs(res);
        assertEquals(expected, res, 0.00001, "Wrong calcY");
    }

    @Test
    void testFindSize()
    {
        double expected = 241;
        double res = main.findSize(0.8, 2, 0.005);
        assertEquals(expected, res, "Wrong findSizeX");
    }

    @Test
    void testFillX()
    {
        double expected = 2;
        main.fillX(0.8, 2, 0.005);
        double res = main.x[240];
        assertEquals(expected, res, 0.00001, "Wrong fillX");
    }

    @Test
    void testFillY()
    {
        main.fillX(0.8, 2, 0.005);
        main.fillY(0.8, 2, 0.005);
        double expected = 0.6975;
        double res = main.y[0];
        assertEquals(expected, res, 0.00001, "Wrong fillY");
    }
}