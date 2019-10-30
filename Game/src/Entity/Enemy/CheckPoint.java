package Entity.Enemy;

import Tile.Tile;

public class CheckPoint {
    private Tile tile;
    private int xDirection , yDirection ;


    public CheckPoint( int xDirection , int yDirection , Tile tile) {
        this.xDirection = xDirection;
        this.tile = tile;
        this.yDirection = yDirection;
    }


    public Tile getTile() {
        return tile;
    }


    public int getxDirection() {
        return xDirection;
    }


    public int getyDirection() {
        return yDirection;
    }
}
