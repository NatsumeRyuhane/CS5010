package questions;

/**
 * This abstract class represents a generic question.
 * It implements the Question interface.
 * The necessary instance variables are defined in this abstract class.
 */
public abstract class GenericQuestion implements Question {
    private String questionText;
    private String answer;

    /**
     * Constructs a new GenericQuestion with the specified question text and answer.
     * @param questionText the text of the question
     * @param answer the answer to the question
     */
    public GenericQuestion(String questionText, String answer) {
        this.setQuestionText(questionText);
        this.setAnswer(answer);
    }

    /**
     * Returns the text of the question.
     * @return the text of the question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the text of the question.
     * @param questionText the new text of the question
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Returns the answer to the question.
     * @return the answer to the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer to the question.
     * @param answer the new answer to the question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Returns the text of the question.
     * @return the text of the question
     */
    public String getText() { return this.getQuestionText(); }


    public abstract String answer(String answer);

    @Override
    public abstract int compareTo(Question question);

    @Override
    public abstract String toString();
}