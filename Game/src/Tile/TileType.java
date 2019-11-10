package Tile;

public enum TileType {


    Mountain("mountain" , true) , Way("way" , false), Rock("rock" , false) , NULL("rock" , false);

    String textureName;
    boolean buildable;


    TileType(String textureName , boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}

