import java.awt.*;
import java.awt.geom.AffineTransform;

public class Branch  implements XmasShape{
    int x;
    int y;
    double scale;
    Color fillColor;
    Color gradColor;

    Branch(int x,int y,double scale, Color fillColor, Color gradColor){
        this.x= x;
        this.y = y;
        this.scale = scale;
        this.fillColor = fillColor;
        this.gradColor = gradColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,115, gradColor);
        g2d.setPaint(grad);
        int x[]={146,286,223,200,170,133,100,69,45,0};
        int y[]={0,100,120,106,120,106,120,106,120,100};
        g2d.fillPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
