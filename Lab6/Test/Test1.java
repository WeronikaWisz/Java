import java.io.IOException;
import java.util.Locale;

public class Test1 {
    //wyznacza i wypisuje listę sąsiadów
    @org.junit.Test
    public void test1() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        AdminUnitList test = adunlist.selectByName("Potoka",true); //szukamy sąsiadów np. dla miejscowosci Potoka
        AdminUnitList neigh = adunlist.getNeighbors(test.units.get(0),15);
        double t1 = System.nanoTime()/1e6;
        for(AdminUnit u:neigh.units){
            System.out.print(u.toString()+"\n");
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

    //wyszukiwanie jednostek na wybranym poziomie hierarchii
    @org.junit.Test
    public void test2() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        AdminUnitList districts = new AdminUnitList();
        for(AdminUnit u:adunlist.units){
            if (u.adminLevel==6) {
                districts.units.add(u);
                System.out.print(u.toString()+"\n");
            }
        }
    }

    //przeszukiwanie sasiadow rtree
    @org.junit.Test
    public void test3() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        AdminUnitList test = adunlist.selectByName("Potoka",true);
        AdminUnitList neigh = adunlist.getNeighborsRtree(test.units.get(0),15);
        double t1 = System.nanoTime()/1e6;
        for(AdminUnit u:neigh.units){
            System.out.print(u.toString()+"\n");
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

}
