package Tile;

public enum TileType {


    Grass("grass" , true) , Sand("sand" , false), Rock("rock" , false) , NULL("rock" , false);

    String textureName;
    boolean buildable;


    TileType(String textureName , boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}

