package Tile;

import Graphic.Assets;


public class WallTile extends Tile{

    public WallTile(int id) {
        super(Assets.c1,id);
    }
    public boolean isSolid(){
        return true;
    }
}
