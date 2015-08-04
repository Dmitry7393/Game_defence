import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Enemy2 extends Enemy {
	  private Image image;
	  private String sprite = "Data/Sprite/enemy/enemy2.png";
	  private int live = 4;
	  AffineTransform affine ;
	Enemy2()
	{
        ImageIcon ii = new ImageIcon(this.getClass().getResource(sprite));
        image = ii.getImage();
        x = 0;
        y = 0;
	}
    public void kill()
    {
    	live = 0;
    }
	public String get_type()
	{
		return "enemy2";
	}
    public Image getImage() {
        return image;
    } 

    public AffineTransform rotateX(double a)
    {
    	   affine = new AffineTransform();
          affine.rotate(Math.toRadians(a), x + image.getWidth(null) / 2, 
                  y + image.getHeight(null) / 2);
          return affine;
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
 
    public double getY() {
        return y;
    }
    public void setX(double x1)
    {
    	x = x1;
    }
    public void setY(double y1)
    {
    	y = y1;
    }
    public void dec_live()
    {
    	live--;
    }
    public int get_live()
    {
    	return live;
    }
    public void push()
    {
    	System.out.println("push");
    	x = x - 90*v_x;
    	y = y - 90*v_y;
    }
}
