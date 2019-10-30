package Entity.Tower;

import Entity.Bullet.Projectile;
import Entity.Enemy.Enemy;
import Entity.Entity;
import Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

public abstract class BasicTower implements Entity {
    protected float x;
    protected float y;
    protected float timeSinceLastShot;
    protected float firingSpeed;
    protected float angle;
    protected int width;
    protected int height;
    protected int damage;
    protected Texture basicTexture;
    protected Tile startTile;
    protected ArrayList<Projectile> projectiles;
    protected ArrayList<Enemy> enemies;
    protected Enemy target;
    protected float range;
    public static int buyingCost;

    public BasicTower(Texture basicTexture, Tile startTile, ArrayList<Enemy> enemies) {
        this.basicTexture = basicTexture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width =(int) startTile.getWidth();
        this.height =(int) startTile.getHeight();
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.target = getTarget();
        this.angle = calculateAngle();
    }

    private float distance(Enemy enemy) {
        if (enemy != null)
            return (float) Math.sqrt((enemy.getX() - x)*(enemy.getX() - x) + (enemy.getY() - y)* (enemy.getY() - y));
        else return 10000;
    }

    private Enemy getTarget() {

        for (int i = 0; i< enemies.size(); i++) {
            if (enemies.get(i).isAlive()) {
                if (distance(enemies.get(i)) <= this.range)
                    return enemies.get(i);
            }
        }
        return null;
    }

    public float calculateAngle() {
        if(getTarget() != null)
            return (float) Math.toDegrees(Math.atan2((getTarget().getY() - y), (getTarget().getX() - x)));
        else return 0;
    }

    private void shoot() {
        timeSinceLastShot = 0;
        if (getTarget() != null && distance(getTarget()) < this.range)
            projectiles.add(new Projectile(QuickLoad("bullet") , getTarget(), 100 ,2, this));
        if(!projectiles.isEmpty() && distance(getTarget()) < this.range) {
            for (Projectile p : projectiles) {
                if (p.isArrivedAtTarget()) projectiles.remove(p);
            }
        }
    }

    public void update() {
        timeSinceLastShot += Delta();
        if (timeSinceLastShot >= firingSpeed) {
            shoot();
        }
        for (Projectile p : projectiles) p.update();

        if (!enemies.isEmpty() && distance(getTarget()) < this.range) {
            angle = calculateAngle();
        }
        Draw();
    }

    public void Draw() {
        //drawQuadTex(baseTexture, x , y , width , height);
        drawQuadTexRot(basicTexture, x, y, width, height, angle);
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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getBuyingCost() {
        return buyingCost;
    }

    public void setBuyingCost(int buyingCost) {
        this.buyingCost = buyingCost;
    }
}
