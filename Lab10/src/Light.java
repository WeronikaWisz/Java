import java.awt.*;

public class Light implements XmasShape {
    int x;
    int y;
    double scale;
    Color fillColor;
    Color gradColor;
    Color lineColor;

    Light(int x,int y,double scale, Color fillColor, Color gradColor, Color lineColor){
        this.x= x;
        this.y = y;
        this.scale = scale;
        this.fillColor = fillColor;
        this.gradColor = gradColor;
        this.lineColor = lineColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,115, gradColor);
        g2d.setPaint(grad);
        int x[]={50,65,100,70,90,50,10,30,0,35};
        int y[]={10,40,40,65,100,80,100,65,40,40};
        g2d.fillPolygon(x,y,x.length);
        g2d.setPaint(lineColor);
        g2d.drawPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
