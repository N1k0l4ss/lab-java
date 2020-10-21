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
        main.fillX();
        double res = main.calcY(main.x[0]);
        res = Math.abs(res);
        assertEquals(expected, res, 0.00001, "Wrong calcY");
    }

    @Test
    void testFillX()
    {
        double expected = 0.8;
        main.fillX();
        double res = main.x[0];
        assertEquals(expected, res, 0.00001, "Wrong fillX");
    }
}