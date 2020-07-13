import javax.swing.plaf.ColorUIResource;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    String[] current;

    /*
     * @param filename  - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename,delimiter,true);
        //reader = new BufferedReader(new FileReader(filename));
        //this.delimiter = delimiter;
    }

    public CSVReader(String filename) throws IOException {
        this(filename,",",true);
        //reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        try {

            String line = reader.readLine();
            if (line == null) {
                return;
            }
            // podziel na pola
            String[] header = line.split(delimiter);
            // przetwarzaj dane w wierszu
            for (int i = 0; i < header.length; i++) {
                // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
                columnLabels.add(header[i]);
                columnLabelsToInt.put(header[i], i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean next() throws IOException {
        try {
            // czyta następny wiersz, dzieli na elementy i przypisuje do current
            String line = reader.readLine();
            if (line == null) {
                return false;
            } else {
                current = line.split(delimiter);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) throws NullPointerException{
        if (columnIndex > this.columnLabels.size()-1 || columnIndex<0)
            throw new NullPointerException(String.format("There is only %d columns", columnLabels.size()));
         return (columnIndex > this.getRecordLength()-1 || this.current[columnIndex].isEmpty());
    }

    boolean isMissing(String columnLabel) throws NullPointerException{
        int columnIndex = columnLabelsToInt.get(columnLabel);
        return this.isMissing(columnIndex);
    }

    String get(int columnIndex) throws NullPointerException{
        if (this.isMissing(columnIndex)) {
            return null;
        }
        else{return current[columnIndex];}
    }

    String get(String columnLabel) throws NullPointerException{
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if(this.isMissing(columnIndex)){
            return null;
        }
        else{return current[columnIndex];}
    }

    int getInt(int columnIndex) throws NullPointerException{
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Integer.parseInt(current[columnIndex]);}
    }

    int getInt(String columnLabel) throws NullPointerException{
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Integer.parseInt(current[columnIndex]);}
    }

    long getLong(int columnIndex) throws NullPointerException{
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Long.parseLong(current[columnIndex]);}
    }

    long getLong(String columnLabel) throws NullPointerException{
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Long.parseLong(current[columnIndex]);}
    }

    double getDouble(int columnIndex) throws NullPointerException{
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Double.parseDouble(current[columnIndex]);}
    }

    double getDouble(String columnLabel) throws NullPointerException{
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if (this.isMissing(columnIndex)) {
            return 0;
        }
        else{return Double.parseDouble(current[columnIndex]);}
    }

    LocalTime getTime(int columnIndex, String format) throws NullPointerException{
        if(this.isMissing(columnIndex)){
            return LocalTime.parse("0");
        }
        LocalTime time;
        if(format.equals("HH:mm")) {
            time = LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            time = LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return time;
    }

    LocalDate getDate(int columnIndex, String format) throws NullPointerException{
        if(this.isMissing(columnIndex)){
            return LocalDate.parse("0");
        }
        LocalDate date;
        if(format.equals("yyyy-MM-dd")) {
            date =LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        else {
            date = LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        return date;
    }

    //wyrażenie regularne przygotowane dla funkcji split
    protected String getSplittingRegex() {
        return String.format("%s(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",delimiter);
    }
}
