import java.awt.*;

public class Wishes implements XmasShape {
    int x;
    int y;
    double scale;
    String wish;
    Color fillColor;

    Wishes(int x,int y,double scale, String wish, Color fillColor){
        this.x= x;
        this.y = y;
        this.scale = scale;
        this.wish = wish;
        this.fillColor = fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.ITALIC, 50);
        g2d.setFont(font);
        g2d.setColor(fillColor);
        g2d.drawString(wish,150,0);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
