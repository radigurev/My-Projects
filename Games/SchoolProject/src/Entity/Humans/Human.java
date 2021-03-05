package Entity.Humans;

import Entity.Entity;
import com.game.Game;
import com.game.Handler;
import Tile.Tile;

public abstract class Human extends Entity{


    public static final float DefaultSpeed=3.0f;
    public static final int DefaultWidth=100, DefaultHeight=100;

    protected float speed;
    protected float xMove,yMove;

    public Human(Handler handler, float x, float y, int width, int height) {
        super(handler,x, y, width, height);
       speed=DefaultSpeed;
        xMove=0;
        yMove=0;
    }
    public void move(){
        if(!checkEntityCollisions(xMove,0f))
                moveX();
        if(!checkEntityCollisions(0f,yMove))
        moveY();
    }

    public void moveX(){
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TileWidth;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TileHeight) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TileHeight)){
                x += xMove;
            }else{
                x=tx*Tile.TileWidth-bounds.x- bounds.height-1;
            }
        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TileWidth;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TileHeight) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TileHeight)){
                x += xMove;
            }else{
                x=tx*Tile.TileWidth+Tile.TileWidth-bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TileHeight;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TileWidth, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TileWidth, ty)){
                y += yMove;
            }else{
                y=ty*Tile.TileHeight+Tile.TileHeight-bounds.y;
            }
        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TileHeight;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TileWidth, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TileWidth, ty)){
                y += yMove;
            }else{
                y=ty*Tile.TileHeight-bounds.y-bounds.height-1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }
    //GETTERS && SETTERS
    public float getxMove(){
        return xMove;
    }
    public void setxMove(float xMove){
        this.xMove=xMove;
    }
    public float getyMove(){
        return yMove;
    }
    public void setyMove(float yMove){
        this.yMove=yMove;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health=health;
    }
    public float getSpeed(){
        return speed;
    }
    public void setSpeed(float speed){
        this.speed=speed;
    }
}
