import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {

    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(ListItem item){
        items.add(item);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for(ListItem i:items) {
            i.writeHTML(out);
        }
        out.print("</ul>\n");
    }
}
