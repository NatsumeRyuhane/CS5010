package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import transmission.AutomaticTransmission;
import transmission.Transmission;

public class AutomaticTransmissionGearShiftTest {
    @Before
    public void setUp() throws Exception {
    }

    // speed increase with no gear shift
    @Test
    public void testAutomaticTransmissionGearShift1() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(4);

        assertEquals(4, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.increaseSpeed();
        assertEquals(6, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // chained calls to increaseSpeed, but with no gear shift
    @Test
    public void testAutomaticTransmissionGearShift2() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(4);

        assertEquals(4, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.increaseSpeed().increaseSpeed();
        assertEquals(8, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // speed increase with gear shift
    @Test
    public void testAutomaticTransmissionGearShift3() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(8);

        assertEquals(8, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.increaseSpeed();
        assertEquals(10, newTransmission.getSpeed());
        assertEquals(2, newTransmission.getGear());
    }

    @Test
    public void testAutomaticTransmissionGearShift3_1() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        assertEquals(0, at.getSpeed());
        assertEquals(0, at.getGear());

        Transmission newTransmission = at.increaseSpeed();
        assertEquals(2, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    @Test
    public void testAutomaticTransmissionGearShift3_2() {
        AutomaticTransmission at = new AutomaticTransmission(2, 10, 20, 30, 40);
        assertEquals(0, at.getSpeed());
        assertEquals(0, at.getGear());

        Transmission newTransmission = at.increaseSpeed();
        assertEquals(2, newTransmission.getSpeed());
        assertEquals(2, newTransmission.getGear());
    }

    // chained calls to increaseSpeed, with gear shift
    @Test
    public void testAutomaticTransmissionGearShift4() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(6);

        assertEquals(6, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.increaseSpeed().increaseSpeed();
        assertEquals(10, newTransmission.getSpeed());
        assertEquals(2, newTransmission.getGear());
    }

    // speed decrease with no gear shift
    @Test
    public void testAutomaticTransmissionGearShift5() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(6);

        assertEquals(6, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.decreaseSpeed();
        assertEquals(4, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // chained calls to decreaseSpeed, but with no gear shift
    @Test
    public void testAutomaticTransmissionGearShift6() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(6);

        assertEquals(6, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.decreaseSpeed().decreaseSpeed();
        assertEquals(2, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // speed decrease with gear shift
    @Test
    public void testAutomaticTransmissionGearShift7() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(10);

        assertEquals(10, at.getSpeed());
        assertEquals(2, at.getGear());

        Transmission newTransmission = at.decreaseSpeed();
        assertEquals(8, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // chained calls to decreaseSpeed, with gear shift
    @Test
    public void testAutomaticTransmissionGearShift8() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(10);

        assertEquals(12, at.getSpeed());
        assertEquals(2, at.getGear());

        Transmission newTransmission = at.decreaseSpeed().decreaseSpeed();
        assertEquals(8, newTransmission.getSpeed());
        assertEquals(1, newTransmission.getGear());
    }

    // speed decrease to 0
    @Test
    public void testAutomaticTransmissionGearShift9() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(2);

        assertEquals(2, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission = at.decreaseSpeed();
        assertEquals(0, newTransmission.getSpeed());
        assertEquals(0, newTransmission.getGear());
    }

    // speed decrease to negative
    @Test(expected = IllegalStateException.class)
    public void testAutomaticTransmissionGearShift10() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);

        assertEquals(0, at.getSpeed());
        assertEquals(0, at.getGear());

        Transmission newTransmission = at.decreaseSpeed();
    }

    // chained calls to decreaseSpeed, with the speed decrease to negative
    @Test(expected = IllegalStateException.class)
    public void testAutomaticTransmissionGearShift11() {
        AutomaticTransmission at = new AutomaticTransmission(10, 20, 30, 40, 50);
        at.setSpeed(6);

        assertEquals(6, at.getSpeed());
        assertEquals(1, at.getGear());

        Transmission newTransmission =
                at.decreaseSpeed()
                .decreaseSpeed()
                .decreaseSpeed()
                .decreaseSpeed()
                .decreaseSpeed();
    }
}
