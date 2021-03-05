package com.game;
//libraries
import java.awt.*;
import java.awt.image.BufferStrategy;
//classes
import Display.Display;
import Graphic.Assets;
import Graphic.GameCamera;
import State.MenuState;
import State.GameState;
import State.State;


public class Game implements Runnable {
    private Display display;
    private int width, height;
    public String title;
    private Thread thread;
    private boolean running=false;
//libraries
    private BufferStrategy bs;
    private Graphics g;
    //states
    public State gameState;
    public State menuState;
    //Inputs
    private KeyManager keymanager;
    private MouseManager mouseManager;
    //Camera
    private GameCamera gameCamera;
    //Handler
    private Handler handler;

    public Game(String title, int width, int height){
        display=new Display(title, width, height);
        this.width=width;
        this.height=height;
        this.title=title;

        keymanager=new KeyManager();
        mouseManager=new MouseManager();

    }
    private void init(){
        display=new Display(title, width, height);
        display.getFrame().addKeyListener(keymanager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        handler =new Handler(this);
    gameCamera= new GameCamera(handler,0,0);
        gameState= new GameState(handler);
        menuState=new MenuState(handler);
        State.setState(menuState);
    }

    private void tick(){
        keymanager.tick();
        if(State.getState() != null)
            State.getState().tick();

    }
    private void render(){
        bs=display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g =bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        //Drawing here


        if(State.getState() != null)
            State.getState().render(g);

        bs.show();
        g.dispose();
    }
    public void run(){
        init();
        int fps=60;
        double timePerTick=1000000000/fps;
        double delta=0;
        long now;
        long lastTime=System.nanoTime();
        long timer=0;
        int ticks=0;

        while(running){
            now=System.nanoTime();
            delta+=(now-lastTime)/timePerTick;
            timer+=now-lastTime;
            lastTime=now;
            if(delta>=1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>=1000000000){
                System.out.println("FPS: "+ticks);
                ticks=0;
                timer=0;
            }
        }
        stop();
    }


    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running)
            return;

        running=false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //SETTERS && GETTERS
    public KeyManager getKeyManager(){
        return keymanager;
    }
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
