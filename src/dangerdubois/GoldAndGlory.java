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

public class GoldAndGlory extends BasicGameState {

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

    private int[][] traps;

    private static TiledMap grassMap;

    private static AppGameContainer app;

    private static Camera camera;

    public static int counter = 0;

    // Player stuff
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
    private static final int tilesY = 100;
    // screen width and height won't change
    private static final int SCREEN_WIDTH = tilesX * tileSize;
    private static int currentsteps = 0;
    private static final int SCREEN_HEIGHT = tilesY * tileSize;

    public GoldAndGlory(int xSize, int ySize) {

    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);

        gc.setShowFPS(false);

        grassMap = new TiledMap("res/d4.tmx");

        camera = new Camera(gc, grassMap);

        Player.spriteInit();
        Mine.configureAnimations();

        sprite = Player.walkSouth;

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


        smallprize = new Treasure(tileSize * 20, tileSize * 15);
        grandprize = new Treasure(tileSize * 7, tileSize * 15);
        grandeprize = new Treasure(tileSize * 17, tileSize * 8);

        trap = new Mine(256, 128);
        trap1 = new Mine(320, 128);
        trap2 = new Mine(384, 128);
        mines.add(trap);
        mines.add(trap1);
        mines.add(trap2);
        treasures.add(smallprize);
        treasures.add(grandprize);
        treasures.add(grandeprize);

        indianBow1 = new Enemy(tileSize * 10, tileSize * 9, "down", false);
        indianBow2 = new Enemy(tileSize * 10, tileSize * 2, "right", false);
        indianBow3 = new Enemy(tileSize * 12, tileSize * 5, "down", false);
        indianBow4 = new Enemy(tileSize * 5, tileSize * 8, "right", false);
        indianBow5 = new Enemy(tileSize * 14, tileSize * 11, "up", false);
        indianBow6 = new Enemy(tileSize * 5, tileSize * 20, "up", false);
        indianBow7 = new Enemy(tileSize * 15, tileSize * 21, "up", false);
        indianBow8 = new Enemy(tileSize * 20, tileSize * 20, "left", false);
        indianBow9 = new Enemy(tileSize * 20, tileSize * 19, "up", false);

        
        
        
        enemies.add(indianBow1);
        indianBow1.configBow();

        enemies.add(indianBow2);
        indianBow2.configBow();
        enemies.add(indianBow3);
        indianBow3.configBow();
        enemies.add(indianBow4);
        indianBow4.configBow();
        enemies.add(indianBow5);
        indianBow5.configBow();
        enemies.add(indianBow6);
        indianBow6.configBow();
        enemies.add(indianBow7);
        indianBow7.configBow();
        enemies.add(indianBow8);
        indianBow8.configBow();
        enemies.add(indianBow9);
        indianBow9.configBow();

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

        sprite.draw((int) Player.x, (int) Player.y);

        //g.draw(Player.rect);
        
        g.drawString("Health: " + Player.health / 1000, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("treasure: " + (int) (Player.gold), camera.cameraX + 10,
                camera.cameraY + 40);

        drawenemies();

        for (Mine m: mines){
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
                //g.draw(a.hitbox);

            }
        }

        for (Treasure b : treasures) {
            if (b.isvisible) {
                b.currentImage.draw(b.x, b.y);
                // draw the hitbox
                //g.draw(i.hitbox);

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

        Player.setpdelta(fdelta);

        
        for (Mine m: mines){
            
            if(m.getExplosionCount() > 0){
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
            o.orbImage.setRotation(o.getRotation());
            o.setTimemoved(o.getTimemoved() + Orb.speed);

        }
        if (attackCounter == 0) {
            attacking = false;
        }
        if (!(currentsteps > 0) && input.isKeyDown(Input.KEY_Z)) {
            attacking = true;
            attackCounter = 8;
            if (direction == "up") {
                sprite = Player.thrustUp;
            } else if (direction == "down") {
                sprite = Player.thrustDown;
            } else if (direction == "left") {
                sprite = Player.thrustLeft;
            } else if (direction == "right") {
                sprite = Player.thrustRight;
            }
        }
        if (!(currentsteps > 0) && !attacking) {

            if (input.isKeyDown(Input.KEY_UP)) {

                if (direction != "up") {
                    sprite = Player.faceUp;
                    direction = "up";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x, Player.y - tileSize))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = Player.walkUp;
                    }

                }
            } else if (input.isKeyDown(Input.KEY_DOWN)) {

                if (direction != "down") {
                    sprite = Player.faceDown;
                    direction = "down";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x, Player.y + tileSize))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = Player.walkDown;
                    }

                }

            } else if (input.isKeyDown(Input.KEY_LEFT)) {

                if (direction != "left") {
                    sprite = Player.faceLeft;
                    direction = "left";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x - tileSize, Player.y))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = Player.walkLeft;
                    }

                }

            } else if (input.isKeyDown(Input.KEY_RIGHT)) {

                if (direction != "right") {
                    sprite = Player.faceRight;
                    direction = "right";
                    currentsteps = 0;
                } else {
                    if (currentsteps == 0 && (!isBlocked(Player.x + tileSize, Player.y))) {
                        currentsteps = tileSize / Player.speed;
                        sprite = Player.walkRight;
                    }

                }
            } else if (input.isKeyPressed(Input.KEY_SPACE)) {
                orbs.add(new Orb(Player.x, Player.y, direction));
            }
            if (currentsteps > 0) {
                for (Enemy e : enemies) {
                     if(e.isIsAlive()){
                    Arrow thisArrow = new Arrow(e.getskX(), e.getskY(), e.direction);
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

            if (!attacking) {

                currentsteps -= 1;
                if (direction == "up") {

                    Player.y -= Player.speed;

                } else if (direction == "down") {
                    Player.y += Player.speed;
                } else if (direction == "left") {
                    Player.x -= Player.speed;
                } else if (direction == "right") {
                    Player.x += Player.speed;
                }

            }
            moveenemies();

        }
        Player.rect.setLocation(Player.getplayershitboxX(), Player.getplayershitboxY());

        for (Treasure i : treasures) {

            if (Player.rect.intersects(i.hitbox)) {

                if (i.isvisible) {

                    Player.gold += 100;
                    i.isvisible = false;
                }

            }
        }
        
        
        for (Mine m : mines) {

            if (Player.rect.intersects(m.getHitBox())) {
                
                if (m.isVisible()) {

                    
                    m.setVisible(false);
                    m.setExplosionCount(38);
                    Player.health -= Mine.damage;
                }

            }
        }

        for (Arrow a : arrows) {

            if (a.isvisible) {
                if ((a.x > (grassMap.getWidth() * tileSize) || a.y > (grassMap.getHeight() * tileSize)) || isBlocked(a.x, a.y)) {

                    a.isvisible = false;
//                    if (arrows.size() > 1) {
//                        arrows.remove(a);
//                    }

                } else if (Player.rect.intersects(a.hitbox)) {
                    Player.health -= 10000;
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

            if (Player.rect.intersects(e.rect)) {

                Player.health -= 100;

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

                if (o.orbHitbox.intersects(e.rect) && o.isVisible()) {
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
        Player.health -= counter / 1000;
        if (Player.health
                <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (Player.gold
                >= 400) {
            makevisible();
            Player.gold = 0;
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

    private void drawenemies() throws SlickException {

        try {

            for (Enemy e : enemies) {
                //if(e.isAlive != false){
                //System.out.println("The current selection is: " +e.currentanime);
                
                if(e.isIsAlive()){
                e.currentanime.draw(e.Bx, e.By);}
            }

             
        } catch (IndexOutOfBoundsException e) {

            System.err.println("IndexOutOfBoundsException: "
                    + e.getMessage());

        }

    }

}
