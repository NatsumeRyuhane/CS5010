package questions;

public class Likert extends GenericQuestion {
    public Likert(String questionText) {
        // Likert questions have no answer, so the answer is set to an empty string
        super(questionText, "");
    }

    public String answer(String answer) {
        if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") || answer.equals("5")) {
            return "Correct";
        } else {
            return "Incorrect";
        }
    }

    @Override
    public int compareTo(Question question) {
        if (question instanceof Likert){
            Likert likertQuestion = (Likert) question;
            return this.getQuestionText().compareTo(likertQuestion.getQuestionText());
        }
        else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Q: " + this.getQuestionText() + "\n" + "A: " + this.getAnswer() + "\n";
    }
}
