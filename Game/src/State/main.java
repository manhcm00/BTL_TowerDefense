package State;

import State.State.Game;
import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.opengl.Display;

import static helpers.Artist.BeginSession;

public class main {
    public static void main(String[] args) {
        BeginSession();

        Game game = new Game();

        while(!Display.isCloseRequested()) {

            Clock.update();


            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}
