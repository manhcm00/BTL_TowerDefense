package Entity.Enemy.Enemies;

import Entity.Enemy.Enemy;
import Tile.*;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.QuickLoad;

public class NormalEnemy extends Enemy {
    public NormalEnemy(Tile startTile, TileGrid grid, int width, int height) {
        super(startTile, grid, width, height);
        this.texture = NormalEnemy;
        this.speed = SPEED;
        this.health = HEALTH;
        this.reward = REWARD;
        this.damage = DAMAGE;
    }

    public static Texture NormalEnemy = QuickLoad("enemyNormal");
    private static final float SPEED = 3;
    private static final int HEALTH = 10;
    private static final int REWARD = 20;
    private static final int DAMAGE = 1;
}
