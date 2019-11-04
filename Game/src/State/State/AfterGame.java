package State.State;

import UI.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class AfterGame {
    private Texture background;
    private UI menuUI;

    public AfterGame() {
        background = QuickLoad("gameOver");
        menuUI = new UI();
        setup();
    }

    private void setup() {
        menuUI.getButtons().add(new Button("Play", QuickLoad("playButton"), WIDTH/2 - 32, HEIGHT / 2 -50));
        menuUI.getButtons().add(new Button("Quit", QuickLoad("playButton"), WIDTH/2 - 32, HEIGHT / 2 + 50));
    }

    private void updateButton() {
        if (Mouse.isButtonDown(0)) {
            System.out.println(Mouse.getX() + " " + Mouse.getY());
            if (menuUI.isButtonClicked("Play")) {

                StateManager.setState(StateManager.GameState.GAME);
                System.out.println("Play is clicked");
            }
            if (menuUI.isButtonClicked("Quit")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        drawQuadTex(background, 0, 0, 1080, 512);
        updateButton();
        menuUI.draw();
    }

}
