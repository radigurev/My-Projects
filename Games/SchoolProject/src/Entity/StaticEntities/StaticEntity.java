package Entity.StaticEntities;

import Entity.Entity;
import Graphic.Assets;
import com.game.Handler;

import java.awt.*;

public abstract class StaticEntity extends Entity{

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


}
