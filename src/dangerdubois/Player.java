/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangerdubois;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jconklin2391
 */

public class Player {
    
    private int positionX, positionY, hitBoxPositionX, hitBoxPositionY, speed, health;
    private Animation moveNorth, moveSouth, moveWest, moveEast, standNorth, standSouth, standWest, standEast, thrustNorth, thrustSouth, thrustWest, thrustEast;
    private Shape hitBox;
    private SpriteSheet playerSpriteSheet;
    private float pdelta;

       Player(int posX, int posY, int spd, int hth) {
           
           positionX = posX;
           positionY = posY;
           hitBoxPositionX = positionX;
           hitBoxPositionY = positionY;
           hitBox = new Rectangle(hitBoxPositionX, hitBoxPositionY, 64, 64);
           //spriteInit(path);
           speed = spd;
           health = hth;
           
       }
        
        
        
         void spriteInit(String path) throws SlickException{
        playerSpriteSheet = new SpriteSheet(path, 64, 64, 0);
        
        standNorth = new Animation();
        standNorth.addFrame(playerSpriteSheet.getSprite(0, 8), 110);

        moveNorth = new Animation();

        moveNorth.setAutoUpdate(false);
        moveNorth.addFrame(playerSpriteSheet.getSprite(0, 8), 200);
        moveNorth.addFrame(playerSpriteSheet.getSprite(1, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(2, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(3, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(4, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(5, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(6, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(7, 8), 200);

        moveNorth.addFrame(playerSpriteSheet.getSprite(8, 8), 200);

        standSouth = new Animation();
        standSouth.addFrame(playerSpriteSheet.getSprite(0, 10), 330);

        moveSouth = new Animation();

        moveSouth.setAutoUpdate(false);

        moveSouth.addFrame(playerSpriteSheet.getSprite(0, 10), 200);
        moveSouth.addFrame(playerSpriteSheet.getSprite(1, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(2, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(3, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(4, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(5, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(6, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(7, 10), 200);

        moveSouth.addFrame(playerSpriteSheet.getSprite(8, 10), 200);

        standWest = new Animation();
        standWest.addFrame(playerSpriteSheet.getSprite(0, 9), 330);

        moveWest = new Animation();

        moveWest.setAutoUpdate(false);

        moveWest.addFrame(playerSpriteSheet.getSprite(0, 9), 200);
        moveWest.addFrame(playerSpriteSheet.getSprite(1, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(2, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(3, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(4, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(5, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(6, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(7, 9), 200);

        moveWest.addFrame(playerSpriteSheet.getSprite(8, 9), 200);

        standEast = new Animation();
        standEast.addFrame(playerSpriteSheet.getSprite(0, 11), 200);

        moveEast = new Animation();

        moveEast.setAutoUpdate(false);

        moveEast.addFrame(playerSpriteSheet.getSprite(0, 11), 200);
        moveEast.addFrame(playerSpriteSheet.getSprite(1, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(2, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(3, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(4, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(5, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(6, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(7, 11), 200);

        moveEast.addFrame(playerSpriteSheet.getSprite(8, 11), 200);

        thrustEast = new Animation();

        thrustEast.setAutoUpdate(false);

        thrustEast.addFrame(playerSpriteSheet.getSprite(0, 7), 150);
        thrustEast.addFrame(playerSpriteSheet.getSprite(1, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(2, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(3, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(4, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(5, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(6, 7), 150);

        thrustEast.addFrame(playerSpriteSheet.getSprite(7, 7), 150);

        thrustWest = new Animation();

        thrustWest.setAutoUpdate(false);

        thrustWest.addFrame(playerSpriteSheet.getSprite(0, 5), 150);
        thrustWest.addFrame(playerSpriteSheet.getSprite(1, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(2, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(3, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(4, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(5, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(6, 5), 150);

        thrustWest.addFrame(playerSpriteSheet.getSprite(7, 5), 150);

        thrustNorth = new Animation();

        thrustNorth.setAutoUpdate(false);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(0, 4), 150);
        thrustNorth.addFrame(playerSpriteSheet.getSprite(1, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(2, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(3, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(4, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(5, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(6, 4), 150);

        thrustNorth.addFrame(playerSpriteSheet.getSprite(7, 4), 150);

        thrustSouth = new Animation();

        thrustSouth.setAutoUpdate(false);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(0, 6), 150);
        thrustSouth.addFrame(playerSpriteSheet.getSprite(1, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(2, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(3, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(4, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(5, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(6, 6), 150);

        thrustSouth.addFrame(playerSpriteSheet.getSprite(7, 6), 150);

        
            
        }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getHitBoxPositionX() {
        return hitBoxPositionX;
    }

    public void setHitBoxPositionX(int hitBoxPositionX) {
        this.hitBoxPositionX = hitBoxPositionX;
    }

    public int getHitBoxPositionY() {
        return hitBoxPositionY;
    }

    public void setHitBoxPositionY(int hitBoxPositionY) {
        this.hitBoxPositionY = hitBoxPositionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Animation getMoveNorth() {
        return moveNorth;
    }

    public void setMoveNorth(Animation moveNorth) {
        this.moveNorth = moveNorth;
    }

    public Animation getMoveSouth() {
        return moveSouth;
    }

    public void setMoveSouth(Animation moveSouth) {
        this.moveSouth = moveSouth;
    }

    public Animation getMoveWest() {
        return moveWest;
    }

    public void setMoveWest(Animation moveWest) {
        this.moveWest = moveWest;
    }

    public Animation getMoveEast() {
        return moveEast;
    }

    public void setMoveEast(Animation moveEast) {
        this.moveEast = moveEast;
    }

    public Animation getStandNorth() {
        return standNorth;
    }

    public void setStandNorth(Animation standNorth) {
        this.standNorth = standNorth;
    }

    public Animation getStandSouth() {
        return standSouth;
    }

    public void setStandSouth(Animation standSouth) {
        this.standSouth = standSouth;
    }

    public Animation getStandWest() {
        return standWest;
    }

    public void setStandWest(Animation standWest) {
        this.standWest = standWest;
    }

    public Animation getStandEast() {
        return standEast;
    }

    public void setStandEast(Animation standEast) {
        this.standEast = standEast;
    }

    public Animation getThrustNorth() {
        return thrustNorth;
    }

    public void setThrustNorth(Animation thrustNorth) {
        this.thrustNorth = thrustNorth;
    }

    public Animation getThrustSouth() {
        return thrustSouth;
    }

    public void setThrustSouth(Animation thrustSouth) {
        this.thrustSouth = thrustSouth;
    }

    public Animation getThrustWest() {
        return thrustWest;
    }

    public void setThrustWest(Animation thrustWest) {
        this.thrustWest = thrustWest;
    }

    public Animation getThrustEast() {
        return thrustEast;
    }

    public void setThrustEast(Animation thrustEast) {
        this.thrustEast = thrustEast;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    public void setHitBox(Shape hitBox) {
        this.hitBox = hitBox;
    }

    public SpriteSheet getPlayerSpriteSheet() {
        return playerSpriteSheet;
    }

    public void setPlayerSpriteSheet(SpriteSheet playerSpriteSheet) {
        this.playerSpriteSheet = playerSpriteSheet;
    }

    public float getPdelta() {
        return pdelta;
    }

    public void setPdelta(float pdelta) {
        this.pdelta = pdelta;
    }
        
        

}
