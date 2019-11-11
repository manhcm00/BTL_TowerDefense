package Entity.Tower;

import Entity.Enemy.Enemy;
import Entity.Entity;

import java.util.ArrayList;

public interface Tower extends Entity {
    float distance(Enemy enemy);
    Enemy getTarget();
    float calculateAngle();
    void shoot();
    void upgrade();
}
