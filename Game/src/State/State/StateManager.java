package State.State;

public class StateManager {
    public static enum GameState {
        MAINMENU, GAME, AFTERGAME;
    }
    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static AfterGame afterGame;
    private static boolean restarted = false;

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
                if (restarted == false) {
                    game = new Game();
                    restarted = true;
                }
                afterGame.update();
                break;
        }
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }

    public static boolean isRestarted() {
        return restarted;
    }

    public static void setRestarted(boolean restarted) {
        StateManager.restarted = restarted;
    }
}