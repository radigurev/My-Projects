package com.game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UImanager {

    private Handler handler;
    ArrayList<UI> objects;
    public UImanager(Handler handler){
        this.handler=handler;
        objects=new ArrayList<UI>();
    }
    public void tick(){
        for(UI a:objects){
            a.tick();
        }
    }
    public void render(Graphics g){
        for(UI a:objects){
            a.render(g);
        }
    }
    public void onMouseMove(MouseEvent e){
        for(UI a:objects){
            a.onMouseMove(e);
        }
    }
    public void onMouseRelease(MouseEvent e){
        for(UI a:objects){
            a.onMouseRelease(e);
        }
    }
    public void addObject(UI a){
        objects.add(a);
    }
    public void removeObject(UI a){
        objects.remove(a);
    }
    //GETTERS && SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UI> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UI> objects) {
        this.objects = objects;
    }
}
