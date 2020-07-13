import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class DocumentTest {

    @Test
    public void writeHTML() throws Exception {
        String title = "cv";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Document(title)
                .setPhoto("...")
                .writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<html>"));
        assertTrue(result.contains("</html>"));
        assertTrue(result.contains("<body>"));
        assertTrue(result.contains("</body>"));
        assertTrue(result.contains("<h1>"));
        assertTrue(result.contains("</h1>"));
        assertTrue(result.contains(title));

    }
}