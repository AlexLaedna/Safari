import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class GameScreen extends JPanel implements Runnable {

    public static int WIDTH=800,HEIGHT=600;

    private static Thread thread;
    private boolean running;
    public static boolean leftMouse;
    public static boolean pause;
    private BufferedImage image;
    private Graphics2D g;

    private double averageFPS;

    public static int mouseX;
    public static int mouseY;

    private Player player;
    public static ArrayList<Bullets> bullets;
    public static ArrayList<StandartEnemy> enemies;
    public static Menu menu;

    public static enum STATES{
        MENUE,
        PLAY,
        EXIT
    }
    public static STATES states=STATES.MENUE;

    public GameScreen(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    public void run() {
        leftMouse=false;
        running = true;
        pause=false;

        int count=0;
        long startTime;
        long URDTimeMillis;
        int FPS=30;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        int maxFrameCount = 30;
        long targetTime = 1000 / FPS;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        player=new Player(300,300);
        menu=new Menu();
        addKeyListener(new InputKey(player));
        addMouseListener(new InputMouse(player));
        addMouseMotionListener(new InputMouse(player));
        enemies=new ArrayList<>();
        bullets = new ArrayList<>();
        enemies.add(new StandartEnemy(player));
        while (running){
            if(states.equals(STATES.MENUE)){
                menu.draw(g);
                menu.update();
                gameDraw();
                while(pause){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (states.equals(STATES.PLAY)){
                gameUpdate();
                gameRender();
                gameDraw();
                while(pause){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(states.equals(STATES.EXIT)){
                System.exit(0);
            }
            startTime = System.nanoTime();
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, WIDTH, HEIGHT);

            //fps
            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMillis;
            try {
                Thread.sleep(waitTime);
            }
            catch(Exception ignored) {
            }
            frameCount++;
            if(frameCount == maxFrameCount) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas",Font.BOLD,60));
        String s="G A M E  O V E R";
        long length2=(int)g.getFontMetrics().getStringBounds(s,g).getWidth();
        g.drawString(s,(int)GameScreen.WIDTH/2-length2/2,(int)GameScreen.HEIGHT/2+10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas",Font.BOLD,40));
        //String s1="TOTAL SCORE: "+Integer.toString(player.score);
        //long leight1=s1.length();
        //g.drawString(s1,(int)GameScreen.WIDTH/2-leight1/2,(int)GameScreen.HEIGHT/2+50);
        gameDraw();
    }
    public void gameRender(){
        player.draw(g);
        for(int i=0;i<bullets.size();i++){bullets.get(i).draw(g);};
        for(int i=0;i<enemies.size();i++){enemies.get(i).draw(g);}
        g.setColor(Color.BLUE);
        g.drawRect(10,10,100,10);
        g.fillRect(10,10,10*player.helth,10);
        g.setColor(Color.WHITE);
        String s="SCORE: "+Integer.toString(player.score);
        long leight=s.length();
        g.drawString(s,GameScreen.WIDTH/2-leight,20);
    }
    public void gameUpdate(){
        player.update();
        CreateEnemies(player);
        for (int i = 0; i < bullets.size(); i++) {
            boolean remove = bullets.get(i).update();
            if(remove) {
                bullets.remove(i);
                i--;
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        //collision enemy and bullet
        for (int i =0;i<enemies.size();i++){
            double ex=enemies.get(i).getX();
            double ey=enemies.get(i).getY();
            for (int j=0;j<bullets.size();j++){
                double bx=bullets.get(j).getX();
                double by=bullets.get(j).getY();
                double dx=ex-bx;
                double dy=ey-by;
                double dist=Math.sqrt(dx*dx+dy*dy);
                if((int)dist<bullets.get(j).getR()+15){
                    enemies.get(i).hit();
                    bullets.remove(j);
                    j--;
                    boolean remove=enemies.get(i).remove();
                    if(remove){
                        enemies.remove(i);
                        i--;
                        player.scorePlus();
                        break;
                    }
                }
            }
        }
    //collision enemy and player
        for(int i=0;i<enemies.size();i++){
            double ex=enemies.get(i).getX();
            double ey=enemies.get(i).getY();
            double px=player.getX();
            double py=player.getY();
            double dx=ex-px;
            double dy=ey-py;
            double dist=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
            if(dist<25){
                player.hit();
                enemies.get(i).hit();
                boolean eremove=enemies.get(i).remove();
                if(eremove){
                    enemies.remove(i);
                    i--;
                    player.scorePlus();
                }
                boolean premove=player.remove();
                if(premove){
                running=false;
                }
            }
        }
    }

    long enemyCreateTimer=0;
    long enemyCreateDelay=1000;
    long enemyCreateDiff=0;

    //private Image image;

    public void CreateEnemies(Player player){

        if(GameScreen.enemies.size()<100&&enemyCreateTimer==0){
            enemyCreateTimer=System.nanoTime();
        }
        if(enemyCreateTimer>0){
            enemyCreateDiff+=(System.nanoTime()-enemyCreateTimer)/300000;
            enemyCreateTimer=System.nanoTime();
        }
        if(enemyCreateDiff>enemyCreateDelay){
            GameScreen.enemies.add(new StandartEnemy(player));
            enemyCreateTimer=0;
            enemyCreateDiff=0;
        }
    }
    public void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}