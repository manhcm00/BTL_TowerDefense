package Entity.Tower;

import Entity.Bullet.Bullet;
import Entity.Bullet.MachineGunBullet;
import Entity.Bullet.SniperBullet;
import Entity.Enemy.Enemy;
import Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.QuickLoad;

public class MachineGunTower extends BasicTower {
    public MachineGunTower(Tile startTile, ArrayList<Enemy> enemies) {
        super(NormalTowerTexture, startTile, enemies);
        this.damage = DAMAGE;
        this.firingSpeed = FIRINGSPEED;
        buyingCost = BUYINGCOST;
        this.range = RANGE;
        refundPrize = REFUNDPRIZE;
        upgradePrize = UPGRADEPRIZE;
    }

    protected void shoot() {
        timeSinceLastShot = 0;
        if (getTarget() != null && distance(getTarget()) < this.range)
            projectiles.add(new MachineGunBullet(getTarget(), this));
        if(!projectiles.isEmpty() && distance(getTarget()) < this.range) {
            for (Bullet p : projectiles) {
                if (p.isArrivedAtTarget()) projectiles.remove(p);
                if (p.isOutOfScreen()) projectiles.remove(p);
            }
        }
    }

    public static Texture NormalTowerTexture = QuickLoad("normaltower");
    private static final int DAMAGE = 1;
    private static final float FIRINGSPEED = 10;
    public static final int BUYINGCOST = 50;
    private static final float RANGE = 80;
    private static final int REFUNDPRIZE = 20;
    private static final int UPGRADEPRIZE = 25;
}
