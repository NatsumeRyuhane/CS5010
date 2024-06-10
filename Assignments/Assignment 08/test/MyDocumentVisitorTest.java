package test;

import org.junit.Test;
import static org.junit.Assert.*;

import document.element.*;
import document.*;

public class MyDocumentVisitorTest {
    @Test
    public void testWordCountVisitor() {
        Document doc = new Document();
        doc.add(new BasicText("Hello, world!"));
        doc.add(new BoldText("Hello, world!"));
        doc.add(new Heading("Hello, world!", 1));
        doc.add(new HyperText("Hello, world!", "http://example.com"));
        doc.add(new ItalicText("Hello, world!"));

        WordCountVisitor visitor = new WordCountVisitor();
        int wc = doc.accept(visitor);

        assertEquals(10, wc);
    }

    @Test
    public void testHTMLStringVisitor() {
        Document doc = new Document();
        doc.add(new BasicText("Hello, world!"));
        doc.add(new BoldText("Hello, world!"));
        doc.add(new Heading("Hello, world!", 1));
        doc.add(new HyperText("Hello, world!", "http://example.com"));
        doc.add(new ItalicText("Hello, world!"));
        Paragraph p = new Paragraph();
        p.add(new ItalicText("Hello, world!"));
        p.add(new BoldText("Hello, world!"));
        doc.add(p);

        HtmlStringVisitor visitor = new HtmlStringVisitor();
        String html = doc.toText(visitor);

        assertEquals("Hello, world!\n<b>Hello, world!</b>\n<h1>Hello, world!</h1>\n<a href=\"http://example.com\">Hello, world!</a>\n<i>Hello, world!</i>\n<p><i>Hello, world!</i>\n<b>Hello, world!</b>\n</p>", html);
    }

    @Test
    public void testMarkdownStringVisitor() {
        Document doc = new Document();
        doc.add(new BasicText("Hello, world!"));
        doc.add(new BoldText("Hello, world!"));
        doc.add(new Heading("Hello, world!", 1));
        doc.add(new HyperText("Hello, world!", "http://example.com"));
        doc.add(new ItalicText("Hello, world!"));

        MarkdownStringVisitor visitor = new MarkdownStringVisitor();
        String markdown = doc.toText(visitor);

        assertEquals("Hello, world!\n**Hello, world!**\n# Hello, world!\n[Hello, world!](http://example.com)\n*Hello, world!*", markdown);
    }

    @Test
    public void testPlainTextVisitor() {
        Document doc = new Document();
        doc.add(new BasicText("Hello, world!"));
        doc.add(new BoldText("Hello, world!"));
        doc.add(new Heading("Hello, world!", 1));
        doc.add(new HyperText("Hello, world!", "http://example.com"));
        doc.add(new ItalicText("Hello, world!"));

        BasicStringVisitor visitor = new BasicStringVisitor();
        String plain = doc.toText(visitor);

        assertEquals("Hello, world! Hello, world! Hello, world! Hello, world! Hello, world!", plain);
    }
}
