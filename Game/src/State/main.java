package State;

import Entity.Enemy;
import Entity.Tower.WaveManager;
import Map.NormalMap;
import Player.Player;
import State.State.Game;
import Tile.TileGrid;
import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.opengl.Display;

import static helpers.Artist.BeginSession;
import static helpers.Artist.QuickLoad;

public class main {
    public static void main(String[] args) {
        BeginSession();

        Game game = new Game();

        while(!Display.isCloseRequested()) {

            Clock.update();


            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}
