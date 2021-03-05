package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //TILE ID
    public static Tile[] tiles=new Tile[256];
    public static Tile WallTile=new WallTile(0);
    public static Tile GrassTile=new GrassTile(1);
    public static Tile RockTile=new TreeTile(2);




    public static final int TileWidth=64, TileHeight=64;

    protected BufferedImage texture;
    protected final int id;
    public Tile(BufferedImage texture,int id){
        this.texture=texture;
        this.id=id;

        tiles[id]=this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TileWidth,TileHeight,null);
    }
public boolean isSolid(){
        return false;
}
    public int getId(){
        return id;
    }
}
