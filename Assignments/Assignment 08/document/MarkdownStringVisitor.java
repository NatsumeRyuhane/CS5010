package document;

import document.element.*;

public class MarkdownStringVisitor implements DocumentVisitor<String> {

    StringBuilder markdownRepresentation = new StringBuilder();
    boolean isEmpty = true;

    /**
     * @param current
     * @return
     */
    @Override
    public String visitBasicText(BasicText current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        this.markdownRepresentation.append(current.getText());
        return this.markdownRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitBoldText(BoldText current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        this.markdownRepresentation.append("**").append(current.getText()).append("**");
        return this.markdownRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitHeading(Heading current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        switch (current.getLevel()) {
            case 1:
                this.markdownRepresentation.append("# ");
                break;
            case 2:
                this.markdownRepresentation.append("## ");
                break;
            case 3:
                this.markdownRepresentation.append("### ");
                break;
            case 4:
                this.markdownRepresentation.append("#### ");
                break;
            case 5:
                this.markdownRepresentation.append("##### ");
                break;
            case 6:
                this.markdownRepresentation.append("###### ");
                break;
        }
        this.markdownRepresentation.append(current.getText());
        return this.markdownRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitHyperText(HyperText current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        this.markdownRepresentation.append("[").append(current.getText()).append("](").append(current.getUrl()).append(")");
        return this.markdownRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitItalicText(ItalicText current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        this.markdownRepresentation.append("*").append(current.getText()).append("*");
        return this.markdownRepresentation.toString();
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitParagraph(Paragraph current) {
        if (!this.isEmpty) {
            this.markdownRepresentation.append("\n");
        }
        this.isEmpty = false;
        for (BasicText e : current.getContent()) {
            if (e instanceof BoldText) {
                this.visitBoldText((BoldText) e);
            } else if (e instanceof Heading) {
                this.visitHeading((Heading) e);
            } else if (e instanceof HyperText) {
                this.visitHyperText((HyperText) e);
            } else if (e instanceof ItalicText) {
                this.visitItalicText((ItalicText) e);
            } else if (e != null) {
                this.visitBasicText(e);
            }
        }

        return this.markdownRepresentation.toString();
    }
}
