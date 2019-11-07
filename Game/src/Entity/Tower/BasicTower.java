package Entity.Tower;

import Entity.Bullet.Bullet;
import Entity.Enemy.Enemy;
import Entity.Entity;
import Player.Player;
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
    protected int level;
    protected Texture basicTexture;
    protected Tile startTile;
    protected ArrayList<Bullet> projectiles;
    protected ArrayList<Enemy> enemies;
    protected Enemy target;
    protected float range;
    protected static int buyingCost, refundPrize, upgradePrize;

    public BasicTower(Texture basicTexture, Tile startTile, ArrayList<Enemy> enemies) {
        this.basicTexture = basicTexture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width =(int) startTile.getWidth();
        this.height =(int) startTile.getHeight();
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Bullet>();
        this.enemies = enemies;
        this.target = getTarget();
        this.angle = calculateAngle();
        this.level = 1;
    }

    protected float distance(Enemy enemy) {
        if (enemy != null)
            return (float) Math.sqrt((enemy.getX() - x)*(enemy.getX() - x) + (enemy.getY() - y)* (enemy.getY() - y));
        else return 10000;
    }

    protected Enemy getTarget() {

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

    abstract protected void shoot();

    public void update() {
        timeSinceLastShot += Delta();
        if (timeSinceLastShot >= firingSpeed) {
            shoot();
        }
        for (Bullet p : projectiles) p.update();

        if (!enemies.isEmpty() && distance(getTarget()) < this.range) {
            angle = calculateAngle();
        }
        Draw();
    }

    public void Draw() {
        //drawQuadTex(baseTexture, x , y , width , height);
        drawQuadTexRot(basicTexture, x, y, width, height, angle);
        if(level == 2) {
            drawQuadTex(QuickLoad("UpgradeLevel") , x + 13 , y + 32 , 5 ,5);
        }
        if(level == 3) {
            drawQuadTex(QuickLoad("UpgradeLevel") , x + 10 , y + 32 , 5 ,5);
            drawQuadTex(QuickLoad("UpgradeLevel") , x + 17 , y + 32  , 5 ,5);
        }
    }

    public void upgrade() {
        if (level < 3 && Player.getCredits() >= upgradePrize) {
            this.damage *= 1.5;
            this.range *= 1.2;
            this.firingSpeed *= 0.8;
            this.level ++;
            Player.addCredits(-upgradePrize);
        }
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

    public static int getRefundPrize() {
        return refundPrize;
    }

    public static void setRefundPrize(int refundPrize) {
        BasicTower.refundPrize = refundPrize;
    }

    public static int getUpgradePrize() {
        return upgradePrize;
    }

    public static void setUpgradePrize(int upgradePrize) {
        BasicTower.upgradePrize = upgradePrize;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
