package Graphic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    private static final int width=64,height=64;
    public static Font font;
    public static BufferedImage c1,c2,c3,c4,tree;
    public static BufferedImage[] playerDown,playerUp,playerLeft,playerRight,Button;
    public static BufferedImage inventoryScreen;
    public static void init() {

        font=FontLoader.loadFont(("C:\\Users\\PC\\Documents\\Java\\res\\Fonts\\ARCADECLASSIC.TTF"),28);
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("C:\\Users\\PC\\Documents\\Java\\res\\textures\\spritesheet2.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("C:\\Users\\PC\\Documents\\Java\\res\\textures\\sheet.png"));
        inventoryScreen = (ImageLoader.loadImage("C:\\Users\\PC\\Documents\\Java\\res\\textures\\inventoryScreen.png"));
        //Down
        playerDown=new BufferedImage[2];
        playerDown[0]=sheet1.crop(32*4,0,32,32);
        playerDown[1]=sheet1.crop(32*5,0,32,32);
        //Up
        playerUp=new BufferedImage[2];
        playerUp[0]=sheet1.crop(32*6,0,32,32);
        playerUp[1]=sheet1.crop(32*7,0,32,32);
        //Left
        playerLeft=new BufferedImage[2];
        playerLeft[0]=sheet1.crop(32*4,32,32,32);
        playerLeft[1]=sheet1.crop(32*5,32,32,32);
        //Right
        playerRight=new BufferedImage[2];
        playerRight[0]=sheet1.crop(32*6,32,32,32);
        playerRight[1]=sheet1.crop(32*7,32,32,32);
        //BUTTON
        Button=new BufferedImage[2];
        Button[0]= sheet1.crop(32 * 6, 32 * 4, 32 * 2, 32);
        Button[1] = sheet1.crop(32 * 6, 32 * 5, 32 * 2, 32);


        c1 = sheet.crop(0, 0, width, height);
        c2 = sheet.crop(width, 0, width, height);
        c3 = sheet.crop(width * 2, 0, width, height);
        c4 = sheet.crop(width * 3, 0, width, height);
        tree=sheet.crop(0,height,width*4,height);






    }

}
