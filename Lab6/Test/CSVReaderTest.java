import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

import static org.junit.Assert.*;

public class CSVReaderTest {
    @org.junit.Test
    public void test1() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String surname = reader.get("nazwisko");
            String street = reader.get(3);
            int idhouse = reader.getInt(4);
            int idapartment = reader.getInt(5);

            System.out.printf(Locale.US,"%d %s %s %d %d \n",id, surname, street, idhouse, idapartment);
        }
    }

    @org.junit.Test
    public void test2() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            long idhouse = reader.getLong(4);
            String name = reader.get("nazwisko");

            System.out.printf(Locale.US,"%d %d %s \n",id, idhouse, name);
        }
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void test3() throws IOException {
        String text = "a,b,c\n123.4,567.8,";
        CSVReader reader = new CSVReader(new StringReader(text), ",", true);
        String check = reader.get(2);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void test41() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv",";",true);
        reader.next();
        int check = reader.getInt(6);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void test42() throws IOException {
        CSVReader reader = new CSVReader("with-header.csv",";",true);
        reader.next();
        int check = reader.getInt("exception");
    }

    @org.junit.Test
    public void test5() throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text), ",", true);
        while(reader.next()){
            String name = reader.get(0);
            System.out.printf(Locale.US,"%s \n", name);
        }
    }


}