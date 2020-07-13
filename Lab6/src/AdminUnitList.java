import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> adun = new HashMap<>();
    Map<AdminUnit,Long> adun2 = new HashMap<>();
    Map<AdminUnit,Long> parents = new HashMap<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);
        while (reader.next()) {
            boolean hasbb=false;
            String name = reader.get("name");
            int admin_level = 0;
            if (!(reader.isMissing("admin_level"))) {
                admin_level = reader.getInt("admin_level");
            }
            double population = 0;
            if (!(reader.isMissing("population"))) {
                population = reader.getDouble("population");
            }
            double area = reader.getDouble("area");
            double density = 0;
            if (!(reader.isMissing("density"))) {
                density = reader.getDouble("density");
            }
            double xmin=0;
            if (!(reader.isMissing("x1"))) {
                xmin = reader.getDouble("x1");
                hasbb=true;
            }
            double xmax=0;
            if (!(reader.isMissing("x2"))) {
                xmax = reader.getDouble("x2");
            }
            double ymin=0;
            if (!(reader.isMissing("y1"))) {
                ymin = reader.getDouble("y1");
            }
            double ymax=0;
            if (!(reader.isMissing("y1"))) {
                ymax = reader.getDouble("y1");
            }


            AdminUnit au = new AdminUnit(name,admin_level,population,area,density,xmin,xmax,ymin,ymax,hasbb);
            units.add(au);

            long parentid = reader.getLong("parent");
            long id = reader.getLong("id");
            adun.put(id,au);
            adun2.put(au,id);
            parents.put(au,parentid);

            if(parentid2child.containsKey(parentid)){
                parentid2child.get(parentid).add(au);
            }
            else{
                List<AdminUnit> adu = new ArrayList<>();
                adu.add(au);
                parentid2child.put(parentid,adu);
            }

        }

        for(AdminUnit u:units) {
            long pid = parents.get(u);

            if (adun.get(pid) != null) {
                u.parent = adun.get(pid);
            } else {
                u.parent = null;
            }

            if (u.parent != null) {
                u.parent.children = parentid2child.get(pid);
            }
            u.fixMissingValues();
        }
    }

    void list(PrintStream out){
        for(int i=0; i<units.size(); i++){
            out.print(units.get(i).toString());
        }
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i=offset; i<limit; i++){
            out.print(units.get(i).toString());
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(int i=0; i<units.size(); i++){
            if(regex) {
                if (units.get(i).name.matches(pattern)) {
                    ret.units.add(units.get(i));
                }
            }
            else{
                if (units.get(i).name.contains(pattern)) {
                    ret.units.add(units.get(i));
                }
            }
        }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList neighbors = new AdminUnitList();
        int ad_level = unit.adminLevel;
            for (AdminUnit u : units) {
                if (u.adminLevel == ad_level && (ad_level==4 || ad_level==6 || ad_level==7) && unit.bbox.intersects(u.bbox) && u!=unit) {
                    neighbors.units.add(u);
                }
                if(u.adminLevel==ad_level && ad_level==8 && unit.bbox.distanceTo(u.bbox)<maxdistance && u!=unit){
                    neighbors.units.add(u);
                }
            }
        return neighbors;
    }

    AdminUnitList getNeighborsRtree(AdminUnit unit, double maxdistance){
        AdminUnitList neighbors = new AdminUnitList();
        int ad_level = unit.adminLevel;
        //przeszukujemy tylko jednostki będące rodzeństwem
        if(unit.parent!=null) {
            for (AdminUnit u : unit.parent.children) {
                if (u.adminLevel == ad_level && (ad_level == 4 || ad_level == 6 || ad_level == 7) && unit.bbox.intersects(u.bbox) && u != unit) {
                    neighbors.units.add(u);
                }
                if (u.adminLevel == ad_level && ad_level == 8 && unit.bbox.distanceTo(u.bbox) < maxdistance && u != unit) {
                    neighbors.units.add(u);
                }
            }

            return neighbors;
        }
        else{ return getNeighbors(unit,maxdistance);}
    }

    Comparator<AdminUnit> nameComparator(){
        class MyComparator implements Comparator<AdminUnit>{
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return t.name.compareTo(t1.name);
            }
        }
        return new MyComparator();
    }

    public AdminUnitList sortInPlaceByName() {
        units.sort(nameComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea(){
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return Double.compare(t.area,t1.area);
            }
        });
        return this;
    }

    /* inaczej sortInPlaceByArea

    Comparator<AdminUnit> areaComparator() {
        return new Comparator<AdminUnit>() {
            @Override
            public int compare (AdminUnit t, AdminUnit t1) {
                return Double.compare(t.area, t1.area);
            }
        };
    }

    public AdminUnitList sortInPlaceByArea() {
        units.sort(areaComparator());
        return this;
    }*/


    AdminUnitList sortInplaceByPopulation(){
        units.sort((t, t1) -> Double.compare(t.population,t1.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList list = new AdminUnitList();
        list.units.addAll(this.units);
        list.sortInplace(cmp);
        return list;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList filtrated = new AdminUnitList();
        filtrated.units = units.stream().filter(pred).collect(Collectors.toList());
        return filtrated;
    }


    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList filtrated = new AdminUnitList();
        filtrated.units = units.stream().filter(pred).collect(Collectors.toList()).subList(0,limit);
        return filtrated;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList filtrated = new AdminUnitList();
        filtrated.units = units.stream().filter(pred).collect(Collectors.toList()).subList(offset,limit);
        return filtrated;
    }

}