import java.awt.*;
public class Bullets {
    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private Color color1;
    public Bullets(double angle, int x, int y) {
        this.x = x;
        this.y = y;
        r = 2;
        double rad = Math.toRadians(angle);
        double speed = 10;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;
        color1 = Color.WHITE;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getR() { return r; }
    public boolean update() {
        x += dx;
        y += dy;
        return x < -r || x > GameScreen.WIDTH + r || y < -r || y > GameScreen.HEIGHT + r;
    }
    public void draw(Graphics g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
    }
}