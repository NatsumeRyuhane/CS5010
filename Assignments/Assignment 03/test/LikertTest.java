package test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import questions.Likert;

public class LikertTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testAnswer() {
        Likert likert = new Likert("TestQuestion");
        assertEquals("Correct", likert.answer("5"));
        assertEquals("Correct", likert.answer("4"));
        assertEquals("Correct", likert.answer("3"));
        assertEquals("Correct", likert.answer("2"));
        assertEquals("Correct", likert.answer("1"));
        assertEquals("Incorrect", likert.answer("0"));
        assertEquals("Incorrect", likert.answer("6"));
        assertEquals("Incorrect", likert.answer(""));
        assertEquals("Incorrect", likert.answer("apple"));
    }

    @Test
    public void testToString() {
        Likert likert = new Likert("TestQuestion");
        assertEquals("Q: TestQuestion\nA:", likert.toString());
    }
}
