import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;
 
public class GameScreen extends JPanel implements ActionListener{
        private static final long serialVersionUID = 1L;
        //game object
        protected Sprite player;
        private Image fon;
        private Image explosion;
        private Image live;
        private Image pointer;
        private Vector<Explosion> v_explosion;
        protected  Vector<Bullet> bullet;
        protected  Vector<Enemy> enemy;
        private int count_sphere;
        private int count_fireball;
        private int count_rocket;
        private int player_live;
        Color c = new Color(0, 0, 255);
      	Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
      	Font font2 = new Font("Tahoma", Font.BOLD|Font.ITALIC, 20);
      	 Bullet image_bullet;
        //physical logic
        AffineTransform affine = new AffineTransform();
        protected double angleX, angleY;
        public double angle; 
        public double angle2;
        private double player_speed = 1.7;
        int middleX ; 
        int middleY;
        private boolean left_mouse_pressed = false;
        //game logic
         private Random random;
         private Timer timer;
         private int level = 1;
         private double frequency;
         private boolean allow_shoot = false;
         private int time_level;
         javax.swing.Timer timer3;
         double time_start;     
       java.util.Timer timerj;
       int distance;
       private int chance_enemy1;
       private int chance_enemy2;
       private int chance_enemy3;
       private int chance_enemy4;
       private double time_game = 0;
       private 	   boolean   type_bullet1 = true;
       private 	   boolean   type_bullet2 = false;
       private 	   boolean   type_bullet3 = false;
       private double  speed_enemy1;
       private double  speed_enemy2; 
       private double  speed_enemy3; 
       private double  speed_enemy4;
       Collision collision = new Collision();
        public void start_game()
        {
        	level = 1;
        	time_game = 0;
        	frequency = 2;
        	player_live = 5;
        	time_level = 60;
        	enemy.removeAllElements();
        	set_weapon(45, 30, 0);
        	set_enemy(2, 9, 16, 2);
        	set_speed_enemy(0.7, 0.7, 0.6, 1);
        }
        public void next_level()
        {
        	level++;
        	time_game += 0.1;
        	frequency = 2;
        	player_live = 5;
        	time_level = 50;
        	enemy.removeAllElements();	
        	if(level == 2)
        	{
                set_enemy(4,3, 5, 4);
                set_weapon(40, 30, 5);
                set_speed_enemy(0.8, 0.8, 0.7, 1.2);
        	}
        	
        }
        public void set_weapon(int w1, int w2, int w3)
        {
        	 count_sphere   = w1;
             count_fireball = w2;
             count_rocket   = w3;
        }
        public void set_enemy(int ch1, int ch2, int ch3, int ch4)
        {
            chance_enemy1 = ch1;
            chance_enemy2 = ch2;
            chance_enemy3 = ch3;
            chance_enemy4 = ch4;
        }
        public void set_speed_enemy(double v1, double v2, double v3, double v4)
        {
        	speed_enemy1 = v1;
        	speed_enemy2 = v2;
        	speed_enemy3 = v3;
        	speed_enemy4 = v4;
        }
        public GameScreen()
        {
                addKeyListener(new TAdapter());
                addMouseListener(new CustomListener());
                addMouseMotionListener(new CustomListener());
                setFocusable(true);
                setBackground(Color.BLACK);
                setDoubleBuffered(true);
                player = new Sprite();
                player.setX(500);
                player.setY(250);
                player.set_speed(player_speed);
                bullet = new Vector<Bullet>();
                enemy = new Vector<Enemy>();
                v_explosion = new Vector<Explosion>();
                ImageIcon ii = new ImageIcon(this.getClass().getResource("Data/Sprite/background/background1.png"));
      	      	fon = ii.getImage();
                timer = new Timer(5, this);
                timer.start();
                timerj = new java.util.Timer();
                timerj.schedule( task, 100 );
                ImageIcon ii2 = new ImageIcon(this.getClass().getResource("Data/Sprite/player/explosion.png"));
                explosion = ii2.getImage();
                
                ImageIcon i3 = new ImageIcon(this.getClass().getResource("Data/Sprite/player/live.png"));
                live = i3.getImage();
                i3 = new ImageIcon(this.getClass().getResource("Data/Sprite/player/str.png"));
                pointer = i3.getImage();
                image_bullet = new Bullet();
                start_game();       
        }
        public void create_explosion(double x, double y)
        {
        	v_explosion.addElement(new Explosion());
        	v_explosion.get(v_explosion.size()-1).x = x;
        	v_explosion.get(v_explosion.size()-1).y = y;
        }
        public static int random_int(int Min, int Max)
        {
        	return (Min + (int)(Math.random() * ((Max - Min) + 1)));
        }
        public void compute()
        {
          	for(int i = 0; i < bullet.size(); i++)
          	{
          		bullet.get(i).bullet_x = bullet.get(i).bullet_x + 3*bullet.get(i).current_direction_x;
          		bullet.get(i).bullet_y = bullet.get(i).bullet_y + 3*bullet.get(i).current_direction_y;
          	}
          	for(int i = 0; i < bullet.size(); i++)
          	{
          	  if(bullet.get(i).bullet_x > 1366 || bullet.get(i).bullet_x < 0 ||
          	     bullet.get(i).bullet_y > 768 || bullet.get(i).bullet_y < 0 )
          		  bullet.remove(i);
          	}
          	//Check collision bullet with enemy
          	for(int i = 0; i < bullet.size(); i++)
    		{
          		for(int j = 0; j < enemy.size(); j++)
    			{
          			if(collision.collision_bullet_enemy(18, (int)bullet.get(i).bullet_x + 18, (int)bullet.get(i).bullet_y+18, //bullet 
          					(int)enemy.get(j).getX(), (int)enemy.get(j).getY(), //point x1, y1
          					(int)enemy.get(j).getX()+(int)enemy.get(j).get_spr_w(), (int)enemy.get(j).getY(), //point x2, y2
          					(int)enemy.get(j).getX()+(int)enemy.get(j).get_spr_w(), (int)enemy.get(j).getY()+(int)enemy.get(j).get_spr_h(),
          					(int)enemy.get(j).getX(), (int)enemy.get(j).getY()+(int)enemy.get(j).get_spr_h() //point x4, y4
          						) == true)
        				{
          				//	create_explosion(bullet.get(i).bullet_x, bullet.get(i).bullet_y);         					
          					if(bullet.get(i).get_type() == 's') //simple bullet 
          					{
          						enemy.get(j).dec_live();
          					}
          					if(bullet.get(i).get_type() == 'f')  //fireball
          					{
          						enemy.get(j).dec_live();
          						enemy.get(j).dec_live();
          					}
          					if(bullet.get(i).get_type() == 'r')  //rocket 
          					{
          						enemy.get(j).dec_live();
          						enemy.get(j).dec_live();
          						enemy.get(j).dec_live();
          					}
          					if(enemy.get(j).get_live() <= 0)
          					{
          						enemy.remove(j);
          					}
          					else  //push enemy
          					{
          						enemy.get(j).push();
          					}
          					bullet.remove(i);
        					break;
        				}
    			}
    		}
	    	//Преследование игрока
	    	for(int i = 0; i < enemy.size(); i++)
	    	{
	    		if(enemy.get(i).get_type() == "enemy1" || enemy.get(i).get_type() == "enemy2" ||
	    		   enemy.get(i).get_type() == "enemy3")
	    		{
	    			angle2 = Math.atan2(player.getY() - enemy.get(i).getY(), player.getX() -  enemy.get(i).getX()) * 180 / 3.14 ;  
			    	double a = player.getX() -  enemy.get(i).getX();
			    	double b = player.getY() -  enemy.get(i).getY();
			    	double kvadrat = Math.sqrt((a*a + b*b));
			    	double  tempx = a / kvadrat;		
			    	double  tempy = Math.sqrt(1 - tempx*tempx);
			    	if(b < 0) tempy = -tempy;
			    	 enemy.get(i).v_x = enemy.get(i).speed*tempx;
			    	 enemy.get(i).v_y = enemy.get(i).speed*tempy;
			    	 enemy.get(i).setX( ( enemy.get(i).getX() + enemy.get(i).speed*tempx) ) ;
			    	 enemy.get(i).setY( ( enemy.get(i).getY() + enemy.get(i).speed*tempy) ) ;
	    		}
	    		else
	    		{
	    			if(enemy.get(i).created == false)
	    			{
	    				angle2 = Math.atan2(player.getY() - enemy.get(i).getY(), player.getX() -  enemy.get(i).getX()) * 180 / 3.14 ;  
				    	double a = player.getX() -  enemy.get(i).getX();
				    	double b = player.getY() -  enemy.get(i).getY();
				    	double kvadrat = Math.sqrt((a*a + b*b));
				    	double  tempx = a / kvadrat;		
				    	double  tempy = Math.sqrt(1 - tempx*tempx);
				    	if(b < 0) tempy = -tempy;
				    	 enemy.get(i).v_x = enemy.get(i).speed*tempx;
				    	 enemy.get(i).v_y = enemy.get(i).speed*tempy;
				    	 enemy.get(i).created = true;
	    			}
			    	 enemy.get(i).setX( ( enemy.get(i).getX() + enemy.get(i).get_vx()) ) ;
			    	 enemy.get(i).setY( ( enemy.get(i).getY() + enemy.get(i).get_vy()) ) ;
	    		}
	    	}
	    	for(int j = 0; j < enemy.size(); j++)
			{
		    	if(collision.collision_with_box((int)player.getX()+14,  (int)player.getY()+28,  (int)player.getX()+114,  (int)player.getY()+95,
		    			(int)enemy.get(j).getX()+22, (int)enemy.get(j).getY()+30,
		    			(int)enemy.get(j).getX()+102, (int)enemy.get(j).getY()+96
		    			) == true)
		    	{
		    		enemy.remove(j);
		    		player_live--;
		    		if(player_live == 0)
		    		{
		    			JOptionPane.showMessageDialog(null, "Game over!");
		    			start_game();
		    			
		    		}
		    	}
			}
	    	if(time_game > frequency)
	    	{
	    		generate_enemy();
	    		time_game = 0;
	    	}
	    	if(left_mouse_pressed == true && allow_shoot == true)
	    	{
   
	    	}
          	time_game += 0.01;
          	frequency -= 0.0001;
        }
        public void generate(int x1, int x2, int y1, int y2,double sp_enemy1, double sp_enemy2, double sp_enemy3, double sp_enemy4)
        {
			if(random_int(1, chance_enemy1) == 1)
			{
        		enemy.addElement(new Enemy1());
        		enemy.get(enemy.size()-1).setX(random_int(x1, x2));
        		enemy.get(enemy.size()-1).setY(random_int(y1, y2));
        		enemy.get(enemy.size()-1).speed = sp_enemy1;
        		return;
			}
			if(random_int(1, chance_enemy2) == 1)
			{
        		enemy.addElement(new Enemy2());
        		enemy.get(enemy.size()-1).setX(random_int(x1, x2));
        		enemy.get(enemy.size()-1).setY(random_int(y1, y2));
        		enemy.get(enemy.size()-1).speed = sp_enemy2;
        		return;
			}
			if(random_int(1, chance_enemy3) == 1)
			{
        		enemy.addElement(new Enemy3());
        		enemy.get(enemy.size()-1).setX(random_int(x1, x2));
        		enemy.get(enemy.size()-1).setY(random_int(y1, y2));
        		enemy.get(enemy.size()-1).speed = sp_enemy3;
        		return;
			}
			if(random_int(1, chance_enemy4) == 1)
			{
        		enemy.addElement(new Enemy4());
        		enemy.get(enemy.size()-1).setX(random_int(x1, x2));
        		enemy.get(enemy.size()-1).setY(random_int(y1, y2));
        		enemy.get(enemy.size()-1).speed = sp_enemy4;
        		return;
			}
        }
        public void generate_enemy()
        {
        	random = new Random();
    		int side = random.nextInt(4)+1;
    		if(enemy.size() <= 20)
    		{
    			if(side == 1)
        		{
            		generate(10, 1024, -50, 50, speed_enemy1, speed_enemy2, speed_enemy3, speed_enemy4);
        		}
        		 if(side == 2)
        		{
    	    		generate(1024, 1124, 10, 600, speed_enemy1, speed_enemy2, speed_enemy3, speed_enemy4);
        		}
        		if(side == 3)
        		{
        			generate(10, 1024, 600, 650, speed_enemy1, speed_enemy2, speed_enemy3, speed_enemy4);
        		}
        		if(side == 4)
        		{
        			generate(-50, 10, 0, 650, speed_enemy1, speed_enemy2, speed_enemy3, speed_enemy4);
        		}
    		}
        }
        TimerTask task = new TimerTask()
        {
            public void run()
            {
            	  ActionListener actionListener = new ActionListener() {
                      public void actionPerformed(ActionEvent actionEvent)
                      {
                    	  time_level--;
                    	  if(time_level == 0)
                    	  {
                    		  next_level();
                    	  }
                      }   
                  };
                  Timer timer = new Timer( 1000, actionListener );
                  timer.start();
            }
        };
        public void paint(Graphics g)
        {
        	super.paint(g);
          	Graphics2D g2d = (Graphics2D)g;
          	c.getAlpha();
          	g.setFont(font2);
        	g.setColor(c);
        	g.drawImage(fon, 0, 0, 1024, 700, null);
        	
        	 if(type_bullet1 == true)
        	 g.drawImage(pointer, 30, 550, 49, 47, null);
        	 
        	 image_bullet = new Sphere();
        	 g.drawImage(image_bullet.getImage(), 40, 600, 24, 24, null);
        	 g.drawString(Integer.toString(count_sphere), 35, 650);
        
        	 if(type_bullet2 == true)
        	 g.drawImage(pointer, 85, 550, 49, 47, null);
        	 image_bullet = new FireBall();
        	 g.drawImage(image_bullet.getImage(), 95, 600, 24, 24, null);
        	 g.drawString(Integer.toString(count_fireball), 95, 650);
        	 
        	 if(type_bullet3 == true)
        	 g.drawImage(pointer, 140, 550, 49, 47, null);
        	 image_bullet = new Rocket();
        	 g.drawImage(image_bullet.getImage(), 145, 595, 40, 40, null);
        	 g.drawString(Integer.toString(count_rocket), 150, 650);
        	 distance = 10;
        	//Draw life
        	for(int i = 0; i < player_live; i++)
        	{
        		g.drawImage(live, distance, 10, 11, 57, null);
        		distance += 20;
        	}
        	//Create bullet
        	for(int i = 0; i < bullet.size(); i++)
          	{
        		if(type_bullet1 == true && count_sphere > 0)
        		{
              		g2d.drawImage(bullet.get(i).getImage(), (int)bullet.get(i).bullet_x, (int)bullet.get(i).bullet_y, this);   
        		}
        		if(type_bullet2 == true && count_fireball > 0)
        		{
              		g2d.drawImage(bullet.get(i).getImage(), (int)bullet.get(i).bullet_x, (int)bullet.get(i).bullet_y, this);   
        		}
        		if(type_bullet3 == true && count_rocket != 0)
        		{
        			bullet.get(i).set_rotate(angle);
            		g2d.setTransform(bullet.get(i).rotateX());
        			g2d.drawImage(bullet.get(i).getImage(), (int)bullet.get(i).bullet_x, (int)bullet.get(i).bullet_y, this);       
        		}
          	}
          	g2d.setTransform(player.rotateX(angle));
          	g2d.drawImage(player.getImage(), (int)player.getX(),  (int)player.getY(), this);
         	for(int i = 0; i < v_explosion.size(); i++)
         	{
         		g.drawImage(explosion, (int)v_explosion.get(i).x, (int)v_explosion.get(i).y, 59, 57, null);
         	}
         	for(int i = 0; i < enemy.size(); i++)
          	{
         		double povorot3 = Math.atan2(player.getY() - enemy.get(i).getY(), player.getX() - enemy.get(i).getX()) * 180 / 3.14 ;  
         		g2d.setTransform(enemy.get(i).rotateX(povorot3)); //new	 	
              	g2d.drawImage(enemy.get(i).getImage(), (int)enemy.get(i).getX(), (int)enemy.get(i).getY(), this);
              	g2d.setTransform(enemy.get(i).rotateX(0)); //new 
              	g.drawString(Integer.toString(enemy.get(i).get_live()), (int)enemy.get(i).getX()+(int)enemy.get(i).get_spr_w()/2, (int)enemy.get(i).getY()); 
          	}
        	g.setFont(font);
       		g2d.setTransform(affine);
       		g.drawString("Level: " + Integer.toString(level), 710, 50);
        	g.drawString("Next level: " + Integer.toString(time_level), 710, 90);
        }
        public void mouseClicked(MouseEvent e)
        {
        }
        public void actionPerformed2(ActionEvent e) 
        {
        }   
        public void actionPerformed(ActionEvent e)
        {
        	 compute();
        	 player.move();
             repaint();  
        }      
        public class CustomListener extends MouseAdapter  implements MouseListener, MouseMotionListener
        {
        	public void mouseMoved(MouseEvent e)
            {
    	        middleX =  (int)player.getX() + (int) player.get_spr_w()/2;
    	        middleY =  (int)player.getY() + (int) player.get_spr_h()/2;
    	    	angleX = ( ( e.getX() - middleX ) ) / 100.0f;		
    	    	angleY = ( ( e.getY() - middleY ) ) / 100.0f;
    	    	angle = Math.atan2(angleY,angleX) * 180 / 3.14; 
            }
        	public void mouseDragged(MouseEvent e)
            {
    	        middleX =  (int)player.getX() + (int) player.get_spr_w()/2;
    	        middleY =  (int)player.getY() + (int) player.get_spr_h()/2;
    	    	angleX = ( ( e.getX() - middleX ) ) / 100.0f;		
    	    	angleY = ( ( e.getY() - middleY ) ) / 100.0f;
    	    	angle = Math.atan2(angleY,angleX) * 180 / 3.14;
            }
           public void mouseClicked(MouseEvent e) {	
              }
      	    public void mouseEntered(MouseEvent e) {
	        }
	
	        public void mouseExited(MouseEvent e) {
	        }
	
	        public void mousePressed(MouseEvent e)
	        {    
	        	left_mouse_pressed = true;

        		
	        }
	        public void mouseReleased(MouseEvent e) {
	        	left_mouse_pressed = false;
	      		double kvadrat = Math.sqrt(angleX*angleX + angleY*angleY);
         		double tempx = angleX / kvadrat;		
         		double tempy = Math.sqrt(1 - tempx*tempx);
        		if(angleY < 0) tempy = -tempy;
        		Bullet b = new Bullet();
        		boolean next = false;
        		if(type_bullet1 == true && count_sphere > 0)
        		{
        			b  = new Sphere();
        			count_sphere--;
        			next = true;
        		}
        		if(type_bullet2 == true && count_fireball > 0)
        		{
        			b = new FireBall();
        			count_fireball--;
        			next = true;
        		}
        		if(type_bullet3 == true && count_rocket > 0)
        		{
        			b = new Rocket();
        			count_rocket--;
        			next = true;
        		}
           		if(next == true)
           		{
            		b.bullet_x = player.getX() + 52  + 5*Math.cos(angle);  // + 50; //koef;
    	        	b.bullet_y = player.getY() + 49  + 5*Math.sin(angle);  //player.getY() + 35; //25;
            		b.current_direction_x = tempx; 
            		b.current_direction_y = tempy; 
            		bullet.addElement(b);
            		allow_shoot = false;
           		}
	        }
        }
        private class TAdapter extends KeyAdapter implements KeyListener 
        {
 
	        public void keyReleased(KeyEvent e) 
	        {
	        	player.keyReleased(e);
	        }
	        public void keyPressed(KeyEvent e)
	        {
	        	int key = e.getKeyCode();
	        	  if (key == KeyEvent.VK_1) 
	        	  {
	                  type_bullet1 = true;
	                  type_bullet2 = false;
	                  type_bullet3 = false;
	              }
	        	  if (key == KeyEvent.VK_2) 
	        	  {
	                  type_bullet1 = false;
	                  type_bullet2 = true;
	                  type_bullet3 = false;
	              }
	        	  if (key == KeyEvent.VK_3) 
	        	  {
	                  type_bullet1 = false;
	                  type_bullet2 = false;
	                  type_bullet3 = true;
	              }
	        	 player.keyPressed(e);
	        }  
        }     
}