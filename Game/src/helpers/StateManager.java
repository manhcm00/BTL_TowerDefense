package helpers;

import State.State.AfterGame;
import State.State.Game;
import State.State.MainMenu;

public class StateManager {
    public static enum GameState {
        MAINMENU, GAME, AFTERGAME;
    }
    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static AfterGame afterGame;

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
            case AFTERGAME:
                if (afterGame == null)
                    afterGame = new AfterGame();
                game = new Game();
                afterGame.update();
                break;
        }
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }
}
