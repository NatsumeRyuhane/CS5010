package test;
import org.junit.Test;
import static org.junit.Assert.*;

import lookandsay.*;

import java.math.BigInteger;
import java.util.Iterator;

public class LookAndSayIteratorTest {
    @Test
    public void LASIteratorConstructorTest() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("1"), new BigInteger("10"));
        las = new LookAndSayIterator(new BigInteger("1"));
        las = new LookAndSayIterator(new BigInteger("9".repeat(100)));
        las = new LookAndSayIterator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void LASIteratorConstructorTestInvalid1() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("0"), new BigInteger("10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void LASIteratorConstructorTestInvalid2() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("99"), new BigInteger("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void LASIteratorConstructorTestInvalid3() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("9".repeat(101)));
    }

    @Test
    public void LASIteratorHasNextTest() {
        // since the iterator will return the passed in value at first,
        // we need to burn off the first one to get to the next
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("1"), new BigInteger("100"));
        las.next();
        assertTrue(las.hasNext());

        las = new LookAndSayIterator(new BigInteger("1"));
        las.next();
        assertTrue(las.hasNext());

        las = new LookAndSayIterator(new BigInteger("9".repeat(100)));
        las.next();
        assertTrue(las.hasNext());

        las = new LookAndSayIterator();
        las.next();
        assertTrue(las.hasNext());

        las = new LookAndSayIterator(new BigInteger("123456789".repeat(11)));
        las.next();
        assertFalse(las.hasNext());
    }

    @Test
    public void LASIteratorNextTest() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("1"));
        assertEquals(new BigInteger("1"), las.next());
        assertEquals(new BigInteger("11"), las.next());
        assertEquals(new BigInteger("21"), las.next());
        assertEquals(new BigInteger("1211"), las.next());
        assertEquals(new BigInteger("111221"), las.next());
        assertEquals(new BigInteger("312211"), las.next());
        assertEquals(new BigInteger("13112221"), las.next());
        assertEquals(new BigInteger("1113213211"), las.next());
    }

    @Test
    public void LASIteratorNextTest2() {
        Iterator<BigInteger> iterator = new LookAndSayIterator();
        BigInteger previous = new BigInteger("1");
        iterator.next(); // burn off the first one
        while (iterator.hasNext()) {
            previous = iterator.next();
        }
        previous = iterator.next();
        assertTrue("The iterator ended but the last number was not more than " + "100 digits long",
                previous.toString().length() > 100);
    }

    @Test
    public void LASIteratorPrevTest() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("1113213211"));
        assertEquals(new BigInteger("1113213211"), las.prev());
        assertEquals(new BigInteger("13112221"), las.prev());
        assertEquals(new BigInteger("312211"), las.prev());
        assertEquals(new BigInteger("111221"), las.prev());
        assertEquals(new BigInteger("1211"), las.prev());
        assertEquals(new BigInteger("21"), las.prev());
        assertEquals(new BigInteger("11"), las.prev());
        assertEquals(new BigInteger("1"), las.prev());
    }

    @Test
    public void LASIteratorPrevTest2() {
        LookAndSayIterator las = new LookAndSayIterator();
        las.next();
        las.next();
        BigInteger num1 = las.next();
        las.prev();
        BigInteger num2 = las.prev();
        assertEquals(num1, num2);
    }

    @Test
    public void LASHasPreviousTest() {
        LookAndSayIterator las = new LookAndSayIterator(new BigInteger("1113213211"));
        las.next();
        assertTrue(las.hasPrevious());
        las = new LookAndSayIterator(new BigInteger("1"));
        assertFalse(las.hasPrevious());
        las = new LookAndSayIterator(new BigInteger("9".repeat(99)));
        assertFalse(las.hasPrevious());
    }
}
