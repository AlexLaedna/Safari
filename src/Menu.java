import java.awt.*;
public class Menu {
    private int buttonWidth;
    private int buttonHight;
    private int transp1=0;
    private int transp2=0;
    private  Color color1;
    private String strPlay,strExit;
    public Menu(){
        buttonHight=30;
        buttonWidth=60;
        color1=Color.WHITE;
        strPlay="Play!";
        strExit="Exit";
    }

    public void update(){
        if(GameScreen.mouseX>GameScreen.WIDTH/2-buttonWidth/2&&
                GameScreen.mouseX<GameScreen.WIDTH/2+buttonWidth/2&&
                GameScreen.mouseY>GameScreen.HEIGHT/2-buttonHight/2-20&&
                GameScreen.mouseY<GameScreen.HEIGHT/2+buttonHight/2-20){
            transp1=60;
            if(GameScreen.leftMouse){
               GameScreen.states=GameScreen.STATES.PLAY;
            }
        }else transp1=0;
        if(GameScreen.mouseX>GameScreen.WIDTH/2-buttonWidth/2&&
                GameScreen.mouseX<GameScreen.WIDTH/2+buttonWidth/2&&
                GameScreen.mouseY>GameScreen.HEIGHT/2-buttonHight/2+20&&
                GameScreen.mouseY<GameScreen.HEIGHT/2+buttonHight/2+20){
            transp2=60;
            if(GameScreen.leftMouse){
                GameScreen.states=GameScreen.STATES.EXIT;
            }
        }else transp2=0;
    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GameScreen.WIDTH/2-buttonWidth/2,GameScreen.HEIGHT/2-buttonHight/2+20,buttonWidth,buttonHight);
        g.drawRect(GameScreen.WIDTH/2-buttonWidth/2,GameScreen.HEIGHT/2-buttonHight/2-20,buttonWidth,buttonHight);
        g.setColor(new Color(255,255,255,transp1));
        g.fillRect(GameScreen.WIDTH/2-buttonWidth/2,GameScreen.HEIGHT/2-buttonHight/2-20,buttonWidth,buttonHight);
        g.setColor(new Color(255,255,255,transp2));
        g.fillRect(GameScreen.WIDTH/2-buttonWidth/2,GameScreen.HEIGHT/2-buttonHight/2+20,buttonWidth,buttonHight);
        g.setStroke(new BasicStroke(1));

        g.setColor(color1);
        g.setFont(new Font("Consolas",Font.BOLD,20));
        long length1=(int)g.getFontMetrics().getStringBounds(strPlay,g).getWidth();
        g.drawString(strPlay,(int)GameScreen.WIDTH/2-length1/2,(int)GameScreen.HEIGHT/2-20+buttonHight/4);
        g.setFont(new Font("Consolas",Font.BOLD,20));
        long length2=(int)g.getFontMetrics().getStringBounds(strExit,g).getWidth();
        g.drawString(strExit,(int)GameScreen.WIDTH/2-length2/2,(int)GameScreen.HEIGHT/2+20+buttonHight/4);

    }
}
