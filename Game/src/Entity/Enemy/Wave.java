package Entity.Enemy;

import Entity.Enemy.Enemies.BossEnemy;
import Entity.Enemy.Enemies.NormalEnemy;
import Entity.Enemy.Enemies.SmallerEnemy;
import Entity.Enemy.Enemies.TankerEnemy;
import Tile.*;
import org.lwjgl.Sys;

import java.util.ArrayList;

import static helpers.Clock.Delta;


public class Wave {

    private float timeSinceLastSpawn , spawnTime;
    private Tile startTile;
    private TileGrid grid;
    private ArrayList<Enemy>	enemyList;
    private boolean waveCompleted;
    private int numberOfEnemies;
    private int current;
    private int[] enemyQueue;

    public Wave(Tile startTile, TileGrid grid, float spawnTime, int[] enemyQueue, int numberEnemies) {
        this.startTile = startTile;
        this.grid = grid;
        this.spawnTime = spawnTime;
        this.enemyQueue = enemyQueue;
        this.timeSinceLastSpawn = 0;
        this.numberOfEnemies = numberEnemies;
        this.enemyList = new ArrayList<Enemy>() ;
        this.waveCompleted = false;
        this.current = 1;

        Spawn();
    }

    public void Update() {
        boolean allEnemiesDead = true;
        if (current < numberOfEnemies ) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                Spawn();
                timeSinceLastSpawn = 0;
                current++;
            }
        }

        for(Enemy e : enemyList ) {
            if(e.isAlive()) {
                allEnemiesDead = false;
                e.update();
                e.Draw();
            }
            else {
                /*if (enemyList.size() > 1)
                    enemyList.remove(e);*/
            }
        }
        if (allEnemiesDead) {
            waveCompleted = true;
        }
    }

    private void Spawn() {
        if (enemyQueue[current] == 1)
            enemyList.add(new NormalEnemy(startTile , grid ,  32 , 32));
        if (enemyQueue[current] == 2)
            enemyList.add(new SmallerEnemy(startTile , grid ,  32 , 32));
        if (enemyQueue[current] == 3)
            enemyList.add(new TankerEnemy(startTile , grid ,  32 , 32));
        if (enemyQueue[current] == 4)
            enemyList.add(new BossEnemy(startTile , grid ,  32 , 32));
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
