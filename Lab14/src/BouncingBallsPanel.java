import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class BouncingBallsPanel extends JPanel {

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;

        Ball(int x,int y,double vx, double vy, Color fillColor){
            this.x= x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = fillColor;
        }

        void render(Graphics2D g2d) {
            // ustaw kolor wypełnienia
            g2d.setColor(color);
            g2d.fillOval(0,0,15,15);
        }
        void transform(Graphics2D g2d) {
            g2d.translate(x,y);
        }

        void draw(Graphics2D g2d){
            // Get the current transform
            AffineTransform saveAT = g2d.getTransform();
            // Perform transformation
            transform(g2d);
            // Render
            render(g2d);
            // Restore original transform
            g2d.setTransform(saveAT);
        }

        void move(){
            Random rand = new Random();
            this.x = x+(int)vx;
            this.y = y+(int)vy;
        }

    }

    List<Ball> balls = new ArrayList<>();
    private final AtomicBoolean disable = new AtomicBoolean(true);

    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                try {
                    synchronized (disable) {
                        if (disable.get()) {
                            disable.wait();
                        }
                    }

                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //przesuń kulki
                //wykonaj odbicia od ściany
                for(Ball ball: balls){
                    ball.move();
                    Dimension d=getSize();
                    if (ball.x < 30) {
                        ball.x = 30;
                        ball.vx *= -1;
                    }
                    if (ball.x > d.width - 30) {
                        ball.x = d.width - 30;
                        ball.vx *= -1;
                    }
                    if (ball.y < 30) {
                        ball.y = 30;
                        ball.vy *= -1;
                    }
                    if (ball.y > d.height - 30) {
                        ball.y = d.height - 30;
                        ball.vy *= -1;
                    }
                    for(Ball ball2: balls){
                        BallCollision(ball,ball2);
                    }
                }
                //wywołaj repaint
                repaint();
                //uśpij
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        Random random = new Random();
        for(int i=0; i<=random.nextInt(10); i++){
            Ball ball = new Ball(random.nextInt(700),random.nextInt(700),random.nextInt(50),random.nextInt(50),new Color(random.nextInt(250), random.nextInt(250), random.nextInt(250)));
            balls.add(ball);
        }
    }

    void onStart(){
        synchronized (disable) {
            disable.set(false);
            disable.notifyAll();
        }
        System.out.println("Start or resume animation thread");
        AnimationThread animation = new AnimationThread();
        animation.start();
    }

    void onStop(){

        synchronized (disable) {
            disable.set(true);
            disable.notifyAll();
            System.out.println("Suspend animation thread");
        }
    }

    void onPlus(){
        System.out.println("Add a ball");
        Random random = new Random();
        Ball ball = new Ball(random.nextInt(700),random.nextInt(700),random.nextInt(50),random.nextInt(50),new Color(random.nextInt(250), random.nextInt(250), random.nextInt(250)));
        balls.add(ball);
    }

    void onMinus(){
        System.out.println("Remove a ball");
        Random random = new Random();
        int count = balls.size();
        if(count>0) {
            balls.remove(random.nextInt(count));
        }
    }

    private void BallCollision(Ball one, Ball two) {
        if(Math.abs(one.x-two.x)<=15 && Math.abs(one.y-two.y)<=15){
            one.vx *= -1;
            one.vy *= -1;
            two.vx *= -1;
            two.vy *= -1;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        for(Ball b:balls){
            b.draw((Graphics2D)g);
        }
    }
}
