package test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import questions.*;

public class QuestionTest {
    /* This class tests the function defined in the Question interface
     * As the Question is an interface, and GenericQuestion is a abstract class,
     * the test will be done using TrueFalse class
     *
     * Since the implementation of the answer() and toString() method varies drastically between the classes,
     * the test of this method is not covered in this test class
     */

    @Before
    public void setUp() {
    }

    @Test
    public void testGetText() {
        TrueFalse q = new TrueFalse("Is the sky blue?", "True");
        Question question = (Question) q;
        assertEquals("Is the sky blue?", question.getText());

        q = new TrueFalse("", "False");
        question = (Question) q;
        assertEquals("", question.getText());
    }

    @Test
    public void testToString() {
        TrueFalse q = new TrueFalse("Is the sky blue?", "True");
        Question question = (Question) q;
        assertEquals("Q: Is the sky blue?\nA: True\n", question.toString());

        q = new TrueFalse("", "False");
        question = (Question) q;
        assertEquals("Q: \nA: False\n", question.toString());
    }

    @Test
    public void testCompareTo() {
        TrueFalse q1 = new TrueFalse("1", "True");
        TrueFalse q2 = new TrueFalse("2", "True");
        MultipleChoice q3 = new MultipleChoice("3", "1", "1", "2", "3", "4", "5", "6", "7", "8");
        MultipleChoice q4 = new MultipleChoice("4", "1", "1", "2", "3", "4", "5", "6");
        MultipleSelect q5 = new MultipleSelect("5", "1 2", "1", "2", "3");
        MultipleSelect q6 = new MultipleSelect("6", "1 2 3", "1", "2", "3", "4", "5");
        Likert q7 = new Likert("7");
        Likert q8 = new Likert("8");

        ArrayList<Question> qlist = new ArrayList<Question>();
        qlist.add(q2);
        qlist.add(q4);
        qlist.add(q6);
        qlist.add(q8);
        qlist.add(q1);
        qlist.add(q3);
        qlist.add(q5);
        qlist.add(q7);

        // verify the initial order
        assertEquals("2", qlist.get(0).getText());
        assertEquals("4", qlist.get(1).getText());
        assertEquals("6", qlist.get(2).getText());
        assertEquals("8", qlist.get(3).getText());
        assertEquals("1", qlist.get(4).getText());
        assertEquals("3", qlist.get(5).getText());
        assertEquals("5", qlist.get(6).getText());
        assertEquals("7", qlist.get(7).getText());


        qlist.sort(null);

        // verify the order after sorting
        assertEquals("1", qlist.get(0).getText());
        assertEquals("2", qlist.get(1).getText());
        assertEquals("3", qlist.get(2).getText());
        assertEquals("4", qlist.get(3).getText());
        assertEquals("5", qlist.get(4).getText());
        assertEquals("6", qlist.get(5).getText());
        assertEquals("7", qlist.get(6).getText());
        assertEquals("8", qlist.get(7).getText());
    }
}
