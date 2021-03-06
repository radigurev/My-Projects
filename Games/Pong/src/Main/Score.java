package Main;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle{

    static int GameWidth;
    static int GameHeight;
    int playerLeft,playerRight;
    Score(int GameWidth,int GameHeight){
        Score.GameWidth=GameWidth;
        Score.GameHeight=GameHeight;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel",Font.BOLD,60));

        g.drawLine(GameWidth/2,0,GameWidth/2,GameHeight);

        g.drawString(String.valueOf(playerLeft),GameWidth/2-75,50);
        g.drawString(String.valueOf(playerRight),GameWidth/2+43,50);
    }
}
