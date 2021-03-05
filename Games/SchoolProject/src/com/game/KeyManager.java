package com.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private  boolean[]keys,justPressed,cantPress;
    public boolean up,down,left,right;
    public boolean aUp, aDown, aLeft, aRight;

    public KeyManager(){
            keys=new boolean[256];
            justPressed=new boolean[keys.length];
            cantPress=new boolean[keys.length];
    }
    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }
    public  void tick(){
        for (int i = 0; i < keys.length; i++) {
            if(cantPress[i]&&!keys[i]){
                    cantPress[i]=false;
            }else if(justPressed[i]){
                cantPress[i]=true;
                justPressed[i]=false;
            }
            if(!cantPress[i]&&keys[i]){
                justPressed[i]=true;
            }
        }



        up=keys[KeyEvent.VK_W];
        down=keys[KeyEvent.VK_S];
        right=keys[KeyEvent.VK_D];
        left=keys[KeyEvent.VK_A];
        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }
    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<0||e.getKeyCode()>= keys.length)
            return;
        keys[e.getKeyCode()]=true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }
}
