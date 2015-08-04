import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
 
public class Sprite implements Runnable{
 
    private String sprite = "Data/Sprite/player/player.png";  //
 
    private double dx;
    private double dy;
    private double x;
    private double y;
    private double speed;
    //private boolean r = false;
    private Image image;
    AffineTransform affine ;
   private double angle = 0;
    public Sprite() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(sprite));
        image = ii.getImage();
        x = 0;
        y = 0;
    }
 
    public AffineTransform rotateX(double a)
    {
    	   affine = new AffineTransform();
          affine.rotate(Math.toRadians(a), x + image.getWidth(null) / 2, 
                  y + image.getHeight(null) / 2);
          return affine;
    }
    public void move() {
        x += dx;
        y += dy;
    }
  public double get_angle()
  {
     return angle;
  }
  public double get_spr_w()
  {
	  return image.getWidth(null);
  }
  public double get_spr_h()
  {
	  return image.getHeight(null);
  }
    public double getX() {
        return x;
    }
    public void setX(int x1)
    {
    	x = x1;
    }
    public void setY(int y1)
    {
    	y = y1;
    }
    public double getY() {
        return y;
    }
 
    public Image getImage() {
        return image;
    }
    public void set_speed(double sp)
    {
    	speed = sp;
    }
    public void keyPressed(KeyEvent e) {
 
        int key = e.getKeyCode();
       
        if (key == KeyEvent.VK_SHIFT) {
                //r = true;
        }
        if (key == KeyEvent.VK_A) {
            dx = -speed;
        }
 
        if (key == KeyEvent.VK_D) {
            dx = speed;
        }
 
        if (key == KeyEvent.VK_W) {
            dy = -speed;
        }
 
        if (key == KeyEvent.VK_S) {
            dy = speed;
        }
    }
 
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            dx = 0;
        }
 
        if (key == KeyEvent.VK_D) {
            dx = 0;
        }
 
        if (key == KeyEvent.VK_W) {
            dy = 0;
        }
 
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
   
    public void run() {
       
    	Sprite s1 = new Sprite();
              new Thread(s1).start();          
    }
}