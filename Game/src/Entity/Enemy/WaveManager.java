package Entity.Enemy;

import Player.Player;
import State.State.StateManager;
import Tile.*;

public class WaveManager {

    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber;
    private Tile startTile;
    private TileGrid grid;
    private Wave currentWave;
    private boolean finishedAllWave;
    public int[][] EnemyStream = {
            {1, 1, 1, 1, 1},
            {2, 2, 1, 1, 1},
            {2, 2, 1, 1, 1},
            {2, 2, 1, 1, 1, 3},
            {2, 2, 1, 1, 1, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1, 1, 1, 3, 3, 3},
            {3, 3, 3, 3, 3},
            {1, 1, 1, 4},
    };
    private int[] numberEnemies = {5, 5, 5, 6, 6, 9, 10, 6, 5, 4};
    private int numberOfWave = 10;

    public WaveManager(Tile startTile, TileGrid grid , float timeBetweenEnemies) {
        this.startTile = startTile;
        this.grid = grid;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        finishedAllWave = false;
        newWave();
    }

    public void Update() {
        if (!currentWave.isCompleted())
            currentWave.Update();
        else if (waveNumber > numberOfWave - 1) {
            finishedAllWave = true;
            StateManager.setState(StateManager.GameState.AFTERGAME);
            StateManager.setRestarted(false);
        }
        else newWave();
    }

    private void newWave() {
        currentWave = new Wave(startTile, grid, timeBetweenEnemies, EnemyStream[waveNumber], numberEnemies[waveNumber]);
        waveNumber++;
        System.out.println("Beginning wave " + waveNumber);
        System.out.println("Player health " + Player.getHealth());
    }

    public Wave getCurrentWave() {
        return currentWave;
    }
}
