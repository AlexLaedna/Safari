import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class InputMouse implements MouseListener,MouseMotionListener {
    Player player;
    public double z;
    public InputMouse(Player player){
        this.player=player;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mouse=e.getButton();
        if(mouse== MouseEvent.BUTTON1){
            player.shot=true;
            GameScreen.leftMouse=true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int mouse=e.getButton();
        if(mouse== MouseEvent.BUTTON1){
            player.shot=false;
            GameScreen.leftMouse=false;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        player.setMdy(e.getY());
        player.setMdx(e.getX());
        GameScreen.mouseX=e.getX();
        GameScreen.mouseY=e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        player.mdy=e.getY();
        player.mdx=e.getX();
        GameScreen.mouseX=e.getX();
        GameScreen.mouseY=e.getY();
    }
}