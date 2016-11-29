import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
public class Player {
    protected BufferedImage imageRes;
    protected Image image;
    public int x,y,dx,dy,helth=10;
    protected int mdx;
    protected int mdy;
    protected double z;
    protected boolean shot;
    private long firingTimer;
    private long firingDelay;
    String sprite= "Images\\hero_small.png";
    public int score;
    public Player(int x,int y){
        this.x=x;
        this.y=y;
        z=0;
        score=0;
        shot = false;
        firingTimer = System.nanoTime();
        firingDelay = 300;
        try {
            imageRes = ImageIO.read(new File(sprite));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void update(){
        x+=dx;
        y+=dy;
        if(x < 21) x = 21;
        if(y < 18) y = 18;
        if(x > GameScreen.WIDTH - 21) x = GameScreen.WIDTH - 21;
        if(y > GameScreen.HEIGHT - 18) y = GameScreen.HEIGHT - 18;
        z=atan2(mdy-y,mdx-x)*180/PI;
        if(z<=180&&z>157.5){
            image=imageRes.getSubimage(0,68,50,34);//left
        }else {
            if(z<=112.5&&z>67.5){
                image=imageRes.getSubimage(0,0,50,34);//down
            }else {
                if(z<=22.5&&z>-22.5){
                    image=imageRes.getSubimage(0,204,50,34);//right
                }else{
                    if(z<=-67.5&&z>-112.5){
                        image=imageRes.getSubimage(0,136,50,34);//up
                    }else{
                        if(z<=-157.5&&z>-180){
                            image=imageRes.getSubimage(0,68,50,34);//left
                        }else{
                            if(z<=-112.5&&z>-157.5){//up-left
                                image=imageRes.getSubimage(0,102,50,34);
                            }else{
                                if(z<=-22.5&&z>-67.5){//up-right
                                    image=imageRes.getSubimage(0,170,50,34);
                                }else{
                                    if(z<=157.5&&z>112.5){//down-left
                                        image=imageRes.getSubimage(0,34,50,34);
                                    }else{//down-right
                                        image=imageRes.getSubimage(0,238,50,34);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(shot){
            long elapsed = (System.nanoTime() - firingTimer) / 500000;

            if(elapsed > firingDelay) {

                firingTimer = System.nanoTime();
                GameScreen.bullets.add(new Bullets(z,x,y));
            }
        }
    }
    public void hit(){helth--;}
    public void draw(Graphics2D g){g.drawImage(image,x-25,y-17,null);}
    public void scorePlus(){score++;}
    public int getX() {return  x;}
    public int getY() {return y;}
    public int getMdx() {return mdx;}
    public int getMdy() {return  mdy;}
    public Image getImage(){return image;}

    public boolean remove() {return helth <= 0;
    }

    public void setMdx(int mdx) {this.mdx=mdx;}

    public void setMdy(int mdy) {
        this.mdy = mdy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}