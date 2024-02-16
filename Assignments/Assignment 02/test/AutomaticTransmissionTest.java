package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import transmission.AutomaticTransmission;

public class AutomaticTransmissionTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAutomaticTransmission() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);

        assertEquals(0, at.getGear());
        assertEquals(0, at.getSpeed());
    }

    // the first argument in constructor should be greater than 0
    @Test(expected = IllegalArgumentException.class)
    public void testATInstantiationWithIllegalArguments1() {
        AutomaticTransmission at = new AutomaticTransmission(0, 20, 30, 40, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testATInstantiationWithIllegalArguments2() {
        AutomaticTransmission at = new AutomaticTransmission(-1, 20, 30, 40, 50);
    }

    // the passed-in sequence of gear shift speeds should be in strict ascending order
    @Test(expected = IllegalArgumentException.class)
    public void testATInstantiationWithIllegalArguments3() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 15, 40, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testATInstantiationWithIllegalArguments4() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 20, 40, 50);
    }
}
