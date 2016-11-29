import javax.swing.JFrame;
public class Game {
    public static void main(String [] args){
        JFrame frame = new JFrame("Safari");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameScreen());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}