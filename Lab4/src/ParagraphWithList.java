import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph{

    UnorderedList list = new UnorderedList();

    ParagraphWithList() {
        super();
    }

    ParagraphWithList(String content) {
        super(content);
    }

    ParagraphWithList setContent(String content){
        super.setContent(content);
        return this;
    }

    ParagraphWithList addListItem(String item){
        list.addItem(new ListItem(item));
        return this;
    }

    void writeHTML(PrintStream out){
        super.writeHTML(out);
        list.writeHTML(out);
    }

}
