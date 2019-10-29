package State.State;

import UI.UI;
import helpers.StateManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import java.awt.*;

import static helpers.Artist.*;
import UI.Button;

public class MainMenu {

    private Texture background;
    private UI menuUI;

    public MainMenu() {
        background = QuickLoad("menu");
        menuUI = new UI();
        for (Button b : menuUI.getButtons()) {
            System.out.println(b.getX() + " " + b.getY());
        }
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
        drawQuadTex(background, 0, 0, 640, 480);
        menuUI.draw();
    }

}
