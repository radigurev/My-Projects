package Entity.StaticEntities;
import Entity.Entity;
import Entity.StaticEntities.StaticEntity;
import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;
import Item.Item;
import java.awt.*;



public class Tree extends StaticEntity {
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TileWidth, Tile.TileHeight*2);

        bounds.x=28;
        bounds.y=95;
        bounds.width=10;
        bounds.height=30;
    }

    @Override
    public void tick() {

    }
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x,(int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.c3,(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height,null);

        //Visible hitbox
/*
        g.setColor(Color.CYAN);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);

 */


    }
}
