package Main;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{

    Random random;
    int xVel;
    int yVel;
    int speed=4;

     Ball(int x, int y, int width, int height) {
        super(x,y,width,height);
        random=new Random();
        int randomXD=random.nextInt(2);
        if(randomXD==0){
            randomXD--;
        }
         setXD(randomXD*speed);
        int randomYD=random.nextInt(2);
        if(randomYD==0){
            randomYD--;
        }
         setYD(randomYD*speed);
    }

    public void setXD(int randomXD){
    xVel=randomXD;

    }
    public void setYD(int randomYD){
        yVel=randomYD;
    }
    public void move(){
    x+=xVel;
    y+=yVel;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x,y,height,width);
    }
}
