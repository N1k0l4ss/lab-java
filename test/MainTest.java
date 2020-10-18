import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testCalcY()
    {
        int i = 240;
        double h = 0.005;
        int p = 4;
        double a = 1.5;
        double res = main.calcY(p, i, a, h);
        assertEquals(a, res, h, "y must be a x*x");
    }
}