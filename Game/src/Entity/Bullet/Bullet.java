package Entity.Bullet;

import Entity.Enemy.Enemy;
import Entity.Entity;
import Entity.Tower.BasicTower;
import State.State.Game;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawQuadTexRot;
import static helpers.Clock.Delta;

public abstract class Bullet implements Entity {
    protected Texture texture;
    protected float x, y, speed;
    protected float damage;
    protected Enemy target;
    protected BasicTower tower;
    protected float xVloctity, yVloctity;
    protected float xDest , yDest;
    protected boolean arrivedAtTarget;
    protected boolean outOfScreen;


    public Bullet(Enemy target, BasicTower tower) {
        this.x = tower.getX() + Game.TILE_SIZE / 2 - 4;
        this.y = tower.getY() + Game.TILE_SIZE / 2 - 4;
        this.target = target;
        this.tower = tower;
        this.damage = tower.getDamage();
        xDest = target.getX();
        yDest = target.getY();
        xVloctity = 0f;
        yVloctity = 0f;
        calculateDirection();
        arrivedAtTarget = false;
        outOfScreen = false;
    }

    private void calculateDirection() {
        double angle = Math.atan2((target.getY() - y + Game.TILE_SIZE / 2 ), (target.getX() - x + Game.TILE_SIZE / 2));
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
        if (!(x >= 0 && x <= 640 && y >=0 && y <= 480)) {
            this.outOfScreen = true;
        }
    }

    public void Draw() {

        drawQuadTexRot(texture, x, y, texture.getImageWidth(), texture.getImageHeight(), (float) Math.toDegrees(Math.atan2((double) yVloctity, (double) xVloctity)));
    }

    public boolean isArrivedAtTarget() {
        return arrivedAtTarget;
    }

    public void setArrivedAtTarget(boolean arrivedAtTarget) {
        this.arrivedAtTarget = arrivedAtTarget;
    }

    public boolean isOutOfScreen() {
        return outOfScreen;
    }

    public void setOutOfScreen(boolean outOfScreen) {
        this.outOfScreen = outOfScreen;
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
