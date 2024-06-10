package document;

import document.element.*;

public class BasicStringVisitor implements DocumentVisitor<String> {
    StringBuilder stringRepresentation = new StringBuilder();
    boolean isEmpty = true;

    /**
     * @param current
     * @return
     */
    public String visitBasicText(BasicText current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    public String visitBoldText(BoldText current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    public String visitHeading(Heading current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    public String visitHyperText(HyperText current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    public String visitItalicText(ItalicText current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    public String visitParagraph(Paragraph current) {
        if (!this.isEmpty) {
            this.stringRepresentation.append(" ");
        }
        this.isEmpty = false;
        this.stringRepresentation.append(current.getText());
        return this.stringRepresentation.toString();
    }
}
