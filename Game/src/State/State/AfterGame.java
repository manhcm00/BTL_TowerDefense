package State.State;

import Player.Player;
import UI.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class AfterGame {
    private Texture winBackground, loseBackground;
    private UI menuUI;

    public AfterGame() {
        winBackground = QuickLoad("win");
        loseBackground = QuickLoad("lose");
        menuUI = new UI();
        setup();
    }

    private void setup() {
        menuUI.getButtons().add(new Button("Play Again", QuickLoad("play"), WIDTH/2 - 32, HEIGHT / 2 + 150));
    }

    private void updateButton() {
        if (Mouse.isButtonDown(0)) {
            System.out.println(Mouse.getX() + " " + Mouse.getY());
            if (menuUI.isButtonClicked("Play Again")) {
                if (!StateManager.restarted) {
                    StateManager.game = new Game();
                    StateManager.restarted = true;
                }
                StateManager.setState(StateManager.GameState.GAME);
                System.out.println("Play is clicked");
            }
        }
    }

    public void update() {
        if (Player.isWin())
            drawQuadTex(winBackground, -32, 0, 1440, 768);
        else
            drawQuadTex(loseBackground, -32, 0, 1440, 768);
        updateButton();
        menuUI.draw();
    }

}
