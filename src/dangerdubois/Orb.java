package dangerdubois;


import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jconklin2391
 */
public class Orb {

    public static int speed = 4;
    public static float speedRotation = 4;
    static int orbWidth = 32;
    static int orbHeight = 32;
    
    private float rotation;
    private float positionX;
    private float positionY;
    private boolean visible;
    private int timemoved; 
    String direction;
    static ArrayList<Image> orbImagesList = new ArrayList();
    Image orbImage;
    Shape orbHitbox;
static Random random = new Random();
    
    Orb(float posX, float posY, String dir) throws SlickException{
        positionX = posX;
        positionY = posY;
        direction = dir;
        timemoved = 0;
        visible = true;
        rotation = random.nextFloat() * 360;
        //orbImage = new Image("res/obama1.png");
        orbImage = orbImagesList.get(random.nextInt(orbImagesList.size()));
        orbHitbox = new Rectangle(positionX + (orbWidth / 2), positionY + (orbHeight / 2), orbWidth, orbHeight);
    }
    
    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setHitBox() {
        this.orbHitbox = new Rectangle(this.getPositionX() + (orbWidth / 2), this.getPositionY()+ (orbHeight / 2), orbWidth, orbHeight);
    
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getTimemoved() {
        return timemoved;
    }

    public void setTimemoved(int timemoved) {
        this.timemoved = timemoved;
    }
    static void getAllImages() throws SlickException{
        orbImagesList.add(new Image("res/obama.png"));
        orbImagesList.add(new Image("res/obama1.png"));
        orbImagesList.add(new Image("res/obama2.png"));
        orbImagesList.add(new Image("res/obama3.png"));
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
