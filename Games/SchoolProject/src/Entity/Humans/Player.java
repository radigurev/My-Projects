package Entity.Humans;
import Entity.Entity;
import Entity.Humans.Human;
import Graphic.Animation;
import Graphic.Assets;
import Item.Inventory;
import com.game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    //Animation
    private Animation animationDown,animationUp,animationLeft,animationRight;
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
    //IVNENTORY
    private Inventory inventory;
    public Player(Handler handler, float x, float y) {
        super(handler,x, y,Human.DefaultWidth,Human.DefaultHeight);
        bounds.x=25;
        bounds.y=50;
        bounds.width=50;
        bounds.height=50;

        animationDown=new Animation(500,Assets.playerDown);
        animationUp=new Animation(500,Assets.playerUp);
        animationLeft=new Animation(500,Assets.playerLeft);
        animationRight=new Animation(500,Assets.playerRight);

        inventory=new Inventory(handler);
    }

    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationRight.tick();
        animationLeft.tick();
        //Movement
       getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();
        inventory.tick();
    }


        private void checkAttacks(){
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
            if(attackTimer < attackCooldown)
                return;

            if(inventory.isActive()){
                return;
            }

            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle ar = new Rectangle();
            int arSize = 20;
            ar.width = arSize;
            ar.height = arSize;

            if(handler.getKeyManager().aUp){
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
            }else if(handler.getKeyManager().aDown){
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;

            }else if(handler.getKeyManager().aLeft){
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            }else if(handler.getKeyManager().aRight){
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            }else{
                return;
            }

            attackTimer = 0;

            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(ar)){
                    e.hurt(1);
                    return;
                }
            }

        }

    public void die(){
        System.out.println("You lose");
    }


    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width, height, null);

       inventory.render(g);
        //Visible hitbox
    /*
        g.setColor(Color.red);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);


     */


    }
    public void postRender(Graphics g){
        inventory.render(g);
    }
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove>0){
            return animationLeft.getCurrentFrame();
        }else if(xMove<0){
            return animationRight.getCurrentFrame();
        }else if(yMove<0){
            return animationUp.getCurrentFrame();
        }else{
            return animationDown.getCurrentFrame();
        }
    }
    //GETTERS && SETTERS
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
