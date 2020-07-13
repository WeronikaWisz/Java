import java.awt.*;

public class Snow implements XmasShape {
    int x;
    int y;
    double scale;
    Color fillColor;
    Color gradColor;

    Snow(int x,int y,double scale, Color fillColor, Color gradColor){
        this.x= x;
        this.y = y;
        this.scale = scale;
        this.fillColor = fillColor;
        this.gradColor = gradColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,45, gradColor);
        g2d.setPaint(grad);
        g2d.fillOval(0,0,100,50);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
