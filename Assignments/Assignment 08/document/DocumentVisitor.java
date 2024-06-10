package document;

import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;

public interface DocumentVisitor<R> {
  
  public R visitBasicText(BasicText current);
  
  public R visitBoldText(BoldText current);

  public R visitHeading(Heading current);

  public R visitHyperText(HyperText current);

  public R visitItalicText(ItalicText current);

  public R visitParagraph(Paragraph current);
}
