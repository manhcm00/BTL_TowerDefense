package Entity.Bullet;

import Entity.Enemy.Enemy;
import Entity.Tower.BasicTower;

import static helpers.Artist.QuickLoad;

public class MachineGunBullet extends Bullet {
    public MachineGunBullet(Enemy target, BasicTower tower) {
        super(target, tower);
        this.texture = QuickLoad("bullet");
        this.speed = 100;
    }
}
