package Tile;

public enum TlieType {


    Grass("grass" , true) , Sand("sand" , false), Rock("rock" , false) , NULL("rock" , false);

    String textureName;
    boolean buildable;


    TlieType(String textureName , boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}

