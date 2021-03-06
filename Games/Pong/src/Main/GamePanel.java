package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    static final int GameWidth=1000;
    static final int GameHeigth=(int)(GameWidth*(0.5555));
    static final Dimension ScreenSize=new Dimension(GameWidth,GameHeigth);
    static final int BallDiameter=20;
    static final int PaddleWidth=25;
    static final int PaddleHeight=100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle PaddleLeft;
    Paddle PaddleRight;
    Ball ball;
    Score score;

    GamePanel(){
        newPaddles();
        newBall();
        score=new Score(GameWidth,GameHeigth);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(ScreenSize);

        gameThread=new Thread(this);
        gameThread.start();
    }

    public void newBall(){

        ball = new Ball((GameWidth/2)-(BallDiameter/2),(GameHeigth/2)-(BallDiameter/2),BallDiameter,BallDiameter);
    }

    public void newPaddles(){

        PaddleLeft=new Paddle(0,(GameHeigth)/2-(PaddleHeight/2),PaddleWidth,PaddleHeight,1);
        PaddleRight=new Paddle(GameWidth-PaddleWidth,(GameHeigth)/2-(PaddleHeight/2),
                PaddleWidth,PaddleHeight,2);

    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        PaddleLeft.draw(g);
        PaddleRight.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        PaddleLeft.move();
        PaddleRight.move();
        ball.move();
    }
    public void checkCollision(){

        if(ball.y <=0) {
            ball.setYD(-ball.yVel);
        }
        if(ball.y >= GameHeigth-BallDiameter) {
            ball.setYD(-ball.yVel);
        }
        //bounce ball off paddles
        if(ball.intersects(PaddleLeft)) {
            ball.xVel = Math.abs(ball.xVel);
            ball.xVel++; //optional for more difficulty
            if(ball.yVel>0)
                ball.yVel++; //optional for more difficulty
            else
                ball.yVel--;
            ball.setXD(ball.xVel);
            ball.setYD(ball.yVel);
        }
        if(ball.intersects(PaddleRight)) {
            ball.xVel = Math.abs(ball.xVel);
            ball.xVel++; //optional for more difficulty
            if(ball.yVel>0)
                ball.yVel++; //optional for more difficulty
            else
                ball.yVel--;
            ball.setXD(-ball.xVel);
            ball.setYD(ball.yVel);
        }

        if(PaddleLeft.y<=0){
            PaddleLeft.y=0;
        }
        if(PaddleLeft.y>=GameHeigth-PaddleHeight){
            PaddleLeft.y=GameHeigth-PaddleHeight;
        }
        if(PaddleRight.y<=0){
            PaddleRight.y=0;
        }
        if(PaddleRight.y>=GameHeigth-PaddleHeight){
            PaddleRight.y=GameHeigth-PaddleHeight;
        }
        if(ball.x<=0){
            score.playerRight++;
            newPaddles();
            newBall();
        }
        if(ball.x>=GameWidth-BallDiameter){
            score.playerLeft++;
            newPaddles();
            newBall();
        }

    }
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class ActionListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            PaddleLeft.keyPressed(e);
            PaddleRight.keyPressed(e);
        }
        public void KeyReleased(KeyEvent e){
        PaddleLeft.keyReleased(e);
        PaddleRight.keyReleased(e);
        }
    }

}
