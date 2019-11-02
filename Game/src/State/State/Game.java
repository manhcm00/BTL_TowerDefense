package State.State;

import Entity.Enemy.Enemies.BossEnemy;
import Entity.Enemy.Enemies.NormalEnemy;
import Entity.Enemy.Enemies.TankerEnemy;
import Entity.Enemy.Enemy;
import Entity.Enemy.WaveManager;
import Entity.Tower.MachineGunTower;
import Entity.Tower.NormalTower;
import Entity.Tower.SniperTower;
import Map.NormalMap;
import Tile.TileGrid;
import Player.Player;
import UI.*;
import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    public static final int TILE_SIZE = 32;
    private UI towerPickerUI;

    public Game() {
        grid = new TileGrid(NormalMap.map);
        waveManager = new WaveManager(grid.getTile(1 , 0), grid,20);
        player = new Player(grid, waveManager);
        setup();
    }

    private void setup() {
        towerPickerUI = new UI();
        towerPickerUI.getButtons().add(new Button("snipertower", QuickLoad("snipertower"), 22*32, 32));
        towerPickerUI.getButtons().add(new Button("normaltower", QuickLoad("normaltower"), 22*32, 96));
        towerPickerUI.getButtons().add(new Button("machineguntower", QuickLoad("normaltower"), 22*32, 160));
    }

    private void updateUI() {
        towerPickerUI.draw();
        if (Mouse.next()) {
            boolean MouseClicked = Mouse.isButtonDown(0);
            if (MouseClicked) {
                if (towerPickerUI.isButtonClicked("snipertower"))
                    player.setTempTower(new SniperTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                if (towerPickerUI.isButtonClicked("normaltower"))
                    player.setTempTower(new NormalTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                if (towerPickerUI.isButtonClicked("machineguntower"))
                    player.setTempTower(new MachineGunTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }

    public void update() {
        grid.Draw();
        waveManager.Update();
        player.Update();
        updateUI();
    }

}