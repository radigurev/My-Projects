package Snake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Delayed;

public class GamePanel extends JPanel implements ActionListener{
    static final int ScreenWidth=900;
    static final int ScreenHeight=900;
    static final int UnitSize=25;
    static final int GameUnits=(ScreenWidth*ScreenHeight)/UnitSize;
    static final int delay=75;
    final float x[]=new float[GameUnits];
    final float y[]=new float[GameUnits];
    int bodyParts=6;
    int points ;
    int pointX;
    int pointY;
    char direction='R';
    boolean running =false;
    Timer timer;
    Random random;
    static boolean Game=false;
    GamePanel(){
random=new Random();
    this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
    }
    public void startGame(){
        newPoint();
        running=true;
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paintComponent(Graphics g){


        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running) {

            g.setColor(Color.RED);
            g.fillOval(pointX, pointY, UnitSize, UnitSize);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect((int) x[i], (int) y[i], UnitSize, UnitSize);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillRect((int) x[i], (int) y[i], UnitSize, UnitSize);
                }
                g.setColor(Color.red);
                g.setFont(new Font("Ariel",Font.BOLD,50));
                FontMetrics metrics=getFontMetrics(g.getFont());
                g.drawString("Score: "+points,(ScreenWidth-metrics.stringWidth("points"))/2,ScreenHeight-1);
                if(Game)
                    pauseMenu(g);
            }


        }else
            gameOver(g);
    }
    public void newPoint(){
        pointX=random.nextInt((int)(ScreenWidth/UnitSize))*UnitSize;
        pointY=random.nextInt((int)(ScreenHeight/UnitSize))*UnitSize;
    }
    public void move(){
        for(int i=bodyParts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];

        }
        switch (direction){
            case 'U':
                y[0]=y[0]-UnitSize;
                break;
            case 'D':
                y[0]=y[0]+UnitSize;
                break;
            case 'L':
                x[0]=x[0]-UnitSize;
                break;
            case 'R':
                x[0]=x[0]+UnitSize;
                break;
        }
    }
    public void checkPoint(){
        if((x[0]==pointX)&&(y[0]==pointY)){
            bodyParts++;
            points++;
            newPoint();

        }
    }
    public void checkCollisions(){
        //If head hits body
        for (int i = bodyParts; i >0 ; i--) {
            if((x[0]==x[i])&&(y[0]==y[i])){
                running =false;
            }
        }
        //check if head touches left border
        if(x[0]<0||x[0]>ScreenWidth-1||
                y[0]<0||y[0]>ScreenHeight-1){
            running =false;
        }
        if(!running)
            timer.stop();
    }
    public void gameOver(Graphics g){

          g.setColor(Color.red);
          g.setFont(new Font("Ariel", Font.BOLD, 75));
          FontMetrics metrics = getFontMetrics(g.getFont());
          g.drawString("Game Over", (ScreenWidth - metrics.stringWidth("Game over")) / 2, ScreenHeight / 2);
          g.setColor(Color.red);
          g.setFont(new Font("Ariel", Font.BOLD, 50));
          g.drawString("Score: " + points, (ScreenWidth - metrics.stringWidth("points")) / 2, ScreenHeight - 1);



    }
    public void pauseMenu(Graphics g){

            g.setColor(Color.red);
            g.setFont(new Font("Ariel",Font.BOLD,75));
            FontMetrics metrics=getFontMetrics(g.getFont());
            g.drawString("Paused",(ScreenWidth-metrics.stringWidth("Paused"))/2,ScreenHeight/2);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkPoint();
            checkCollisions();
        }
        repaint();
    }
    public void pause(){
       Game=true;
        timer.stop();

    }
    public void resume(){
        Game=false;
        timer.start();

    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){

                case KeyEvent.VK_LEFT:
                    if(direction !='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction !='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction !='D'){
                        direction='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction !='U'){
                        direction='D';
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(Game) {
                        resume();
                    } else {
                        pause();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if(!running)
                    new GameFrame();
            }
        }
    }
}
