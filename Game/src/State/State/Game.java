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
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    public static final int TILE_SIZE = 32;
    private UI buttons;
    private Texture background;

    public Game() {
        grid = new TileGrid(NormalMap.map);
        waveManager = new WaveManager(grid.getTile(1 , 0), grid,20);
        player = new Player(grid, waveManager);
        setup();
    }

    private void setup() {
        buttons = new UI();
        buttons.getButtons().add(new Button("snipertower", QuickLoad("buttonSniper"), 21*32, 32));
        buttons.getButtons().add(new Button("normaltower", QuickLoad("buttonNormal"), 21*32, 128));
        buttons.getButtons().add(new Button("machineguntower", QuickLoad("buttonMachine"), 21*32, 224));
        buttons.getButtons().add(new Button("upgrade", QuickLoad("UpgradeButtonGraphic"), 20*32, 360));
        buttons.getButtons().add(new Button("refund", QuickLoad("SellButtonGraphic"), 22*32, 360));
        background = QuickLoad("background");
    }

    private void updateUI() {
        drawQuadTex(background, 640, 0, 1080, 768);
        buttons.draw();
        buttons.drawInfo(650 , 400 , player.healthString());
        buttons.drawInfo(650 , 420 , player.creditsString());
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