package Entity.Enemy;

import Entity.Enemy.Enemies.NormalEnemy;
import Entity.Enemy.Enemies.TankerEnemy;

import java.util.ArrayList;

import static helpers.Clock.Delta;


public class Wave {

    private float timeSinceLastSpawn , spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy>	enemyList;
    private int  enemiesPerWave;
    private boolean waveCompleted;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<Enemy>() ;
        this.waveCompleted = false;

        Spawn();
    }

    public void Update() {
        boolean allEnemiesDead = true;
        if (enemyList.size() < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }

        for(Enemy e : enemyList ) {
            if(!e.isAlive()) {
                //enemyList.remove(e);
            }
            else {
                allEnemiesDead = false;
                e.update();
                e.Draw();
            }
        }
        if (allEnemiesDead) {
            waveCompleted = true;
        }
    }

    private void Spawn() {
        enemyList.add(new TankerEnemy(enemyType.getStartTile() , enemyType.getGrid() ,  32 ,
                32));
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
