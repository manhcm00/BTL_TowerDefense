package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import java.awt.*;
import java.util.ArrayList;

import static helpers.Artist.*;

public class UI {
    private ArrayList<Button> buttons;
    private UI mainUI;
    private TrueTypeFont font;
    private Font awtFont;


    public UI() {
        buttons = new ArrayList<Button>();
        awtFont = new Font("Consolas" , Font.BOLD , 16);
        font = new TrueTypeFont(awtFont , false);
    }

    public void drawInfo(int x , int y , String s) {
        font.drawString(x , y , s);
    }
    public boolean isButtonClicked(String buttonName) {
        Button b = getButton(buttonName);
        float mouseY= HEIGHT - Mouse.getY() - 1;
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
