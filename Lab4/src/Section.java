import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section{
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String title){
        this.title = title;
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));
        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<div>\n<h2>");
        out.print(title);
        out.print("</h2>\n");
        for(Paragraph p : paragraphs) {
            p.writeHTML(out);
        }
        out.print("</div>\n");
    }
}
