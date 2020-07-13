import java.awt.*;

public class Trunk implements XmasShape  {
    int x;
    int y;
    double scale;
    Color fillColor;
    Color gradColor;

    Trunk(int x,int y,double scale, Color fillColor, Color gradColor){
        this.x= x;
        this.y = y;
        this.scale = scale;
        this.fillColor = fillColor;
        this.gradColor = gradColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        //g2d.drawRect(0,0,50,100);
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,115, gradColor);
        g2d.setPaint(grad);
        //g2d.fill3DRect(0,0,50,100,false);
        int x[]={50,50,0,0};
        int y[]={0,100,100,0};
        g2d.fillPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
