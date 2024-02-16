package test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import questions.MultipleSelect;

public class MultipleSelectTest {
    @Before
    public void setUp() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        MultipleSelect ms = new MultipleSelect("TestQuestion", "1 2 apple", "OPT1", "OPT2", "OPT3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        MultipleSelect ms = new MultipleSelect("TestQuestion", "1 0", "OPT1", "OPT2", "OPT3");
    }

    @Test
    public void testConstructor3() {
        MultipleSelect ms = new MultipleSelect("TestQuestion", "1 3 5", "OPT1", "OPT2", "OPT3");
    }

    @Test
    public void testAnswer() {
        MultipleSelect ms = new MultipleSelect("TestQuestion", "1 3 5", "OPT1", "OPT2", "OPT3", "OPT4", "OPT5");
        assertEquals("Correct", ms.answer("1 3 5"));
        assertEquals("Correct", ms.answer("5 3 1"));
        assertEquals("Correct", ms.answer("1 5 3"));
        assertEquals("Correct", ms.answer("3 1 5"));
        assertEquals("Correct", ms.answer("3 5 1"));
        assertEquals("Correct", ms.answer("5 1 3"));
        assertEquals("Incorrect", ms.answer("1 2 3"));
        assertEquals("Incorrect", ms.answer("1 2 3 4"));
        assertEquals("Incorrect", ms.answer("1 2 3 4 5"));
        assertEquals("Incorrect", ms.answer("apple"));
        assertEquals("Incorrect", ms.answer(""));
    }
}
