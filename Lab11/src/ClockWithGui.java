import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel{
    LocalTime time = LocalTime.now();

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;

        g2d.clearRect(0,0,getHeight(),getWidth());
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            //g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
            g2d.drawString(Integer.toString(i),(int)trg.getX() - g2d.getFontMetrics().stringWidth(Integer.toString(i))/2,(int)trg.getY());
        }

        for(int i=1;i<61;i++){
            AffineTransform at2 = g2d.getTransform();
            g2d.rotate(2*Math.PI/60*i);
            if(i % 5 == 0){
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, -150, 0, -140);
            }
            else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, -150, 0, -145);
            }
            g2d.setTransform(at2);
        }

        AffineTransform saveAT = g2d.getTransform();
        //g2d.rotate((time.getHour()%12+time.getMinute()*2*Math.PI/720)*2*Math.PI/12);
        g2d.rotate(time.getHour()%12*2*Math.PI/12+time.getMinute()*2*Math.PI/720);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0,0,0,-65);
        g2d.setTransform(saveAT);

        AffineTransform saveAT2 = g2d.getTransform();
        g2d.rotate(time.getMinute()*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0,0,0,-80);
        g2d.setTransform(saveAT2);

        AffineTransform saveAT3 = g2d.getTransform();
        g2d.rotate(time.getSecond()*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(0,0,0,-95);
        g2d.setTransform(saveAT3);

    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        ClockWithGui gui = new ClockWithGui();
        ClockThread clock = gui.new ClockThread();
        clock.start();
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(gui);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
