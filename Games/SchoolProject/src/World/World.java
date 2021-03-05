package World;

import Entity.EntityManager;
import Entity.Humans.Player;
import Entity.StaticEntities.Tree;
import Item.ItemManager;
import Tile.Tile;
import Utils.Utils;
import com.game.Game;
import com.game.Handler;

import java.awt.*;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX,spawnY;
    private int[][] tiles;
    //Enteties
    private EntityManager entityManager;
    //Items
    private ItemManager itemManager;
    public World(Handler handler, String path){
        this.handler=handler;
        entityManager = new EntityManager(handler,new Player(handler,100,100));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Tree(handler,100,200));
        entityManager.addEntity(new Tree(handler,100,300));
        entityManager.addEntity(new Tree(handler,100,400));
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart =(int)Math.max(0,handler.getGameCamera().getxOffset()/Tile.TileWidth);
        int xEnd=(int)Math.min(width,(handler.getGameCamera().getxOffset()+handler.getWidth())/Tile.TileWidth+1);
        int yStart=(int) Math.max(0,handler.getGameCamera().getyOffset()/Tile.TileHeight);
        int yEnd=(int) Math.min(height,(handler.getGameCamera().getyOffset()+handler.getHeight())/Tile.TileHeight+1);

        for(int i=yStart;i<yEnd;i++){
            for(int j=xStart;j<xEnd ;j++){
                getTile(j,i).render(g,(int) (j*Tile.TileWidth-handler.getGameCamera().getxOffset()),
                        (int)(i*Tile.TileHeight-handler.getGameCamera().getyOffset()));

            }
        }
        //ITEMS
        itemManager.render(g);

        //Entitiies
        entityManager.render(g);
    }
    public Tile getTile(int x, int y){
        if(x<0 ||y<0||x>=width||y>=height)
            return Tile.GrassTile;

        Tile t=Tile.tiles[tiles[x][y]];
        if(t==null)
            return Tile.GrassTile;

        return t;
    }
    private void loadWorld(String path){
        String file =Utils.loadFileAsString(path);
        String[] tokens=file.split("\\s+");
        width=Utils.parseInt(tokens[0]);
        height=Utils.parseInt(tokens[1]);
        spawnX=Utils.parseInt(tokens[2]);
        spawnY=Utils.parseInt(tokens[3]);

        tiles=new int [width][height];
        for(int y=0;y<height;y++){
            for (int x=0;x<width;x++){
                tiles[x][y]=Utils.parseInt(tokens[(x+y*width)+4]);
            }
        }

    }
    //GETTERS && SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
