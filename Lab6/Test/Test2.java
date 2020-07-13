import java.io.IOException;
import java.util.Locale;
import java.util.function.Predicate;

public class Test2 {
    @org.junit.Test
    public void test1() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        Predicate<AdminUnit> p = a->a.name.startsWith("K");
        AdminUnitList unitsStartingWithK = adunlist.filter(p).sortInPlaceByName();
        for(AdminUnit u:unitsStartingWithK.units){
            System.out.print(u.toString()+"\n");
        }
    }

    @org.junit.Test
    public void test2() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        Predicate<AdminUnit> p = a->a.adminLevel==6;
        Predicate<AdminUnit> p2 = a->a.parent.name.equals("województwo małopolskie");
        AdminUnitList units1 = adunlist.filter(p.and(p2));
        for(AdminUnit u:units1.units){
            System.out.print(u.toString()+"\n");
        }
    }

    @org.junit.Test
    public void test3() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        Predicate<AdminUnit> p = a->a.density<10;
        Predicate<AdminUnit> p2 = a->a.density>100;
        AdminUnitList units1 = adunlist.filter(p.or(p2));
        for(AdminUnit u:units1.units){
            System.out.print(u.toString()+"\n");
        }
    }

    @org.junit.Test
    public void test4() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        Predicate<AdminUnit> p = a->a.name.startsWith("K");
        AdminUnitList units1 = adunlist.filter(p.negate(),10,20);
        for(AdminUnit u:units1.units){
            System.out.print(u.toString()+"\n");
        }
    }

    @org.junit.Test
    public void test5() throws IOException {
        AdminUnitList adunlist = new AdminUnitList();
        adunlist.read("admin-units.csv");
        Predicate<AdminUnit> p = a->a.adminLevel==6;
        Predicate<AdminUnit> p2 = adminUnit -> {
            if (adminUnit.children == null)
                return false;
            if (adminUnit.children.size() > 3)
                return true;
            else return false;
        };
        AdminUnitList units1 = adunlist.filter(p.and(p2),1,6);
        for(AdminUnit u:units1.units){
            System.out.print(u.toString()+"\n");
        }
    }

}
