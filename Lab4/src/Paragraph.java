import java.io.PrintStream;

public class Paragraph{
    String content;

    Paragraph(String content){
        this.content = content;
    }

    public Paragraph() {
       this.content = "";
    }

    Paragraph setContent(String content){
        this.content = content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n",content);
    }

}
