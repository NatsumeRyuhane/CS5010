package questions;

public class MultipleChoice extends GenericQuestion {
    private final String[] options;

    // Overloaded constructors for different number of options from 3 to 8
    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3) {
        super(questionText, answer);

        // validate if the answer provided is valid
        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 3) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3};
    }

    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3, String option4) {
        super(questionText, answer);

        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 4) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3, option4};
    }

    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3, String option4, String option5) {
        super(questionText, answer);

        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 5) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3, option4, option5};
    }

    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6) {
        super(questionText, answer);

        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 6) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3, option4, option5, option6};
    }

    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6, String option7) {
        super(questionText, answer);

        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 7) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3, option4, option5, option6, option7};
    }

    public MultipleChoice(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6, String option7, String option8) {
        super(questionText, answer);

        try {
            int option = Integer.parseInt(answer);
            if (option < 1 || option > 8) {
                throw new IllegalArgumentException("Invalid input: " + answer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + answer);
        }

        this.options = new String[]{option1, option2, option3, option4, option5, option6, option7, option8};
    }

    public String answer(String answer) {
        if (answer.equals(this.getAnswer())) {
            return "Correct";
        }
        else {
            return "Incorrect";
        }
    }

    @Override
    public int compareTo(Question question) {
        if (question instanceof MultipleChoice) {
            MultipleChoice multipleChoiceQuestion = (MultipleChoice) question;
            return this.getQuestionText().compareTo(multipleChoiceQuestion.getQuestionText());
        } else if (question instanceof TrueFalse) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder optionsString = new StringBuilder();

        int i = 1;
        for (String option : this.options) {
            optionsString
                    .append(" ")
                    .append(i)
                    .append(". ")
                    .append(option)
                    .append("\n");

            i++;
        }
        return "Q: " + this.getQuestionText() + "\n" + optionsString + "A: " + this.getAnswer() + "\n";
    }
}
