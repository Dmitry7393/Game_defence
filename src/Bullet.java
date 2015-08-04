import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
 
public class Bullet{
 
  
    public double bullet_x;
    public double bullet_y;
    public double current_direction_x; 
    public double current_direction_y;
    private Image image;
    protected  double angle ;
    AffineTransform affine ;
    boolean one_rotate = false;
    public Bullet() {
        /*ImageIcon ii = new ImageIcon(this.getClass().getResource(sprite));
        image = ii.getImage();*/
    }
    public void set_rotate(double angle1)
    {
    }
    public char get_type()
    {
    	return '0';
    }
  public double get_spr_w()
  {
	  return image.getWidth(null);
  }
  public double get_spr_h()
  {
	  return image.getHeight(null);
  }
    public Image getImage() {
        return image;
    }
    public AffineTransform rotateX()
    {
          return affine;
    }
}