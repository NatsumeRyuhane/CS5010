package document;

import java.util.ArrayList;
import java.util.List;

import document.element.*;

public class Document {
  
  private List<TextElement> content;
  
  public Document() {
    content = new ArrayList<>();
  }

  public void add(TextElement e) {
    content.add(e);
  }

  public <R> R accept(DocumentVisitor<R> visitor) {
    R result = null;
    for (TextElement e : content) {
      if (e instanceof BoldText) {
        result = visitor.visitBoldText((BoldText) e);
      } else if (e instanceof Heading) {
        result = visitor.visitHeading((Heading) e);
      } else if (e instanceof HyperText) {
        result = visitor.visitHyperText((HyperText) e);
      } else if (e instanceof ItalicText) {
        result = visitor.visitItalicText((ItalicText) e);
      } else if (e instanceof BasicText) {
        result = visitor.visitBasicText((BasicText) e);
      } else if (e instanceof Paragraph) {
        result = visitor.visitParagraph((Paragraph) e);
      }
    }
    return result;
  }

  public int countWords() {
    Integer result = accept(new WordCountVisitor());
    if (result == null) {
      return 0;
    }
    return result;
  }

  public String toText(DocumentVisitor<String> visitor) {
    if (visitor == null) {
      throw new IllegalArgumentException("Visitor cannot be null");
    }

    String str = accept(visitor);
    if (str == null) {
      return "";
    }

    return str;
  }
}
