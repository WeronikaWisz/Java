import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }


    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        sections.add(section);
        return section;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.print("<html>\n<body>\n");
        photo.writeHTML(out);
        out.printf("<h1>%s</h1>\n", title);
        for(Section s : sections) {
            s.writeHTML(out);
        }
        out.print("</body>\n</html>");
    }
}
