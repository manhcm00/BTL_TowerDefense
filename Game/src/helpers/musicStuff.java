package helpers;

import jdk.jfr.FlightRecorder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class musicStuff {
    public static File soundOfNormalGun, soundOfMachineGun, soundOfSniperGun,startSound;

    public musicStuff() {
        soundOfNormalGun = new File("C:\\Users\\ADMIN\\Downloads\\eclipse-workspace\\eclipse-workspace\\Game\\src\\helpers\\sound\\normal.wav");
        soundOfMachineGun = new File("C:\\Users\\ADMIN\\Downloads\\eclipse-workspace\\eclipse-workspace\\Game\\src\\helpers\\sound\\machine.wav");
        soundOfSniperGun = new File("C:\\Users\\ADMIN\\Downloads\\eclipse-workspace\\eclipse-workspace\\Game\\src\\helpers\\sound\\sniper.wav");
        startSound = new File("C:\\Users\\ADMIN\\Downloads\\eclipse-workspace\\eclipse-workspace\\Game\\src\\helpers\\sound\\start.wav");
    }


    public static void main(String[] args) {
        musicStuff sound = new musicStuff();
        sound.playSoundOfNormalGun();
    }

    public static void playSound(File path) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(path));
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSoundOfNormalGun() {
        playSound(soundOfNormalGun);
    }

    public static void playsoundOfMachineGun() {
        playSound(soundOfMachineGun);
    }

    public static void playSoundOfSniperGun() {
        playSound(soundOfSniperGun);
    }

    public static void playStartSound() {
        playSound(startSound);
    }
}
