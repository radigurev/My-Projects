package Graphic;

import Tile.Tile;
import com.game.Game;
import Entity.*;
import com.game.Handler;

public class GameCamera {
    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
   public void BlankSpace(){
        if(xOffset<0){
            xOffset=0;
        }else   if(xOffset> handler.getWorld().getWidth()*Tile.TileWidth- handler.getWidth()){
            xOffset=handler.getWorld().getWidth()*Tile.TileWidth- handler.getWidth();
        }
        if(yOffset<0){
            yOffset=0;
        }else if(yOffset>handler.getWorld().getHeight()*Tile.TileHeight-handler.getHeight()){
            yOffset=handler.getWorld().getHeight()*Tile.TileHeight-handler.getHeight();
        }


    }
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        BlankSpace();
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
