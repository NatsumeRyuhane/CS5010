package questions;

public class TrueFalse extends GenericQuestion implements Question{
    public TrueFalse(String question, String answer) {
        super(question, answer);

        if (!answer.equals("True") && !answer.equals("False")) {
            throw new IllegalArgumentException("Answer must be 'True' or 'False'");
        }
    }

    public String answer(String answer) {
        if (answer.equals(this.getAnswer())) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }

    @Override
    public int compareTo(Question question) {
        if (question instanceof TrueFalse) {
            TrueFalse trueFalseQuestion = (TrueFalse) question;
            return this.getQuestionText().compareTo(trueFalseQuestion.getQuestionText());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Q: " + this.getQuestionText() + "\n" + "A: " + this.getAnswer() + "\n";
    }
}
