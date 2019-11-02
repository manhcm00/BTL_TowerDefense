package Entity.Enemy.Enemies;

import Entity.Enemy.Enemy;
import Tile.*;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.QuickLoad;

public class BossEnemy extends Enemy {
    public BossEnemy(Tile startTile, TileGrid grid, int width, int height) {
        super(startTile, grid, width, height);
        this.texture = BossEnemy;
        this.speed = SPEED;
        this.health = HEALTH;
        this.reward = REWARD;
        this.damage = DAMAGE;
        this.armor = ARMOR;
    }

    public static Texture BossEnemy = QuickLoad("enemyBoss");
    private static final float SPEED = 1;
    private static final int HEALTH = 30;
    private static final int REWARD = 200;
    private static final int DAMAGE = 5;
    private static final float ARMOR = 8;
}
