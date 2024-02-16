package test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import questions.TrueFalse;

public class TrueFalseTest {
    @Before
    public void setUp() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        TrueFalse tf = new TrueFalse("TestQuestion", "apple");
    }

    @Test
    public void testConstructor2() {
        TrueFalse tf = new TrueFalse("TestQuestion", "True");
    }

    @Test
    public void testConstructor3() {
        TrueFalse tf = new TrueFalse("TestQuestion", "False");
    }

    @Test
    public void testAnswer() {
        TrueFalse tf = new TrueFalse("TestQuestion", "True");
        assertEquals("Correct", tf.answer("True"));
        assertEquals("Incorrect", tf.answer("False"));
        assertEquals("Incorrect", tf.answer("true"));
        assertEquals("Incorrect", tf.answer("false"));
        assertEquals("Incorrect", tf.answer("apple"));
        assertEquals("Incorrect", tf.answer(""));
    }
}
