import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.*;

public class StandartEnemy {
    private double z,x,y,ex,ey;
    int helth;
    protected BufferedImage imageRes;
    protected Image image;
    String sprite= "Images\\StandartEnemy.png";
    private int speed=3;

    public StandartEnemy(Player player) {
        Random ran1=new Random(System.currentTimeMillis());
        int r1=ran1.nextInt(1);
        if(r1==0){
            Random ran2=new Random(System.currentTimeMillis());
            int r2=ran2.nextInt(1);
            if(r2==0){
            x=0;
            y=Math.random()*GameScreen.HEIGHT;
            }else {
                x=GameScreen.WIDTH;
                y=Math.random()*GameScreen.HEIGHT;
            };
        }else {
            Random ran2=new Random(System.currentTimeMillis());
            int r2= ran2.nextInt(1);
            if(r2==0){
                x=Math.random()*GameScreen.WIDTH;
                y=0;
            }else {
                x=Math.random()*GameScreen.WIDTH;
                y=GameScreen.HEIGHT;
            };
        };
        helth=2;
        z=atan2(player.y-y,player.x-x)*180/PI;
        double rad=Math.toRadians(z);
        ex = Math.cos(rad) * speed;
        ey = Math.sin(rad) * speed;
        try {
            imageRes = ImageIO.read(new File(sprite));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image=imageRes.getSubimage(0,0,114,126);
    }

    public void hit(){helth--;}
    public boolean remove(){
        return helth <= 0;
    }
    public void update() {
        x += ex;
        y += ey;
        if (x<0&&ex<0)ex=-ex;
        if (x>GameScreen.WIDTH&&ex>0)ex=-ex;
        if (y<0&&ey<0)ey=-ey;
        if (y>GameScreen.HEIGHT&&ey>0)ey=-ey;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        double zx=x-15;
        double zy=y-15;
        g.drawImage(image,(int)zx,(int)zy,(int)zx+30,(int)zy+30,0,0,114,126,null);

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
