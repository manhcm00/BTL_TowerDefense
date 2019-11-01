package Entity.Bullet;


import Entity.Enemy.Enemy;
import Entity.Tower.BasicTower;

import static helpers.Artist.QuickLoad;

public class NormalBullet extends Bullet {
    public NormalBullet(Enemy target, BasicTower tower) {
        super(target, tower);
        this.texture = QuickLoad("bullet");
        this.damage = 2;
        this.speed = 100;
    }
}
