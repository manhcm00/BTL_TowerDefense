package State.State;

import helpers.Clock;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class MainMenu {

    private Texture background;

    public MainMenu() {
        BeginSession();
        background = QuickLoad("menu");
        while(!Display.isCloseRequested()) {

            Clock.update();

            update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();

    }

    public void update() {
        drawQuadTex(background, 0, 0, 640, 480);
    }
}
