package Entity.Tower;

import Entity.Enemy;
import Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

public class BasicTower {
    private float x;
    private float y;
    private float timeSinceLastShot;
    private float firingSpeed;
    private float angle;
    private int width;
    private int height;
    private int damage;
    private Texture baseTexture, basicTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;
    public static final float RANGE = 160;

    public BasicTower(Texture baseTexture, Tile startTile, int damage, ArrayList<Enemy> enemies) {
        this.baseTexture = baseTexture;
        this.basicTexture = QuickLoad("enemy");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.damage = damage;
        this.width =(int) startTile.getWidth();
        this.height =(int) startTile.getHeight();
        this.firingSpeed = 30;
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
                if (distance(enemies.get(i)) <= RANGE)
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
        if (getTarget() != null && distance(getTarget()) < RANGE)
            projectiles.add(new Projectile(QuickLoad("bullet") , getTarget(), 100 ,2, this));
        if(!projectiles.isEmpty() && distance(getTarget()) < RANGE) {
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

        if (!enemies.isEmpty() && distance(getTarget()) < RANGE) {
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
}
