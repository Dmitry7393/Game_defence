import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class FireBall extends Bullet {
	 private String sprite = "Data/Sprite/bullet/fireball.png";
	    private Image image;
	    AffineTransform affine ;
	  
	    public FireBall()
	    {
	        ImageIcon ii = new ImageIcon(this.getClass().getResource(sprite));
	        image = ii.getImage();
	    }
	    public void set_rotate(double angle1)
	    {
	    	if(one_rotate == false)
	    	{
	    		angle = angle1;
	    		one_rotate = true;
	    	}
	    }
	    public AffineTransform rotateX()
	    {
		    	   affine = new AffineTransform();
			       affine.rotate(Math.toRadians(angle), bullet_x + image.getWidth(null) / 2, 
			        bullet_y + image.getHeight(null) / 2);
			       return affine;
	    }
	    public char get_type()
	    {
	    	return 'f';
	    }
		  public double get_spr_w()
		  {
			  return image.getWidth(null);
		  }
		  public double get_spr_h()
		  {
			  return image.getHeight(null);
		  }
	    public Image getImage() 
	    {
	        return image;
	    }
}
