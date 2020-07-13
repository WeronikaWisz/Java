import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox;
    List<AdminUnit> children;

    AdminUnit(String n, int al, double p, double a, double d, double xmin, double xmax, double ymin, double ymax, boolean hasbb){
        name = n;
        adminLevel = al;
        population = p;
        area = a;
        density = d;
        if(hasbb) {
            bbox = new BoundingBox(xmin, xmax, ymin, ymax);
        }
        else{ bbox = new BoundingBox();}
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[ ");
        buf.append(name + ", ");
        buf.append(adminLevel + ", ");
        buf.append(population + ", ");
        buf.append(area + ", ");
        buf.append(density +", ");
        buf.append(bbox.toString());
        buf.append(" ]");
        return buf.toString();
    }

    void fixMissingValues() {

        if(this.density!=0){
            if(this.population==0){
                this.population=this.area*this.density;
            }
        }
        if(this.parent!=null) {
            if (this.parent.density != 0) {
                this.density = this.parent.density;
                if (this.population == 0) {
                    this.population = this.area * this.density;
                }
            }
            else{this.parent.fixMissingValues();}
        }
    }
}
