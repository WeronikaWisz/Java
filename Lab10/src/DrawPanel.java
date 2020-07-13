import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    ArrayList<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
        setBackground(new Color(187, 201, 218));
        setOpaque(true);
        //wishes
        Add(new Wishes(150,70,1,"Happy New Year",new Color(46, 84, 168)));
        //snow
        Add(new Snow(-50,600,5,new Color(224, 246, 248),new Color(20, 26, 110)));
        Add(new Snow(250,600,5,new Color(224, 246, 248),new Color(20, 26, 110)));
        Add(new Snow(550,600,5,new Color(224, 246, 248),new Color(20, 26, 110)));
        //trunk
        Add(new Trunk(430,520,1,new Color(100, 50, 30), new Color(0,10,0)));
        //branches
        Add(new Branch(205,370,1.75,new Color(0,255,0), new Color(0,10,0)));
        Add(new Branch(250,300,1.5,new Color(0,255,0), new Color(0,10,0)));
        Add(new Branch(332,250,1,new Color(0,255,0), new Color(0,10,0)));
        Add(new Branch(375,215,0.75,new Color(0,255,0), new Color(0,10,0)));
        Add(new Branch(415,190,0.5,new Color(0,255,0), new Color(0,10,0)));
        Add(new Branch(455,175,0.25,new Color(0,255,0), new Color(0,10,0)));
        //bubbles
        Add(new Bubble(565,480,1.5,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(485,480,1.5,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(382,480,1.5,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(300,480,1.5,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(542,370,1.25,new Color(1,1,0),new Color(249, 122, 6)));
        Add(new Bubble(488,370,1.25,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(417,370,1.25,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(360,370,1.25,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(532,305,1,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(490,305,1,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(438,305,1,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(397,305,1,new Color(1,1,0),new Color(218,39,46)));
        Add(new Bubble(520,250,0.75,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(492,250,0.75,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(460,250,0.75,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(427,250,0.75,new Color(1,1,0),new Color(249,122,6)));
        Add(new Bubble(493,205,0.5, new Color(1, 1, 0), new Color(218, 39, 46)));
        Add(new Bubble(472,205,0.5, new Color(1, 1, 0), new Color(218, 39, 46)));
        //lights
        Add(new Light(467,137,0.5, new Color(248, 219, 4), new Color(244, 152, 13), new Color(149, 139,0
        )));
        Add(new Light(70,120,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91, 117, 137
        )));
        Add(new Light(280,130,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(250,300,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(190,200,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(50,230,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(110,340,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(650,110,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(700,230,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(800,180,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(900,110,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(880,320,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
        Add(new Light(750,360,0.25, new Color(169, 248, 248), new Color(101, 172, 244), new Color(91,117,137
        )));
    }

    void Add(XmasShape shape){
        shapes.add(shape);
        return;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }

    }
}

