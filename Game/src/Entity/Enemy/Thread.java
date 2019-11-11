package Entity.Enemy;

import Entity.Entity;

public interface Thread extends Entity {
    void takeDamage(float damage);
    int setupdDrection(int x, int y);
    void Die();
    void CompletedMission();
}
