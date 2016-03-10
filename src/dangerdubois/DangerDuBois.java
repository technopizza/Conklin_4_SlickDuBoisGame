package dangerdubois;

import org.newdawn.slick.state.*;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.BasicGame;

import org.newdawn.slick.Font;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.TrueTypeFont;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

public class DangerDuBois extends BasicGameState {

//    public Item healthpotion, healthpotion1;
//    public Item1 speedpotion, speedpotion1;
//    public ItemWin antidote;
//    public Ninja ninja;
    public Enemy indianBow1;
    public Enemy indianBow2;
    public Enemy indianBow3;
    public Enemy indianBow4;
    public Enemy indianBow5;
    public Enemy indianBow6;
    public Enemy indianBow7;
    public Enemy indianBow8;
    public Enemy indianBow9;
    
    Sound enemydeath, explosion, eat, ouch;

    public Player duBois;

    Treasure smallprize;
    Treasure grandprize;
    Treasure grandeprize;

    Mine trap, trap1, trap2;

    public ArrayList<Treasure> treasures = new ArrayList();

    public ArrayList<Enemy> enemies = new ArrayList();
    public ArrayList<Enemy> enemiesBow = new ArrayList();

    public ArrayList<Arrow> arrows = new ArrayList();

    public ArrayList<Orb> orbs = new ArrayList();

    public ArrayList<Mine> mines = new ArrayList();

    public ArrayList<Item> stuff = new ArrayList();

    public ArrayList<Item1> stuff1 = new ArrayList();

    public ArrayList<ItemWin> stuffwin = new ArrayList();

    private int[][] enemyLocations;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // duBois stuff
    private Animation sprite;
    boolean attacking = false;
    int attackCounter = 0;
    String direction = "down";

    /**
     *
     * The collision map indicating which tiles block movement - generated based
     *
     * on tile properties
     */
    // changed to match size of sprites & map
    private static final int tileSize = 64;
    private static final int tilesX = 50;
    private static final int tilesY = 50;
    // screen width and height won't change
    private static final int SCREEN_WIDTH = tilesX * tileSize;
    private static int currentsteps = 0;
    private static final int SCREEN_HEIGHT = tilesY * tileSize;

    public DangerDuBois(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        duBois = new Player(128, 64*23, 4, 100);

        enemydeath = new Sound("res/coedeath.ogg");
        explosion = new Sound("res/explosion.wav");
        ouch = new Sound("res/ouch.wav");
        eat = new Sound("res/yes.wav");
        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);

        grassMap = new TiledMap("res/dubois1.tmx");

        duBois.spriteInit("res/duboisprite.png");

        camera = new Camera(gc, grassMap);

        Mine.configureAnimations();

        sprite = duBois.getMoveSouth();

        Orb.getAllImages();

        // *****************************************************************
        // Obstacles etc.
        // build a collision map based on tile properties in the TileD map
        Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        
        

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                // int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                // Why was this changed?
                // It's a Different Layer.
                // You should read the TMX file. It's xml, i.e.,human-readable
                // for a reason
                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "blocked", "false");

                if ("true".equals(value)) {

                    Blocked.blocked[xAxis][yAxis] = true;

                }

            }

        }

        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "mine", "false");

                if ("true".equals(value)) {

                            mines.add(new Mine(tileSize * xAxis, tileSize * yAxis));


                }

            }

        }
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                String value = grassMap.getTileProperty(tileID,
                        "monster", "false");

                if (!("false".equals(value))) {
                        value = value.intern();
                            enemies.add(new Enemy(tileSize * xAxis, tileSize * yAxis, value.toString().trim(), false));
                            //System.out.println("new guy at (" + xAxis + ", " + yAxis + ") facing " + value);

                }

            }

        }
        smallprize = new Treasure(tileSize * 20, tileSize * 15);
        grandprize = new Treasure(tileSize * 7, tileSize * 15);
        grandeprize = new Treasure(tileSize * 17, tileSize * 8);

//        trap = new Mine(256, 128);
//        trap1 = new Mine(320, 128);
//        trap2 = new Mine(384, 128);
//        mines.add(trap);
//        mines.add(trap1);
//        mines.add(trap2);
        treasures.add(smallprize);
        treasures.add(grandprize);
        treasures.add(grandeprize);

//        indianBow1 = new Enemy(tileSize * 10, tileSize * 9, "down", false);
//        indianBow2 = new Enemy(tileSize * 10, tileSize * 2, "right", false);
//        indianBow3 = new Enemy(tileSize * 12, tileSize * 5, "down", false);
//        indianBow4 = new Enemy(tileSize * 5, tileSize * 8, "right", false);
//        indianBow5 = new Enemy(tileSize * 14, tileSize *10, "up", false);
//        indianBow6 = new Enemy(tileSize * 5, tileSize * 20, "up", false);
//        indianBow7 = new Enemy(tileSize * 15, tileSize * 21, "up", false);
//        indianBow8 = new Enemy(tileSize * 20, tileSize * 20, "left", false);
//        indianBow9 = new Enemy(tileSize * 20, tileSize * 19, "up", false);

//        enemies.add(indianBow1);
//        indianBow1.configBow();
//
//        enemies.add(indianBow2);
//        indianBow2.configBow();
//        enemies.add(indianBow3);
//        indianBow3.configBow();
//        enemies.add(indianBow4);
//        indianBow4.configBow();
//        enemies.add(indianBow5);
//        indianBow5.configBow();
//        enemies.add(indianBow6);
//        indianBow6.configBow();
//        enemies.add(indianBow7);
//        indianBow7.configBow();
//        enemies.add(indianBow8);
//        indianBow8.configBow();
//        enemies.add(indianBow9);
//        indianBow9.configBow();
        for(Enemy e : enemies){
            e.configBow();
        }
        
        
}

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) duBois.getPositionX(), (int) duBois.getPositionY());

        camera.drawMap();

        camera.translateGraphics();

        sprite.draw((int) duBois.getPositionX(), (int) duBois.getPositionY());

        //g.draw(duBois.getHitBox());
        g.setColor(Color.red);
        g.drawString("Health: " + duBois.getHealth(), camera.cameraX + 10,
                camera.cameraY + 10);
        g.drawString("Score: " + duBois.getScore(), camera.cameraX + 20,
                camera.cameraY + 20);

        drawenemies(g);

        for (Mine m : mines) {
            if (m.getExplosionCount() > 0) {
                m.getMineExplosionAnimation().draw(m.getPositionX(), m.getPositionY());
            }
        }

        for (Mine m : mines) {
            if (m.isVisible()) {
                m.getCurrentImage().draw(m.getPositionX(), m.getPositionY());
                // draw the hitbox
                //g.draw(o.orbHitbox);

            }
        }

        for (Orb o : orbs) {
            if (o.isVisible()) {
                
                o.orbImage.draw(o.getPositionX(), o.getPositionY());
                // draw the hitbox
                //g.draw(o.orbHitbox);

            }
        }

        for (Arrow a : arrows) {
            if (a.isvisible) {
                a.currentImage.draw(a.x, a.y);
                // draw the hitbox
                g.draw(a.hitbox);

            }
        }

        for (Treasure b : treasures) {
            if (b.isvisible) {
                b.currentImage.draw(b.x, b.y);
                // draw the hitbox
                //g.draw(b.hitbox);

            }
        }

        for (Item i : stuff) {
            if (i.isvisible) {
                i.currentImage.draw(i.x, i.y);
                // draw the hitbox
                //g.draw(i.hitbox);

            }
        }

        for (Item1 h : stuff1) {
            if (h.isvisible) {
                h.currentImage.draw(h.x, h.y);
                // draw the hitbox
                //g.draw(h.hitbox);

            }
        }

        for (ItemWin w : stuffwin) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
                // draw the hitbox
                //g.draw(w.hitbox);

            }
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        Input input = gc.getInput();

        float fdelta = delta * 0.4f;

        duBois.setPdelta(fdelta);

        for (Mine m : mines) {

            if (m.getExplosionCount() > 0) {
                m.getMineExplosionAnimation().update(delta);
                m.setExplosionCount(m.getExplosionCount() - 1);
            }
        }

        for (Arrow a : arrows) {
            if (a.direction == "up") {
                a.y -= Arrow.speed;
            } else if (a.direction == "down") {
                a.y += Arrow.speed;
            } else if (a.direction == "right") {
                a.x += Arrow.speed;
            } else if (a.direction == "left") {
                a.x -= Arrow.speed;
            }
            a.distance += Arrow.speed;
        }
        for (Orb o : orbs) {
            o.setRotation(o.getRotation() + Orb.speedRotation);
            if (o.direction == "up") {
                o.setPositionY(o.getPositionY() - Orb.speed);
            } else if (o.direction == "down") {
                o.setPositionY(o.getPositionY() + Orb.speed);
            } else if (o.direction == "right") {
                o.setPositionX(o.getPositionX() + Orb.speed);
            } else if (o.direction == "left") {
                o.setPositionX(o.getPositionX() - Orb.speed);

            }
            //o.orbImage.setRotation(o.getRotation());
            o.setTimemoved(o.getTimemoved() + Orb.speed);

        }
        if (attackCounter == 0) {
            attacking = false;
        }
        if (!(currentsteps > 0) && input.isKeyDown(Input.KEY_Z)) {
            attacking = true;
            attackCounter = 8;
            if (direction == "up") {
                sprite = duBois.getThrustNorth();
            } else if (direction == "down") {
                sprite = duBois.getThrustSouth();
            } else if (direction == "left") {
                sprite = duBois.getThrustWest();
            } else if (direction == "right") {
                sprite = duBois.getThrustEast();
            }
        }
        if (!(currentsteps > 0) && !attacking) {

            if (input.isKeyDown(Input.KEY_UP)) {

                if (direction != "up") {
                    sprite = duBois.getStandNorth();
                    direction = "up";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(duBois.getPositionX(), duBois.getPositionY() - tileSize))) {
                        currentsteps = tileSize / duBois.getSpeed();
                        sprite = duBois.getMoveNorth();
                    }

                }
            } else if (input.isKeyDown(Input.KEY_DOWN)) {

                if (direction != "down") {
                    sprite = duBois.getStandSouth();
                    direction = "down";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(duBois.getPositionX(), duBois.getPositionY() + tileSize))) {
                        currentsteps = tileSize / duBois.getSpeed();
                        sprite = duBois.getMoveSouth();
                    }

                }

            } else if (input.isKeyDown(Input.KEY_LEFT)) {

                if (direction != "left") {
                    sprite = duBois.getStandWest();
                    direction = "left";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(duBois.getPositionX() - tileSize, duBois.getPositionY()))) {
                        currentsteps = tileSize / duBois.getSpeed();
                        sprite = duBois.getMoveWest();
                    }

                }

            } else if (input.isKeyDown(Input.KEY_RIGHT)) {

                if (direction != "right") {
                    sprite = duBois.getStandEast();
                    direction = "right";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(duBois.getPositionX() + tileSize, duBois.getPositionY()))) {
                        currentsteps = tileSize / duBois.getSpeed();
                        sprite = duBois.getMoveEast();
                    }

                }
            } else if (input.isKeyPressed(Input.KEY_SPACE)) {
                orbs.add(new Orb(duBois.getPositionX(), duBois.getPositionY(), direction));
                if(direction == "left" ){
                
                orbs.get(orbs.size() - 1).orbImage = orbs.get(orbs.size() - 1).orbImage.getFlippedCopy(true, false);
            }
            
            if(direction == "up" ){
                
                orbs.get(orbs.size() - 1).orbImage = orbs.get(orbs.size() - 1).orbImage.getFlippedCopy(false, true);
            
                if(direction == "down" ){
                
                orbs.get(orbs.size() - 1).orbImage = orbs.get(orbs.size() - 1).orbImage.getFlippedCopy(true, true);
            }
            }
            }
            if (currentsteps > 0) {
                for (Enemy e : enemies) {
                    if (e.isIsAlive()) {
                        Arrow thisArrow = new Arrow(e.getskX() + 16, e.getskY() + 16, e.direction);
                        thisArrow.getImage();
                        arrows.add(thisArrow);
                    }
                }
            }

        } else {
            sprite.update(delta);
            if (attackCounter > 0) {
                attackCounter--;
            }

            if (!attacking && currentsteps > 0) {

                currentsteps -= 1;
                
                if (direction == "up") {

                    duBois.setPositionY(duBois.getPositionY() - duBois.getSpeed());

                } else if (direction == "down") {
                    duBois.setPositionY(duBois.getPositionY() + duBois.getSpeed());
                } else if (direction == "left") {
                    duBois.setPositionX(duBois.getPositionX() - duBois.getSpeed());
                } else if (direction == "right") {
                    duBois.setPositionX(duBois.getPositionX() + duBois.getSpeed());
                }
duBois.setHitBoxPositionX(duBois.getPositionX());
duBois.setHitBoxPositionY(duBois.getPositionY());
            }
            moveenemies();

        }
        duBois.getHitBox().setLocation(duBois.getHitBoxPositionX(), duBois.getHitBoxPositionY());

        for (Treasure i : treasures) {

            if (duBois.getHitBox().intersects(i.hitbox)) {

                if (i.isvisible) {

                    duBois.setHealth(duBois.getHealth() + 50);
                    i.isvisible = false;
                    eat.play();
                }

            }
        }

        for (Mine m : mines) {

            if (duBois.getHitBox().intersects(m.getHitBox())) {

                if (m.isVisible()) {

                    m.setVisible(false);
                    m.setExplosionCount(38);
                    duBois.setHealth(duBois.getHealth() - Mine.damage);
                    explosion.play();
                    //ouch.play();
                }

            }
        }

        for (Arrow a : arrows) {

            if (a.isvisible) {
                if ((a.x > (grassMap.getWidth() * tileSize) || a.y > (grassMap.getHeight() * tileSize)) || isBlocked(a.x, a.y) || a.distance >= Arrow.speed * 6 * 4) {

                    a.isvisible = false;
//                    if (arrows.size() > 1) {
//                        arrows.remove(a);
//                    }

                } else if (duBois.getHitBox().intersects(a.hitbox)) {
                    duBois.setHealth(duBois.getHealth() - 10);
                    ouch.play();
                    a.isvisible = false;
                    
//                     if (arrows.size() > 1) {
//                        arrows.remove(a);
//                    }
                }

            }

        }

        for (Enemy e : enemies) {

            e.rect.setLocation(e.getskhitboxX(), e.getskhitboxY());

        }

        for (Orb o : orbs) {

            o.setHitBox();

        }

        if (!arrows.isEmpty()) {

            for (Arrow a : arrows) {

                try {
                    a.hitbox.setLocation(a.gethitboxX(), a.gethitboxY());
                } catch (IndexOutOfBoundsException e) {

                }

            }
        }

        for (Enemy e : enemies) {

            if (e.isIsAlive() && duBois.getHitBox().intersects(e.rect)) {

                duBois.setHealth(duBois.getHealth() - 10);

            }

        }

        for (Orb o : orbs) {

            if (o.isVisible()) {
                if ((o.getPositionX() > (grassMap.getWidth() * tileSize) || o.getPositionY() > (grassMap.getHeight() * tileSize)) || isBlocked(o.getPositionX(), o.getPositionY())) {

                    if (o.getDirection() == "up") {
                        o.setDirection("down");
                    } else if (o.getDirection() == "down") {
                        o.setDirection("up");
                    } else if (o.getDirection() == "right") {
                        o.setDirection("left");
                    } else if (o.getDirection() == "left") {
                        o.setDirection("right");
                    }
//                    if (arrows.size() > 1) {
//                        arrows.remove(a);
//                    }

                }

            }

        }

        for (Orb o : orbs) {
            if (o.getTimemoved() >= (64 * 5)) {
                o.setVisible(false);
            }

        }

        for (Enemy e : enemies) {
            for (Orb o : orbs) {

                if (o.orbHitbox.intersects(e.rect) && o.isVisible() && e.isIsAlive()) {
                    duBois.setScore(duBois.getScore() + 10);
                    enemydeath.play();
                    e.setIsAlive(false);
                    o.setVisible(false);
                    //o.setVisible(false);
                    //enemies.remove(e);
                    //orbs.remove(o);
                    //System.out.println("HIT");

                }

            }

        }

        for (int en1 = 0;
                en1 < Enemy.getNumberOfEnemies();
                en1++) {

            for (int en2 = 0; en2 < Enemy.getNumberOfEnemies(); en2++) {

                if (enemies.get(en1).rect.intersects(enemies.get(en2).rect)) {

                    if (enemies.get(en1).getID() != enemies.get(en2).getID()) {

                        if (enemies.get(en1).health > 0 && enemies.get(en2).health > 0) {

                            enemies.get(en1).health -= 1;

                            enemies.get(en2).health -= 1;

                        }

                    }

                }

            }

        }
        //duBois.health -= counter / 1000;
        if (duBois.getHealth()
                <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (duBois.getScore()
                >= 100) {
            makevisible();
            duBois.setScore(0);
            sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (Item1 h : stuff1) {

            h.isvisible = true;
        }

        for (Item i : stuff) {

            i.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / tileSize;

        int yBlock = (int) ty / tileSize;

        return Blocked.blocked[xBlock][yBlock];

        // this could make a better kludge
    }

    private void moveenemies() throws SlickException {

        for (Enemy e : enemies) {

            //  e.setdirection();
            e.move();

        }

    }

    private void drawenemies(Graphics g) throws SlickException {

        try {

            for (Enemy e : enemies) {
                //if(e.isAlive != false){
                //System.out.println("The current selection is: " +e.currentanime);

                if (e.isIsAlive()) {
                    e.currentanime.draw(e.Bx, e.By);
                    //g.draw(e.rect);
                }
            }

        } catch (IndexOutOfBoundsException e) {

            System.err.println("IndexOutOfBoundsException: "
                    + e.getMessage());

        }

    }

}
