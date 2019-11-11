package Entity.Tower;

import Entity.Bullet.Bullet;
import Entity.Bullet.NormalBullet;
import Entity.Enemy.Enemy;
import Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.QuickLoad;

public class NormalTower extends BasicTower {

    public NormalTower(Tile startTile, ArrayList<Enemy> enemies) {
        super(NormalTowerTexture, startTile, enemies);
        this.damage = DAMAGE;
        this.firingSpeed = FIRINGSPEED;
        buyingCost = BUYINGCOST;
        refundPrize = REFUNDPRIZE;
        this.range = RANGE;
        upgradePrize = UPGRADEPRIZE;
    }

    public void shoot() {
        timeSinceLastShot = 0;
        if (getTarget() != null && distance(getTarget()) < this.range) {
            projectiles.add(new NormalBullet(getTarget(), this));
            sound.playSoundOfNormalGun();
        }
        if(!projectiles.isEmpty() && distance(getTarget()) < this.range) {
            for (Bullet p : projectiles) {
                if (p.isArrivedAtTarget()) projectiles.remove(p);
                if (p.isOutOfScreen()) projectiles.remove(p);
            }
        }
    }

    private static Texture NormalTowerTexture = QuickLoad("normaltower");
    private static final float DAMAGE = 2;
    private static final float FIRINGSPEED = 15;
    private static final int BUYINGCOST = 100;
    private static final float RANGE = 200;
    private static final int REFUNDPRIZE = 20;
    private static final int UPGRADEPRIZE = 50;
}
