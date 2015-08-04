
public class Collision {
 public Collision()
 {
	 
 }
 public boolean   collision_with_box(int x, int y, int dx, int dy, int x2, int y2, int dx2, int dy2)
	 {
			if((x2 >= x) && (x2 <= dx) && (y2 >= y) && (y2 <= dy))
			{
				return true;
			}
			if( (dx2 >= x) && (dx2 <= dx) && (y2 >= y) && (y2 <= dy) )
			{
				return true;
			}
			if( (dx2 >= x) && (dy2 >= y) && (dy2 <= dy) && (dx2 <= dx))
			{
				return true;
			}
			if((x2 >= x) && (x2 <= dx) && (dy2 >= y) && (dy2 <= dy))
			{
				return true;
			}
			return false;
	 }
 public boolean collision_bullet_enemy(int radius, int center_x, int center_y, 
			 int x1, int y1, int x2, int y2,
			 int x3, int y3, int x4, int y4)
	{
		if(center_x >= x1 && center_x <= x2 && center_y >= y1 && center_y <= y4)
		return true;			
		return false;
	}
}
