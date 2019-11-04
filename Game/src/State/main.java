package State;

import State.State.StateManager;
import helpers.Clock;
import org.lwjgl.opengl.Display;

import static helpers.Artist.BeginSession;

public class main {
    public static void main(String[] args) {
        BeginSession();

        while(!Display.isCloseRequested()) {

            Clock.update();

            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}
