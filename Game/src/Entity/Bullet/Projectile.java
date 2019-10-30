package Entity.Bullet;

import Entity.Enemy.Enemy;
import Entity.Entity;
import Entity.Tower.BasicTower;
import State.State.Game;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.drawQuadTex;
import static helpers.Clock.Delta;

public class Projectile implements Entity {
    private Texture texture;
    private float x, y, speed;
    private int damage;
    private Enemy target;
    private BasicTower tower;
    private float xVloctity, yVloctity;
    private float xDest , yDest;
    private boolean arrivedAtTarget;


    public Projectile(Texture texture, Enemy target, float speed, int damage, BasicTower tower) {
        this.texture = texture;
        this.x = tower.getX() + Game.TILE_SIZE / 2 - 3;
        this.y = tower.getY() + Game.TILE_SIZE / 2 - 3;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.tower = tower;
        xDest = target.getX(); yDest = target.getY();
        xVloctity = 0f;
        yVloctity = 0f;
        calculateDirection();
        arrivedAtTarget = false;
    }

    private void calculateDirection() {
        double angle = Math.atan2((target.getY() - y + Game.TILE_SIZE / 4), (target.getX() - x + Game.TILE_SIZE / 4));
        xVloctity = (float) Math.cos(angle);
        yVloctity = (float) Math.sin(angle);
    }

    public void update() {
        if(x < xDest + 20 && x > xDest - 20 && y < yDest + 20 && y > yDest -20){
            if (!arrivedAtTarget){
                target.takeDamage(damage);
                arrivedAtTarget = true;
            }
        }
        else {
            x += xVloctity * Delta() * speed;
            y += yVloctity * Delta() * speed;
            Draw();
        }
    }

    public void Draw() {
        drawQuadTex(texture, x, y, 6, 6);
    }

    public boolean isArrivedAtTarget() {
        return arrivedAtTarget;
    }

    public void setArrivedAtTarget(boolean arrivedAtTarget) {
        this.arrivedAtTarget = arrivedAtTarget;
    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }
}
