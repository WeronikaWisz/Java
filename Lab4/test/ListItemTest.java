import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ListItemTest {

    @Test
    public void writeHTML() throws Exception {
        String item = "java";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ListItem(item).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<li"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains(item));

    }
}