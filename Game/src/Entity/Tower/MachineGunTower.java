package Entity.Tower;

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
        this.buyingCost = BUYINGCOST;
        this.range = RANGE;
    }

    public static Texture NormalTowerTexture = QuickLoad("enemy");
    private static final int DAMAGE = 1;
    private static final float FIRINGSPEED = 15;
    public static final int BUYINGCOST = 50;
    private static final float RANGE = 80;
}
