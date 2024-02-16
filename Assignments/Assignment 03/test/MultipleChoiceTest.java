package test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import questions.MultipleChoice;

public class MultipleChoiceTest {
    @Before
    public void setUp() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        MultipleChoice mc = new MultipleChoice("TestQuestion", "apple", "OPT1", "OPT2", "OPT3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        MultipleChoice mc = new MultipleChoice("TestQuestion", "0", "OPT1", "OPT2", "OPT3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        MultipleChoice mc = new MultipleChoice("TestQuestion", "5", "OPT1", "OPT2", "OPT3");
    }

    @Test
    public void testConstructor4() {
        MultipleChoice mc = new MultipleChoice("TestQuestion", "5", "OPT1", "OPT2", "OPT3", "OPT4", "OPT5");
    }

    @Test
    public void testAnswer() {
        MultipleChoice mc = new MultipleChoice("TestQuestion", "1", "OPT1", "OPT2", "OPT3");
        assertEquals("Correct", mc.answer("1"));
        assertEquals("Incorrect", mc.answer("2"));
        assertEquals("Incorrect", mc.answer("3"));
        assertEquals("Incorrect", mc.answer("4"));
        assertEquals("Incorrect", mc.answer("0"));
        assertEquals("Incorrect", mc.answer("5"));
        assertEquals("Incorrect", mc.answer(""));
        assertEquals("Incorrect", mc.answer("apple"));

        mc = new MultipleChoice("TestQuestion", "3", "OPT1", "OPT2", "OPT3");
        assertEquals("Correct", mc.answer("3"));
        assertEquals("Incorrect", mc.answer("1"));
        assertEquals("Incorrect", mc.answer("2"));
        assertEquals("Incorrect", mc.answer("4"));
        assertEquals("Incorrect", mc.answer("0"));
        assertEquals("Incorrect", mc.answer("5"));
        assertEquals("Incorrect", mc.answer(""));
        assertEquals("Incorrect", mc.answer("apple"));
    }
}
