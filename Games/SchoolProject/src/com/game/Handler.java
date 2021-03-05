package com.game;

import Graphic.GameCamera;
import World.World;
public class Handler {
    private Game game;
    private World world;
        public Handler(Game game){
            this.game=game;
        }
        public int getWidth(){
            return game.getWidth();
        }
        public GameCamera getGameCamera(){
            return game.getGameCamera();
    }
    //SETTERS && GETTERS
        public KeyManager getKeyManager(){
            return game.getKeyManager();
        }
        public MouseManager getMouseManager(){
            return game.getMouseManager();
        }
        public int getHeight(){
            return game.getHeight();
        }
        public Game getGame(){
            return game;
        }
        public void setGame(Game game){
            this.game=game;
        }
        public World getWorld(){
            return world;
        }
        public void setWorld(World world){
            this.world=world;
        }
}
