import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {


       CSVReader reader = new CSVReader("with-header.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String name = reader.get("nazwisko");
            String miejscowosc = reader.get(3);

            System.out.printf(Locale.US,"%d %s %s \n",id, name, miejscowosc);
        }

        CSVReader reader2 = new CSVReader("admin-units.csv",",",true);
        while(reader2.next()){
            long id = reader2.getLong("id");
            String parent = "";
            if(!(reader2.isMissing("parent"))) {
                parent = reader2.get("parent");
            }
            String name = reader2.get("name");
            int admin_level = 0;
            if(!(reader2.isMissing("admin_level"))) {
                admin_level = reader2.getInt("admin_level");
            }
            double population = 0;
            if(!(reader2.isMissing("population"))) {
                population = reader2.getDouble("population");
            }
            double area = reader2.getDouble("area");
            double density = 0;
            if(!(reader2.isMissing("density"))) {
                density = reader2.getDouble("density");
            }

            System.out.printf(Locale.US,"%d %s %s %d %f %f %f \n",id, parent, name, admin_level, population, area, density);
        }
    }
}
