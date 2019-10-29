package Entity;

import Tile.Tile;
import Tile.TileGrid;
import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;

import java.util.ArrayList;

import  static helpers.Artist.*;

public class Enemy {
    private int width , height , health , currentCheckPoint;
    private float x , y , speed;
    private Texture texture;
    private Tile startTile;
    private boolean first = true , alive = true;
    private TileGrid grid;

    private ArrayList<CheckPoint>	checkpoints;
    private int[] directions;
    private final int HEALTH = 10;


    public TileGrid getGrid() {
        return grid;
    }

    public Enemy(Texture texture , Tile startTile , TileGrid grid  , int width , int height , float speed) {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.grid = grid;
        this.checkpoints = new ArrayList<CheckPoint>();
        this.directions = new int[2];
        this.health = 10;

        // Direction x
        this.directions[0] = 0;

        // Direction y
        this.directions[1] = 1;
        directions = FindNextD(startTile);
        this.currentCheckPoint = 0;
        PopulateCheckPointList();
    }

    private CheckPoint FindNextC(Tile s , int dir[]) {
        Tile next = null;
        CheckPoint c = null;
        // tim cp
        boolean found = false;
        // tim diem re
        int counter = 1;


        while(!found) {
            if(s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter ,
                    s.getYPlace() + dir[1] * counter).getType()) {
                found = true;
                counter -= 1;
                next = grid.getTile(s.getXPlace() + dir[0] * counter ,s.getYPlace() + dir[1] * counter);

            }

            counter ++;
        }

        c= new CheckPoint(dir[0] , dir[1] , next);


        return c;
    }

    public void takeDamage(int damage){
        health = health - damage;
        if(health <= 0){
            Die();
        }
    }



    private void PopulateCheckPointList() {

        checkpoints.add(FindNextC(startTile , directions = FindNextD(startTile)));
        int counter = 0;
        boolean cont = true;
        while(cont) {
            int[] currentD = FindNextD(checkpoints.get(counter).getTile());
            if(currentD[0] == 2 || counter == 20) {
                cont = false;

            } else {
                checkpoints.add( FindNextC ( checkpoints.get(counter).getTile() ,
                        directions = FindNextD(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private boolean CheckPointReach() {
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckPoint).getTile();
        if(x > t.getX() - 2 && x < t.getX() + 2 && y > t.getY() - 2 && y < t.getY() + 2) {
            reached = true;
            x = t.getX();
            y = t.getY();
        }
        return reached;
    }




    // tim huong tiep theo
    private int[] FindNextD(Tile s) {
        int[] dir = new int[2];


        Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1 );
        Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());
        Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1 );
        Tile l = grid.getTile(s.getXPlace() - 1 , s.getYPlace());



        if(s.getType() == u.getType() && directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if( s.getType() == r.getType() && directions[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if( s.getType() == l.getType() && directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else if( s.getType() == d.getType() && directions[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }
        return dir;
    }

    public void Update() {
        if(first)	first = false;
        else {
            if(CheckPointReach()) {
                if(currentCheckPoint + 1 == checkpoints.size()) {
                    Die();
                } else {
                    currentCheckPoint ++;
                }
            } else {
                x += Delta() * checkpoints.get(currentCheckPoint).getxDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckPoint).getyDirection() * speed;
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void Die() {
        if(alive)	alive = false;
    }

    public void Draw() {
        drawQuadTex(texture , x , y , width , height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

}
