package State.State;

import Entity.Enemy;
import Entity.Tower.BasicTower;
import Entity.Tower.WaveManager;
import Entity.Wave;
import Map.NormalMap;
import Tile.TileGrid;
import Player.Player;
import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.opengl.Display;

import static helpers.Artist.BeginSession;
import static helpers.Artist.QuickLoad;

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    public static final int TILE_SIZE = 32;

    public Game() {
        grid = new TileGrid(NormalMap.map);

        Enemy e = new Enemy(QuickLoad("enemy") , grid.getTile(1 , 0) , grid , 32 , 32 , 3);

        waveManager = new WaveManager(e , 15, 100);

        player = new Player(grid, waveManager);
    }

    public void update() {
        grid.Draw();
        waveManager.Update();
        player.Update();
    }

}