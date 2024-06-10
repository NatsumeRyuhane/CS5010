package document;

import document.element.*;

public class WordCountVisitor implements DocumentVisitor<Integer> {
    private int wordCount = 0;

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitBasicText(BasicText current) {
        String text = current.getText();
        String[] words = text.split("\\s+");
        wordCount += words.length;
        return wordCount;
    }

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitBoldText(BoldText current) {
        return this.visitBasicText((BasicText) current);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitHeading(Heading current) {
        return this.visitBasicText((BasicText) current);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitHyperText(HyperText current) {
        return this.visitBasicText((BasicText) current);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitItalicText(ItalicText current) {
        return this.visitBasicText((BasicText) current);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public Integer visitParagraph(Paragraph current) {
        for (BasicText e : current.getContent()) {
            this.visitBasicText(e);
        }

        return this.wordCount;
    }
}
