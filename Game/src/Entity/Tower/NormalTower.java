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
        this.buyingCost = BUYINGCOST;
        this.range = RANGE;
    }

    protected void shoot() {
        timeSinceLastShot = 0;
        if (getTarget() != null && distance(getTarget()) < this.range)
            projectiles.add(new NormalBullet(getTarget(), this));
        if(!projectiles.isEmpty() && distance(getTarget()) < this.range) {
            for (Bullet p : projectiles) {
                if (p.isArrivedAtTarget()) projectiles.remove(p);
                if (p.isOutOfScreen()) projectiles.remove(p);
            }
        }
    }

    public static Texture NormalTowerTexture = QuickLoad("normaltower");
    private static final int DAMAGE = 2;
    private static final float FIRINGSPEED = 30;
    public static final int BUYINGCOST = 50;
    private static final float RANGE = 160;
}
