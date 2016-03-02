/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangerdubois;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


/**
 *
 * @author jconklin2391
 */
public class Arrow {
     public float x;
	public float y;
	public boolean isvisible = true;
	Image currentImage;
	Shape hitbox;
	Image arrowImageUp = new Image("res/arrowUp.png");
        Image arrowImageDown = new Image("res/arrowDown.png");
        Image arrowImageLeft = new Image("res/arrowLeft.png");
        Image arrowImageRight = new Image("res/arrowRight.png");
        String direction;
        static int speed = 8;
        private int startX, startY, width = 32, height = 32;

    float hitboxX = x+32f;

    float hitboxY = this.y;

    
    
    public Shape rect;

	Arrow(float a, float b, String dir) throws SlickException {
		this.x = a;
		this.y = b;
                this.direction=dir;
		this.hitbox = new Rectangle(a, b, width, height);// 64 is the width of the item
		//.currentImage = arrowImage;
                
                
}
        public  float getX() {

		return this.x;

	}

	public  float getY() {

		return y;

	}

	public  float gethitboxX() {

		return x + 22;

	}

	public  float gethitboxY() {

		return y;
	}

	public  void sethitboxX() {

		hitboxX = gethitboxX();

	}

	public  void setplayershitboxY() {

		hitboxY = gethitboxY();

	}
        public void getImage(){
           
            if(direction == "up"){
                currentImage = arrowImageUp;
            }
            else if(direction == "down"){
                currentImage = arrowImageDown;
            }
            else if(direction == "right"){
                currentImage = arrowImageRight;
            }
            else if(direction == "left"){
                currentImage = arrowImageLeft;
            }
          
        }
        
}
