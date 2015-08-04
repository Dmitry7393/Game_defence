
import java.awt.Image;
import java.awt.geom.AffineTransform;
public class Enemy
{
    private Image image;
    AffineTransform affine ;
    protected double v_x;
    protected double v_y;
    protected double x;
    protected double y;
    protected double speed;
    public boolean created = false;
    public AffineTransform rotateX(double a)
    {
        return affine;
    }
    public Image getImage()
    {
        return image;
    }
    public void kill()
    {
    	
    }
    public double get_vx()
    {
    	return v_x;
    }
    public double get_vy()
    {
    	return v_y;
    }
	public String get_type()
	{
		return " ";
	}
    public double get_angle()
    {
        return 0;
    }
    public double get_spr_w()
    {
  	  return 0;
    }
    public double get_spr_h()
    {
  	  return 0;
    }
    public double getX()
    {
          return 0;
    }
   public int get_live()
   {
	   return 55;
   }
   public void push()
   {
	   
   }
   public void dec_live() { }
  public double getY()
  {
      return 0;
  }
  public void setX(double x1)
  {
  
  }
  public void setY(double y1)
  {
  	
  }
}