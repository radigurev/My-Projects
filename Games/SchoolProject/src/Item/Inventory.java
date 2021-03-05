package Item;

import Graphic.Assets;
import Graphic.Text;
import com.game.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {
    private Handler handler;
    private boolean active=false;
    private ArrayList<Item> inventoryItems;
    private int invX = 220, invY = 170,
            invWidth = 512, invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2+5,
            invListSpacing = 30;

    private int invImageX=610,invImageY=203,invImageWidth=63,invImageHeight=64,
        invCountX=633, invCountY=303;

    private int selectedItem=0;

    public  Inventory(Handler handler){
        this.handler=handler;
        inventoryItems=new ArrayList<Item>();

    }
        public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){
            active=!active;
        }
            if(!active)
                return;

            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
                selectedItem--;
            }
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
                selectedItem++;
            }

            if(selectedItem<0)
                selectedItem=inventoryItems.size()-1;
            else if(selectedItem>=inventoryItems.size()){
                selectedItem=0;
            }
        }
        public void render(Graphics g){
            if(!active)
                return;


            g.drawImage(Assets.inventoryScreen,invX,invY,invWidth,invHeight,null);

            int length=inventoryItems.size();
            if(length==0)
                return;

            for (int i = -5; i < 6; i++) {
                if(selectedItem+i<0|| selectedItem+i>=length)
                    continue;
                if(i==0)
                    Text.drawString(g,inventoryItems.get(selectedItem+i).getName(),invListCenterX,
                            invListCenterY+i*invListSpacing,true,Color.YELLOW,Assets.font);
                    else
                    Text.drawString(g,inventoryItems.get(selectedItem+i).getName(),invListCenterX,invListCenterY+i*invListSpacing,true,Color.WHITE,Assets.font);

            }
            Item item=inventoryItems.get(selectedItem);
                g.drawImage(item.getTexture(),invImageX,invImageY,invImageWidth,invImageHeight,null);
                Text.drawString(g,Integer.toString(item.getCount()),invCountX,invCountY,false,Color.WHITE,Assets.font);
        }
        //Inventory methods

        public void addItem(Item item){
            for (Item i:inventoryItems) {
                if(i.getId()==item.getId()){
                    i.setCount(i.getCount()+item.getCount());
                    return;
                }
            }


            inventoryItems.add(item);
        }
        //GETTERS && SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
