package questions;

public interface Question extends Comparable<Question> {

    /**
     * This method is used to answer the question.
     * @param answer Tests the provided answer against the correct answer.
     * @return The result of the test.
     */
    String answer(String answer);

    /**
     * This method is used to get the text of the question.
     * @return The text of the question.
     */
    String getText();

    /**
     * This method is used to get the string representation of the question.
     * @return The string representation of the question.
     */
    @Override
    String toString();

    /**
     * This method is used to compare the current question with another question.
     * @param question The question to compare to.
     * @return A negative integer, zero, or a positive integer as this question is less than, equal to, or greater than the specified question.
     */
    @Override
    int compareTo(Question question);
}