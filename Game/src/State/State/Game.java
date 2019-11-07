package State.State;

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
    private UI buttons;

    public Game() {
        grid = new TileGrid(NormalMap.map);
        waveManager = new WaveManager(grid.getTile(1 , 0), grid,20);
        player = new Player(grid, waveManager);
        setup();
    }

    private void setup() {
        buttons = new UI();
        buttons.getButtons().add(new Button("snipertower", QuickLoad("snipertower"), 22*32, 32));
        buttons.getButtons().add(new Button("normaltower", QuickLoad("normaltower"), 22*32, 96));
        buttons.getButtons().add(new Button("machineguntower", QuickLoad("normaltower"), 22*32, 160));
        buttons.getButtons().add(new Button("upgrade", QuickLoad("UpgradeButtonGraphic"), 22*32, 280));
        buttons.getButtons().add(new Button("refund", QuickLoad("SellButtonGraphic"), 22*32, 360));

    }

    private void updateUI() {
        buttons.draw();
        buttons.drawInfo(520 , 0 , player.healthString());
        buttons.drawInfo(520 , 20 , player.creditsString());
        if (Mouse.next()) {
            boolean MouseClicked = Mouse.isButtonDown(0);
            if (MouseClicked) {
                if (buttons.isButtonClicked("snipertower"))
                    player.setTempTower(new SniperTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                if (buttons.isButtonClicked("normaltower"))
                    player.setTempTower(new NormalTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                if (buttons.isButtonClicked("machineguntower"))
                    player.setTempTower(new MachineGunTower(grid.getTile(0, 0), waveManager.getCurrentWave().getEnemyList()));
                if(buttons.isButtonClicked("upgrade"))
                    player.setHoldingUpgrade(true);
                if(buttons.isButtonClicked("refund"))
                    player.setHoldingRefund(true);
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