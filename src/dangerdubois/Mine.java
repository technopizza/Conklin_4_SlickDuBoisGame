/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangerdubois;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jconklin2391
 */
public class Mine {
    
    static int[][] mineCoords;
    static int numberOfMines = 10;
    
    private static int hitBoxWidth = 32;
    private static int hitBoxHeight = 32;
    Image mineImage = new Image("res/mine.png");
    static int damage = 50;
    static SpriteSheet explosionSpriteSheet;
    static Animation baseExplosionAnimation;
    
    private float positionX;
    private float positionY;
    private boolean visible;
    private Image currentImage;
    private Shape hitBox;
    private int explosionCount;
    private Animation mineExplosionAnimation;

    Mine(float posX, float posY) throws SlickException{
        positionX = posX;
        positionY = posY;
        visible = true;
        currentImage = mineImage;
        explosionCount = 0;
        mineExplosionAnimation = baseExplosionAnimation;
        hitBox = new Rectangle(positionX + (hitBoxWidth / 2), positionY + (hitBoxHeight / 2), hitBoxWidth, hitBoxHeight);
    }
    
    public static int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public static void setHitBoxWidth(int aHitBoxWidth) {
        hitBoxWidth = aHitBoxWidth;
    }

    public static int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public static void setHitBoxHeight(int aHitBoxHeight) {
        hitBoxHeight = aHitBoxHeight;
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

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    public void setHitBox(Shape hitBox) {
        this.hitBox = hitBox;
    }
    
    static void playAnimation(int x, int y){
        baseExplosionAnimation.draw(x, y);
    }
    
    static void configureAnimations() throws SlickException{
        explosionSpriteSheet = new SpriteSheet("res/explosion_transparent.png", 64, 64, 0);
        baseExplosionAnimation = new Animation();
        baseExplosionAnimation.setAutoUpdate(false);
        int delay = 25;
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(4, 4), delay);
       
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(0, 0), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(1, 0), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(2, 0), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(3, 0), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(4, 0), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(0, 1), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(1, 1), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(2, 1), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(3, 1), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(4, 1), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(0, 2), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(1, 2), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(2, 2), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(3, 2), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(4, 2), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(0, 3), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(1, 3), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(2, 3), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(3, 3), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(4, 3), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(0, 4), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(1, 4), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(2, 4), delay);
        baseExplosionAnimation.addFrame(explosionSpriteSheet.getSprite(3, 4), delay);
    }

    public int getExplosionCount() {
        return explosionCount;
    }

    public void setExplosionCount(int explosionCount) {
        this.explosionCount = explosionCount;
    }

    public Animation getMineExplosionAnimation() {
        return mineExplosionAnimation;
    }

    public void setMineExplosionAnimation(Animation mineExplosionAnimation) {
        this.mineExplosionAnimation = mineExplosionAnimation;
    }
}
