package Entity.Bullet;

import Entity.Enemy.Enemy;
import Entity.Tower.BasicTower;

import static helpers.Artist.QuickLoad;

public class SniperBullet extends Bullet {
    public SniperBullet(Enemy target, BasicTower tower) {
        super(target, tower);
        this.texture = QuickLoad("sniperBullet");
        this.speed = 50;
    }
}
