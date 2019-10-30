package State.State;

import Entity.Enemy.Enemies.NormalEnemy;
import Entity.Enemy.Enemy;
import Entity.Enemy.WaveManager;
import Map.NormalMap;
import Tile.TileGrid;
import Player.Player;

import static helpers.Artist.QuickLoad;

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    public static final int TILE_SIZE = 32;

    public Game() {
        grid = new TileGrid(NormalMap.map);

        Enemy e = new NormalEnemy(grid.getTile(1 , 0) , grid , 32 , 32);

        waveManager = new WaveManager(e , 20, 5);

        player = new Player(grid, waveManager);
    }

    public void update() {
        grid.Draw();
        waveManager.Update();
        player.Update();
    }

}