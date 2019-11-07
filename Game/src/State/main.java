package State;

import State.State.StateManager;
import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import static helpers.Clock.paused;
import static helpers.Clock.Pause;

import static helpers.Artist.BeginSession;

public class main {
    public static void main(String[] args) {
        BeginSession();

        while(!Display.isCloseRequested()) {
            while (Keyboard.next()) {
                if(Keyboard.getEventKey() == Keyboard.KEY_P && Keyboard.getEventKeyState()) {
                    Pause();
                }
            }
            if(!paused) {
                Clock.update();
            }
            StateManager.update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}
