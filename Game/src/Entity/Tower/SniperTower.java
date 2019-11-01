package Entity.Tower;

import Entity.Bullet.Bullet;
import Entity.Bullet.SniperBullet;
import Entity.Enemy.Enemy;
import Tile.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.QuickLoad;

public class SniperTower extends BasicTower {
    public SniperTower(Tile startTile, ArrayList<Enemy> enemies) {
        super(NormalTowerTexture, startTile, enemies);
        this.damage = DAMAGE;
        this.firingSpeed = FIRINGSPEED;
        this.buyingCost = BUYINGCOST;
        this.range = RANGE;
    }

    protected void shoot() {
        timeSinceLastShot = 0;
        if (getTarget() != null && distance(getTarget()) < this.range)
            projectiles.add(new SniperBullet(getTarget(), this));
        if(!projectiles.isEmpty() && distance(getTarget()) < this.range) {
            for (Bullet p : projectiles) {
                if (p.isArrivedAtTarget()) projectiles.remove(p);
            }
        }
    }

    public static Texture NormalTowerTexture = QuickLoad("snipertower");
    private static final int DAMAGE = 15;
    private static final float FIRINGSPEED = 60;
    public static final int BUYINGCOST = 150;
    private static final float RANGE = 320;
}
