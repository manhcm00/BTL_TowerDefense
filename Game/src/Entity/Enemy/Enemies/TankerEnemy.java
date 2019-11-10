package Entity.Enemy.Enemies;

import Entity.Enemy.Enemy;
import Tile.*;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.QuickLoad;

public class TankerEnemy extends Enemy {
    public TankerEnemy(Tile startTile, TileGrid grid, int width, int height) {
        super(startTile, grid, width, height);
        this.texture = TankerEnemy;
        this.speed = SPEED;
        this.health = HEALTH;
        this.reward = REWARD;
        this.damage = DAMAGE;
        this.armor = ARMOR;
    }

    public static Texture TankerEnemy = QuickLoad("enemyTanker");
    private static final float SPEED = 2;
    private static final int HEALTH = 20;
    private static final int REWARD = 30;
    private static final int DAMAGE = 1;
    private static final float ARMOR = 8;
}
