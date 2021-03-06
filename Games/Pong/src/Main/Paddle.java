package Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

    int id;
    float yVel;
    int speed=5;

    Paddle(int x,int y,int PaddleWidth,int PaddleHeight,int id){
        super(x,y,PaddleWidth,PaddleHeight);
        this.id=id;
    }
    public void keyPressed(KeyEvent e){
            switch (id){
                case 1:
                        if(e.getKeyCode()==KeyEvent.VK_W){
                            setY(-speed);
                            move();
                        }
                        if (e.getKeyCode()==KeyEvent.VK_S){
                            setY(+speed);
                            move();
                        }break;

                case 2: if(e.getKeyCode()==KeyEvent.VK_UP){
                    setY(-speed);
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    setY(+speed);
                    move();
                }
            }
    }
    public void keyReleased(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setY(0);
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_S){
                    setY(0);
                    move();
                }break;

            case 2: if(e.getKeyCode()==KeyEvent.VK_UP){
                setY(0);
                move();
            }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    setY(0);
                }
        }
    }
    public void setY(int y){
        yVel=y;
    }
    public void move(){
    y=y+(int)yVel;
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.RED);
        }else
            g.setColor(Color.BLUE);
            g.fillRect(x,y,width,height);
    }
}
