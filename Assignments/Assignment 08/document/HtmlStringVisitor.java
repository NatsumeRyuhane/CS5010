package document;

import document.element.*;

public class HtmlStringVisitor implements DocumentVisitor<String> {

    StringBuilder HTMLRepresentation = new StringBuilder();
    boolean isEmpty = true;

    /**
     * @param current
     * @return
     */
    @Override
    public String visitBasicText(BasicText current) {
        this.HTMLRepresentation.append(current.getText());
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitBoldText(BoldText current) {
        this.HTMLRepresentation.append("<b>").append(current.getText()).append("</b>");
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitHeading(Heading current) {
        this.HTMLRepresentation.append("<h").append(current.getLevel()).append(">");
        this.HTMLRepresentation.append(current.getText());
        this.HTMLRepresentation.append("</h").append(current.getLevel()).append(">");
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitHyperText(HyperText current) {
        this.HTMLRepresentation.append("<a href=\"").append(current.getUrl()).append("\">");
        this.HTMLRepresentation.append(current.getText());
        this.HTMLRepresentation.append("</a>");
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitItalicText(ItalicText current) {
        this.isEmpty = false;
        this.HTMLRepresentation.append("<i>").append(current.getText()).append("</i>");
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }

    /**
     * @param current
     * @return
     */
    @Override
    public String visitParagraph(Paragraph current) {
        this.HTMLRepresentation.append("<p>");
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
        this.HTMLRepresentation.append("</p>");
        this.HTMLRepresentation.append("\n");
        return this.HTMLRepresentation.substring(0, this.HTMLRepresentation.length() - 1);
    }
}
