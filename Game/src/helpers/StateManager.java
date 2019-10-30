package helpers;

import State.State.Game;
import State.State.MainMenu;

public class StateManager {
    public static enum GameState {
        MAINMENU, GAME, EDITOR;
    }
    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;

    public static void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null)
                    mainMenu = new MainMenu();
                mainMenu.update();
                break;
            case GAME:
                if (game == null)
                    game = new Game();
                game.update();

                break;
        }
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }
}
