package State;

import Map.NormalMap;
import State.State.Game;
import State.State.MainMenu;

public class Laucher {
    public static void main(String[] args) {
        int [][] map = NormalMap.map;
        MainMenu menu = new MainMenu();
        Game game = new Game();
        //game.start();
    }
}
