package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;

public class UI {
    private ArrayList<Button> buttons;
    private UI mainUI;

    public UI() {
        buttons = new ArrayList<Button>();
        buttons.add(new Button("Play", QuickLoad("playButton"), WIDTH/2 - 32, HEIGHT / 2 -100));
        buttons.add(new Button("Editor", QuickLoad("playButton"), WIDTH/2 - 32, HEIGHT / 2 ));
        buttons.add(new Button("Quit", QuickLoad("playButton"), WIDTH/2 - 32, HEIGHT / 2 + 100));

    }

    public boolean isButtonClicked(String buttonName) {
        Button b = getButton(buttonName);
        float mouseY= HEIGHT - Mouse.getY() - 1;
        System.out.println(Mouse.getX() + " " + Mouse.getY());
        if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth()
                && mouseY > b.getY() && mouseY < b.getY() + b.getHeight())
            return true;
        return false;
    }

    private Button getButton(String buttonName) {
        for (Button b : buttons) {
            if (b.getName().equals(buttonName))
                return b;
        }
        return null;
    }

    public void draw() {
        for (Button b : buttons) {
            drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }
}
