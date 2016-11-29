import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class InputKey implements KeyListener {
    private Player player;
    public InputKey(Player player){
        this.player=player;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDx(-3);
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.setDx(3);
        }
        if (key == KeyEvent.VK_UP) {
            player.setDy(-3);
        }
        if (key == KeyEvent.VK_DOWN) {
            player.setDy(3);
        }
        if(key==KeyEvent.VK_ESCAPE){
           GameScreen.states= GameScreen.STATES.MENUE;
        }
        if(key==KeyEvent.VK_SPACE){
            GameScreen.pause = !GameScreen.pause;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            player.dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            player.dy = 0;
        }
    }
}