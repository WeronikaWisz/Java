import java.io.IOException;
import java.util.function.Predicate;

public class QueryTest {
    @org.junit.Test
    public void test() throws IOException {
        AdminUnitList list = new AdminUnitList();
        list.read("admin-units.csv");
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        AdminUnitList q = query.execute();
        for(AdminUnit u:q.units){
            System.out.print(u.toString()+"\n");
        }
    }
}
