public class BoundingBox {

    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty;

    BoundingBox() {
        this.empty = true;
    }

    BoundingBox(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.empty = false;
    }
    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if(x<xmin || x>xmax) {
            if(x<xmin) {
                xmin=x;
            }
            else{xmax=x;}
        }
        if(y<ymin || y>ymax) {
            if(y<ymin) {
                ymin=y;
            }
            else{ymax=y;}
        }
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        if(x>xmin && x<xmax && y>ymin && y<ymax) {
            return true;
        }
        else{return false;}
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        if(bb.xmin>xmin && bb.xmax<xmax && bb.ymin>ymin && bb.ymax<ymax) {
            return true;
        }
        else {return false;}
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        if((bb.xmin<=xmin && bb.xmax<=xmax && bb.xmax>=xmin) || (bb.xmin>=xmin && bb.xmax>=xmax && bb.xmin<=xmax) || (bb.ymin<=ymin && bb.ymax<=ymax && bb.ymax>=ymin) || (bb.ymin>=ymin && bb.ymax>=ymax && bb.ymin<=ymax) || (bb.xmin>=xmin && bb.xmax<=xmax && bb.ymin<=ymin && bb.ymax>=ymax) || (bb.ymin>=ymin && bb.ymax<=ymax && bb.xmin<=xmin && bb.xmax>=xmax)) {
            return true;
        }
        else {return false;}
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if(bb.xmin<xmin) {
            xmin = bb.xmin;
        }
        if(bb.xmax>xmax) {
            xmax = bb.xmax;
        }
        if(bb.ymin<ymin) {
            ymin = bb.ymin;
        }
        if(bb.ymax>ymax) {
            ymax = bb.ymax;
        }
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        if(empty) {
            return true;
        }
        else{return false;}
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        if(isEmpty()) {
            throw new IllegalStateException("BoundingBox is empty");
        }
        return (xmin+xmax)/2;
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        if(isEmpty()) {
            throw new IllegalStateException("BoundingBox is empty");
        }
        return (ymin+ymax)/2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx){
        if(isEmpty()) {
            throw new IllegalStateException("BoundingBox is empty");
        }
        double [] bbcenter = {this.getCenterX(),this.getCenterY()};
        double [] bbxcenter = {bbx.getCenterX(),bbx.getCenterY()};
        double distance = Math.sqrt(Math.pow(bbcenter[0]-bbxcenter[0],2)+Math.pow(bbcenter[1]-bbxcenter[1],2));
        return distance;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("( ");
        buf.append(xmin + ", ");
        buf.append(xmax + ", ");
        buf.append(ymin + ", ");
        buf.append(ymax);
        buf.append(" )");
        return buf.toString();
    }

}

