package questions;
import java.util.Arrays;

public class MultipleSelect extends GenericQuestion {
    private String[] options = null;
    private int[] answerArray;

    /**
     * Array of overloaded constructors for the MultipleSelect class, for 3 to 8 options
     */
    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3};
    }

    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3, String option4) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3, option4};
    }

    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3, String option4, String option5) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3, option4, option5};
    }

    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3, option4, option5, option6};
    }

    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6, String option7) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3, option4, option5, option6, option7};
    }

    public MultipleSelect(String questionText, String answer, String option1, String option2, String option3, String option4, String option5, String option6, String option7, String option8) {
        super(questionText, answer);
        this.answerArray = parseAnswerString(answer);
        this.options = new String[]{option1, option2, option3, option4, option5, option6, option7, option8};
    }

    /**
     * Parses the answer string provided into a sorted array of integers
     * This method is to compare the answers regardless of the order of the selected options
     *
     * @param answer the answer string
     * @return the answer array
     * @throws NumberFormatException if the answer string contains non-integer characters
     * @throws IllegalArgumentException if the answer string contains invalid integers
     */
    public int[] parseAnswerString(String answer) {
        String[] stringAnswerArray = answer.split(" ");
        int[] intAnswerArray = new int[stringAnswerArray.length];
        for (int i = 0; i < stringAnswerArray.length; i++) {
            try {
                int option = Integer.parseInt(stringAnswerArray[i]);
                if (this.options != null) {
                    if (option < 1 || option > this.options.length) {
                        throw new IllegalArgumentException("Invalid input: " + option);
                    }
                }
                else {
                    if (option < 1 || option > 8) {
                        throw new IllegalArgumentException("Invalid input: " + option);
                    }
                }
                intAnswerArray[i] = option;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input: " + stringAnswerArray[i]);
            }
        }

        Arrays.sort(intAnswerArray);
        return intAnswerArray;
    }

    /**
     * Evaluates the answer string
     *
     * @param answer the user-input answer string
     * @return "Correct" if the answer is correct, "Incorrect" otherwise
     * @throws NumberFormatException if the answer string contains non-integer characters
     */
    public String answer(String answer) {
        try {
            int[] inputAnswerArray = parseAnswerString(answer);
            if (Arrays.equals(inputAnswerArray, this.answerArray)) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        }
        catch (NumberFormatException e) {
            return "Incorrect";
        }
        catch (IllegalArgumentException e) {
            return "Incorrect";
        }
    }

    @Override
    public int compareTo(Question question) {
        if (question instanceof MultipleSelect) {
            MultipleSelect multipleSelectQuestion = (MultipleSelect) question;
            return this.getQuestionText().compareTo(multipleSelectQuestion.getQuestionText());
        } else if (question instanceof TrueFalse || question instanceof MultipleChoice) {
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
