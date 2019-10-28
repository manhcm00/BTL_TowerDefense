package Tile;

public class TileGrid {
    public Tile[][] map;
    public int TileWidth , TileHeight;

    public TileGrid() {
        map = new Tile[20][15];
        for(int i = 0; i < map.length ; i++) {
            for(int j = 0 ; j < map[i].length ; j++) {
                map[i][j] = new Tile(i * 32 , j * 32 , 32 , 32 , TlieType.Grass);
            }
        }
    }

    public void Draw() {
        for(int i = 0; i < map.length ; i++) {
            for(int j = 0 ; j < map[i].length ; j++) {
                Tile t = map[i][j];
                t.Draw();
            }
        }
    }

    public TileGrid(int[][] newMap) {
        map = new Tile[20][15];
        TileWidth = newMap[0].length;
        TileHeight = newMap.length;
        for(int i = 0; i < map.length ; i++) {
            for(int j = 0 ; j < map[i].length ; j++) {
                switch(newMap[j][i]) {
                    case 0:
                        map[i][j] = new Tile(i * 32 , j * 32 , 32 , 32 , TlieType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * 32 , j * 32 , 32 , 32 , TlieType.Sand);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * 32 , j * 32 , 32 , 32 , TlieType.Rock);
                        break;
                }

            }
        }
    }

    public void SetTile(int xCoord , int yCoord , TlieType type) {
        map[xCoord][yCoord] = new Tile(xCoord * 32 , yCoord*32 , 32 ,32 , type);
    }

    public Tile getTile(int X , int Y) {
        if(X >= 0 && X < TileWidth && Y >= 0 && Y < TileHeight) {
            return map[X][Y];
        } else {
            return new Tile(0 , 0 , 0 , 0 , TlieType.NULL);
        }
    }

}
