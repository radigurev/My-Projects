package State;

import Graphic.Assets;
import com.game.ClickListener;
import com.game.UIButton;
import com.game.UImanager;
import com.game.*;

import java.awt.*;

public class MenuState extends State{
    private UImanager uiManager;
    public MenuState(Handler handler){
        super(handler);
        uiManager=new UImanager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIButton(200, 200, 128, 64, Assets.Button, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }
    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
